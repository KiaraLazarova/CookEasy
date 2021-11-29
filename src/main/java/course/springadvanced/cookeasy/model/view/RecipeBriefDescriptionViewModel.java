package course.springadvanced.cookeasy.model.view;

import course.springadvanced.cookeasy.model.entity.CategoryEntity;
import course.springadvanced.cookeasy.model.entity.LevelEntity;

public class RecipeBriefDescriptionViewModel {
    private String title;
    private String description;
    private long likes;
    private long saves;
    private long cooks;
    private long comments;
    private CategoryEntity categoryEntity;
    private LevelEntity levelEntity;

    public RecipeBriefDescriptionViewModel() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}