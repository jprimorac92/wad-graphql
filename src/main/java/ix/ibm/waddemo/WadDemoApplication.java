package ix.ibm.waddemo;

import com.coxautodev.graphql.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLHttpServlet;
import ix.ibm.waddemo.resolver.CourseResolver;
import ix.ibm.waddemo.resolver.Mutation;
import ix.ibm.waddemo.resolver.Query;
import ix.ibm.waddemo.resolver.Subscription;
import ix.ibm.waddemo.service.CourseService;
import ix.ibm.waddemo.service.ProfessorService;
import ix.ibm.waddemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WadDemoApplication {
    @Autowired
    private CourseService courseService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private StudentService studentService;

    public static void main(String[] args) {
        SpringApplication.run(WadDemoApplication.class, args);
    }


    @Bean
    public ServletRegistrationBean graphQLServlet() {
        return new ServletRegistrationBean(SimpleGraphQLHttpServlet.newBuilder(buildSchema(courseService, professorService, studentService)).build(), "/graphql");
    }

    private static GraphQLSchema buildSchema(CourseService courseService, ProfessorService professorService, StudentService studentService) {
        return SchemaParser
                .newParser()
                .file("graphql/schema.graphqls")
                .resolvers(
                        new Query(courseService, professorService, studentService),
                        new CourseResolver(professorService),
                        new Mutation(courseService),
                        new Subscription())
                .build()
                .makeExecutableSchema();
    }

}
