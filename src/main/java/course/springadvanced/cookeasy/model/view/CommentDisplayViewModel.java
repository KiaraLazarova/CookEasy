package course.springadvanced.cookeasy.model.view;

import java.time.LocalDate;

public class CommentDisplayViewModel {
    private LocalDate createdOn;
    private String content;
    private String authorUsername;
    private String authorGenderNameEnumName;

    public CommentDisplayViewModel() {
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorUsername() {
        return this.authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public String getAuthorGenderNameEnumName() {
        return this.authorGenderNameEnumName;
    }

    public void setAuthorGenderNameEnumName(String authorGenderNameEnumName) {
        this.authorGenderNameEnumName = authorGenderNameEnumName;
    }
}