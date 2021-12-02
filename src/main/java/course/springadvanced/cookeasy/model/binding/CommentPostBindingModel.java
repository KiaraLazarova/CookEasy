package course.springadvanced.cookeasy.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentPostBindingModel {
    @NotBlank
    @Size(min = 10)
    private String content;

    public CommentPostBindingModel() {
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}