package course.springadvanced.finalproject.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {
    private boolean approved;
    private LocalDateTime createdOn;
    private String content;
    private UserEntity author;
    private RecipeEntity recipeEntity;

    public CommentEntity() {
    }

    @Column(name = "approved", nullable = false)
    public boolean isApproved() {
        return this.approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Column(name = "created_on", nullable = false)
    public LocalDateTime getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "content", nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    public UserEntity getAuthor() {
        return this.author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    @ManyToOne
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    public RecipeEntity getRecipeEntity() {
        return this.recipeEntity;
    }

    public void setRecipeEntity(RecipeEntity recipeEntity) {
        this.recipeEntity = recipeEntity;
    }
}