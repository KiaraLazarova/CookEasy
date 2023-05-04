package com.cookeasy.service;

import com.cookeasy.model.entity.RecipeEntity;
import com.cookeasy.model.service.RecipeAddServiceModel;
import com.cookeasy.model.service.RecipeEditServiceModel;
import com.cookeasy.model.view.RecipeBriefDescriptionViewModel;
import com.cookeasy.model.view.RecipeDetailsViewModel;

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
    boolean canViewRecipe(String callerUsername, Long recipeId);
    List<RecipeBriefDescriptionViewModel> getAddedRecipesBriefDescriptions(String username);
    List<RecipeBriefDescriptionViewModel> getLikedRecipesBriefDescriptions(String username);
    List<RecipeBriefDescriptionViewModel> getSavedRecipesBriefDescriptions(String username);
    List<RecipeBriefDescriptionViewModel> getCookedRecipesBriefDescriptions(String username);
}