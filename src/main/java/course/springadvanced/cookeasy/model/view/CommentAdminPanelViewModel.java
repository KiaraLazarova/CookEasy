package course.springadvanced.cookeasy.model.view;

import java.time.LocalDate;

public class CommentAdminPanelViewModel {
    private Long id;
    private String authorUsername;
    private String recipeTitle;
    private LocalDate createdOn;
    private String content;
    private boolean approved;
    private boolean archived;

    public CommentAdminPanelViewModel() {
    }

    public String getAuthorUsername() {
        return this.authorUsername;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public String getRecipeTitle() {
        return this.recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
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

    public boolean isApproved() {
        return this.approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean isArchived() {
        return this.archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}