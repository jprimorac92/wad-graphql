package ix.ibm.waddemo.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import ix.ibm.waddemo.pojo.Course;
import ix.ibm.waddemo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Query implements GraphQLQueryResolver {

    private final CourseService courseService;

    @Autowired
    public Query(CourseService courseService) {
        this.courseService = courseService;
    }

    public List<Course> allCourses() {
        return courseService.findAll();
    }

}
