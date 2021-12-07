package course.springadvanced.cookeasy.service;

import course.springadvanced.cookeasy.model.entity.RecipeEntity;
import course.springadvanced.cookeasy.model.service.RecipeAddServiceModel;
import course.springadvanced.cookeasy.model.service.RecipeEditServiceModel;
import course.springadvanced.cookeasy.model.view.RecipeBriefDescriptionViewModel;
import course.springadvanced.cookeasy.model.view.RecipeDetailsViewModel;
import java.util.List;

public interface RecipeService {
    void initializeRecipes();
    void addRecipe(String username, RecipeAddServiceModel recipeAddServiceModel);
    boolean isTitleOccupied(String title);
    List<RecipeBriefDescriptionViewModel> getRecipesBriefDescriptions(String username);
    RecipeDetailsViewModel getRecipeDetails(Long id);
    void likeRecipe(String username, Long id);
    void saveRecipe(String username, Long id);
    void cookRecipe(String username, Long id);
    RecipeEntity findRecipeById(Long id);
    boolean isRecipeLiked(String username, Long id);
    boolean isRecipeSaved(String username, Long id);
    boolean isRecipeCooked(String username, Long id);
    void editRecipe(Long id, RecipeEditServiceModel recipeEditServiceModel);
    void deleteRecipe(Long id);
    String getRecipeTitle(Long id);
    String getRecipeAuthorUsername(Long id);
    long getRecipeCount();
    boolean isRecipeOwner(String callerUsername, Long recipeId);
}