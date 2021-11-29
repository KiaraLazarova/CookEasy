package course.springadvanced.cookeasy.service;

import course.springadvanced.cookeasy.model.service.RecipeAddServiceModel;
import course.springadvanced.cookeasy.model.view.RecipeBriefDescriptionViewModel;
import java.util.List;

public interface RecipeService {
    void addRecipe(String username, RecipeAddServiceModel recipeAddServiceModel);
    boolean isTitleOccupied(String title);
    List<RecipeBriefDescriptionViewModel> getRecipesBriefDescriptions(String username);
}