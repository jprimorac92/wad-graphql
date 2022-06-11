package ix.ibm.waddemo.pojo;

import lombok.Data;

@Data
public class CreateCourseInput {
    private Long id;
    private String title;
    private String description;
}
