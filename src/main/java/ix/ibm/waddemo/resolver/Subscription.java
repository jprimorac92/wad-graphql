package ix.ibm.waddemo.resolver;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import ix.ibm.waddemo.pojo.Course;
import ix.ibm.waddemo.pojo.CourseGrade;
import ix.ibm.waddemo.service.CourseService;
import ix.ibm.waddemo.service.StudentService;
import org.reactivestreams.Publisher;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Subscription implements GraphQLSubscriptionResolver {

    private final CourseService courseService;
    private final StudentService studentService;

    private final Random random = new Random();

    public Subscription(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    public Publisher<CourseGrade> getGrades(Long courseId) {
        Course course = courseService.findById(courseId);

        Observable<CourseGrade> observable = Observable.create(e -> {
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            executorService.scheduleAtFixedRate(() -> {
                long studentId = 1L;
                CourseGrade grade = createCourseGrade(course, studentId);
                e.onNext(grade);
            }, 0, 4, TimeUnit.SECONDS);
        });

        ConnectableObservable<CourseGrade> connectableObservable = observable.share().publish();
        connectableObservable.connect();
        return connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
    }

    private CourseGrade createCourseGrade(Course course, long studentId) {
        CourseGrade courseGrade = new CourseGrade();
        courseGrade.setCourse(course);
        courseGrade.setStudent(studentService.findById(studentId));
        courseGrade.setGrade(random.nextInt(11));

        return courseGrade;
    }
}
