package ix.ibm.waddemo.resolver;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import ix.ibm.waddemo.pojo.Course;
import ix.ibm.waddemo.pojo.WorkshopParticipant;
import ix.ibm.waddemo.pojo.Professor;
import ix.ibm.waddemo.pojo.Student;
import ix.ibm.waddemo.service.CourseService;
import ix.ibm.waddemo.service.ProfessorService;
import ix.ibm.waddemo.service.StudentService;

public class Query implements GraphQLQueryResolver
{
    private final CourseService courseService;

    private final StudentService studentService;

    private final ProfessorService professorService;

    public Query(CourseService courseService, ProfessorService professorService, StudentService studentService)
    {
        this.courseService=courseService;
        this.professorService=professorService;
        this.studentService=studentService;
    }

    /*
    public List<Course> allCourses()
    {
        return courseService.findAll();
    }

    public List<Student> allStudents()
    {
        return studentService.findAll();
    }


    public List<Professor> allProfessors()
    {
        return professorService.findAll();
    }
*/

    /* UNION
    public List<Object> allAll()
    {
        List list1 = studentService.findAll();
        List list2 = professorService.findAll();

        list1.addAll(list2);

        return list1;
    }

     */

    /* INTERFACE
    public List<WorkshopParticipant> allWorkshopParticipants()
    {
        List list1 = professorService.findAll();
        List list2 = studentService.findAll();

        list1.addAll(list2);
        return list1;
    }
     */
}
