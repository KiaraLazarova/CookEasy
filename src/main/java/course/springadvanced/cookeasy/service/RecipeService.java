package course.springadvanced.cookeasy.service;

import course.springadvanced.cookeasy.model.service.RecipeAddServiceModel;

public interface RecipeService {
    void addRecipe(String username, RecipeAddServiceModel recipeAddServiceModel);
    boolean isTitleOccupied(String title);
}