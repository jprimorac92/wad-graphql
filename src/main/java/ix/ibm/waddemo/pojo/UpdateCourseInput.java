package ix.ibm.waddemo.pojo;

import lombok.Data;

import java.util.Set;

@Data
public class UpdateCourseInput extends CreateCourseInput {
    Long id;
    Set<Long> professors;
    Set<Long> students;
}
