package course.springadvanced.cookeasy.model.view;

import course.springadvanced.cookeasy.model.entity.CategoryEntity;
import course.springadvanced.cookeasy.model.entity.LevelEntity;
import course.springadvanced.cookeasy.model.entity.UserEntity;
import java.time.LocalDate;

public class RecipeDetailsViewModel {
    private Long id;
    private LocalDate createdOn;
    private String title;
    private int hours;
    private int minutes;
    private String description;
    private long likes;
    private long saves;
    private long cooks;
    private long comments;
    private CategoryEntity categoryEntity;
    private LevelEntity levelEntity;
    private UserEntity author;

    public RecipeDetailsViewModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHours() {
        return this.hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getLikes() {
        return this.likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public long getSaves() {
        return this.saves;
    }

    public void setSaves(long saves) {
        this.saves = saves;
    }

    public long getCooks() {
        return this.cooks;
    }

    public void setCooks(long cooks) {
        this.cooks = cooks;
    }

    public long getComments() {
        return this.comments;
    }

    public void setComments(long comments) {
        this.comments = comments;
    }

    public CategoryEntity getCategoryEntity() {
        return this.categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public LevelEntity getLevelEntity() {
        return this.levelEntity;
    }

    public void setLevelEntity(LevelEntity levelEntity) {
        this.levelEntity = levelEntity;
    }

    public UserEntity getAuthor() {
        return this.author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }
}