package ix.ibm.waddemo.pojo;

import lombok.Data;

@Data
public class CourseGrade {
    private Course course;
    private Student student;
    private int grade;
}
