package ix.ibm.waddemo.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import ix.ibm.waddemo.pojo.Course;
import ix.ibm.waddemo.pojo.CreateCourseInput;
import ix.ibm.waddemo.pojo.UpdateCourseInput;
import ix.ibm.waddemo.service.CourseService;

public class Mutation implements GraphQLMutationResolver {

    private final CourseService courseService;

    public Mutation(CourseService courseService) {
        this.courseService = courseService;
    }

    public Course createCourse(String title, String description) {
        Course course = new Course();
        course.setTitle(title);
        course.setDescription(description);

        return courseService.create(course);
    }

    public Course updateCourse(long id, String title, String description) {
        Course course = new Course();
        course.setTitle(title);
        course.setDescription(description);

        return courseService.update(course, id);
    }

    public Course createCourseByInput(CreateCourseInput input) {
        Course course = new Course();
        course.setTitle(input.getTitle());
        course.setDescription(input.getDescription());

        return courseService.create(course);
    }

    public Course updateCourseByInput(UpdateCourseInput input) {
        return courseService.update(input);
    }

    public Boolean deleteCourse(Long id) {
        return courseService.delete(id);
    }
}
