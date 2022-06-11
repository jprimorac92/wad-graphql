package ix.ibm.waddemo.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import ix.ibm.waddemo.pojo.Course;
import ix.ibm.waddemo.pojo.Professor;
import ix.ibm.waddemo.pojo.Student;
import ix.ibm.waddemo.service.ProfessorService;
import ix.ibm.waddemo.service.StudentService;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CourseResolver implements GraphQLResolver<Course> {
    private final ProfessorService professorService;
    private final StudentService studentService;

    public CourseResolver(ProfessorService professorService, StudentService studentService) {
        this.professorService = professorService;
        this.studentService = studentService;
    }

    public List<Professor> professors(Course course) {
        return professorService.findAllProfessorsForCourse(course);
    }

    public List<Student> students(Course course) {
        return studentService.findAllStudentsForCourse(course);
    }
}
