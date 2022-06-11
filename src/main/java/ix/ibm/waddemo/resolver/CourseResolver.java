package ix.ibm.waddemo.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import ix.ibm.waddemo.pojo.Course;
import ix.ibm.waddemo.pojo.Professor;
import ix.ibm.waddemo.service.ProfessorService;
import javax.annotation.Resource;

import java.util.List;
import org.springframework.stereotype.Component;

public class CourseResolver implements GraphQLResolver<Course>
{
    private final ProfessorService professorService;

    public CourseResolver(ProfessorService professorService) {
        this.professorService = professorService;
    }


    public List<Professor> professors(Course course) {
        return professorService.findAllProfessorsForCourse(course);
    }
}
