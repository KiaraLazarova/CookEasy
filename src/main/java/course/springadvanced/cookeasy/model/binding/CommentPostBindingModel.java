package course.springadvanced.cookeasy.model.binding;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CommentPostBindingModel {
    @NotBlank
    @Size(min = 10)
    private String content;
}