package course.springadvanced.cookeasy.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recipes")
public class RecipeEntity extends BaseEntity {
    private LocalDateTime createdOn;
    private String title;
    private int preparationTime;
    private String description;
    private long likes;
    private long saves;
    private long cooks;
    private CategoryEntity categoryEntity;
    private LevelEntity levelEntity;
    private UserEntity author;

    public RecipeEntity() {
    }

    @Column(name = "created_on", nullable = false)
    public LocalDateTime getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "title", nullable = false, unique = true)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "preparation_time", nullable = false)
    public int getPreparationTime() {
        return this.preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    @Lob
    @Column(name = "description", columnDefinition = "LONGTEXT", nullable = false)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "likes", nullable = false)
    public long getLikes() {
        return this.likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    @Column(name = "saves", nullable = false)
    public long getSaves() {
        return this.saves;
    }

    public void setSaves(long saves) {
        this.saves = saves;
    }

    @Column(name = "cooks", nullable = false)
    public long getCooks() {
        return this.cooks;
    }

    public void setCooks(long cooks) {
        this.cooks = cooks;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    public CategoryEntity getCategoryEntity() {
        return this.categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    @ManyToOne
    @JoinColumn(name = "level_id", referencedColumnName = "id")
    public LevelEntity getLevelEntity() {
        return this.levelEntity;
    }

    public void setLevelEntity(LevelEntity levelEntity) {
        this.levelEntity = levelEntity;
    }

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    public UserEntity getAuthor() {
        return this.author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }
}