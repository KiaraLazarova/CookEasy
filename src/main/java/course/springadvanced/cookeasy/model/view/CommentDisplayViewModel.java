package course.springadvanced.cookeasy.model.view;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CommentDisplayViewModel {
    private LocalDate createdOn;
    private String content;
    private String authorUsername;
    private String authorGenderNameEnumName;
}