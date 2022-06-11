package ix.ibm.waddemo.resolver;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import ix.ibm.waddemo.pojo.Student;
import ix.ibm.waddemo.service.StudentService;
import javax.annotation.Resource;

@Component
public class Subscription implements GraphQLSubscriptionResolver
{
    @Resource
    private StudentService studentService;

    public Publisher<Student> studentLevel(Long studentId)
    {
        Observable<Student> observable = Observable.create(e -> {
            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
                scheduledExecutorService.scheduleAtFixedRate(()->
                    {
                        final Student student = studentService.findById(studentId);
                        student.setLevel((int)Math.floor(Math.random()*5));
                        e.onNext(student);
                    }
                    ,0,2, TimeUnit.SECONDS);
        });

        ConnectableObservable connect = observable.share().publish();
        connect.connect();

        return connect.toFlowable(BackpressureStrategy.BUFFER);
    }
}
