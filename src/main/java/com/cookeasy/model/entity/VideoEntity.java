package com.cookeasy.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "videos")
public class VideoEntity extends BaseEntity {
    private String title;
    private String url;
    private UserEntity author;
    private RecipeEntity recipeEntity;

    public VideoEntity() {
    }

    @Column(name = "title", nullable = false, unique = true)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "url", nullable = false, unique = true)
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
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