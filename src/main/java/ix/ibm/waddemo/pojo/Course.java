package ix.ibm.waddemo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(exclude = {"professors", "students"})
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "professor_course",
            joinColumns = {@JoinColumn(name = "professorId")},
            inverseJoinColumns = {@JoinColumn(name = "courseId")}
    )
    @JsonIgnoreProperties("courses")
    Set<Professor> professors = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_course",
            joinColumns = {@JoinColumn(name = "studentId")},
            inverseJoinColumns = {@JoinColumn(name = "couserId")}
    )
    @JsonIgnoreProperties("courses")
    Set<Student> students = new HashSet<>();

}
