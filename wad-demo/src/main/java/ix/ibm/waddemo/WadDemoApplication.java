package ix.ibm.waddemo;

import com.coxautodev.graphql.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLHttpServlet;
import ix.ibm.waddemo.resolver.Query;
import ix.ibm.waddemo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WadDemoApplication {

	@Autowired
	private CourseService courseService;

	public static void main(String[] args) {
		SpringApplication.run(WadDemoApplication.class, args);
	}

	private static GraphQLSchema buildSchema(CourseService courseService) {
		return SchemaParser
				.newParser()
				.file("graphql/schema.graphqls")
//                .dictionary()
				.resolvers(new Query(courseService))
				.build()
				.makeExecutableSchema();
	}

	@Bean
	ServletRegistrationBean graphQLServletRegistrationBean() {
		return new ServletRegistrationBean(SimpleGraphQLHttpServlet.newBuilder(buildSchema(courseService)).build(), "/graphql");
	}

}
