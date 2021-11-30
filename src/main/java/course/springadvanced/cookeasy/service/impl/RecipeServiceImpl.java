package course.springadvanced.cookeasy.service.impl;

import course.springadvanced.cookeasy.model.entity.CategoryEntity;
import course.springadvanced.cookeasy.model.entity.LevelEntity;
import course.springadvanced.cookeasy.model.entity.RecipeEntity;
import course.springadvanced.cookeasy.model.entity.UserEntity;
import course.springadvanced.cookeasy.model.service.RecipeAddServiceModel;
import course.springadvanced.cookeasy.model.view.RecipeBriefDescriptionViewModel;
import course.springadvanced.cookeasy.model.view.RecipeDetailsViewModel;
import course.springadvanced.cookeasy.repository.RecipeRepository;
import course.springadvanced.cookeasy.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final CategoryService categoryService;
    private final LevelService levelService;
    private final UserService userService;
    private final CommentService commentService;
    private final ModelMapper modelMapper;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, CategoryService categoryService, LevelService levelService, UserService userService, CommentService commentService, ModelMapper modelMapper) {
        this.recipeRepository = recipeRepository;
        this.categoryService = categoryService;
        this.levelService = levelService;
        this.userService = userService;
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addRecipe(String username, RecipeAddServiceModel recipeAddServiceModel) {
        RecipeEntity recipe = this.modelMapper.map(recipeAddServiceModel, RecipeEntity.class);

        recipe.setCreatedOn(LocalDateTime.now());

        int preparationTime = recipeAddServiceModel.getHours() * 60 + recipeAddServiceModel.getMinutes();
        recipe.setPreparationTime(preparationTime);

        CategoryEntity category = this.categoryService.findCategoryByCategoryName(recipeAddServiceModel.getCategoryNameEnum());
        recipe.setCategoryEntity(category);

        LevelEntity level = this.levelService.findLevelByLevelName(recipeAddServiceModel.getLevelNameEnum());
        recipe.setLevelEntity(level);

        UserEntity author = this.userService.findUserByUsername(username);
        recipe.setAuthor(author);

        this.recipeRepository.saveAndFlush(recipe);
    }

    @Override
    public boolean isTitleOccupied(String title) {
        return this.recipeRepository.findByTitle(title).isPresent();
    }

    @Override
    public List<RecipeBriefDescriptionViewModel> getRecipesBriefDescriptions(String username) {
        UserEntity author = this.userService.findUserByUsername(username);
        Long levelId = author.getLevelEntity().getId();

        return this.recipeRepository.findAllByLevelEntityByOrderByCategoryNameEnumAsc(levelId)
                .stream()
                .map(this::mapToRecipeBriefDescriptionViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public RecipeDetailsViewModel getRecipeDetails(Long id) {
        RecipeEntity recipe = this.findRecipeById(id);

        return this.mapToRecipeDetailsViewModel(recipe);
    }

    @Override
    public void likeRecipe(String username, Long id) {
        RecipeEntity recipe = this.findRecipeById(id);
        recipe.setLikes(recipe.getLikes() + 1);

        this.recipeRepository.saveAndFlush(recipe);

        UserEntity user = this.userService.findUserByUsername(username);
        user.getLikedRecipes().add(recipe);

        this.userService.saveAndFlushUser(user);
    }

    @Override
    public void saveRecipe(String username, Long id) {
        RecipeEntity recipe = this.findRecipeById(id);
        recipe.setSaves(recipe.getSaves() + 1);

        this.recipeRepository.saveAndFlush(recipe);

        UserEntity user = this.userService.findUserByUsername(username);
        user.getSavedRecipes().add(recipe);

        this.userService.saveAndFlushUser(user);
    }

    @Override
    public void cookRecipe(String username, Long id) {
        RecipeEntity recipe = this.findRecipeById(id);
        recipe.setCooks(recipe.getCooks() + 1);

        this.recipeRepository.saveAndFlush(recipe);

        UserEntity user = this.userService.findUserByUsername(username);
        user.getCookedRecipes().add(recipe);

        this.userService.saveAndFlushUser(user);
    }

    private RecipeBriefDescriptionViewModel mapToRecipeBriefDescriptionViewModel(RecipeEntity recipe) {
        RecipeBriefDescriptionViewModel recipeBriefDescriptionViewModel =
                this.modelMapper.map(recipe, RecipeBriefDescriptionViewModel.class);
        recipeBriefDescriptionViewModel.setComments(this.commentService.getCountOfRecipeComments(recipe.getId()));

        return recipeBriefDescriptionViewModel;
    }

    @Override
    public RecipeEntity findRecipeById(Long id) {
        //TODO add error handling - object ot found exception
        return this.recipeRepository.findById(id).get();
    }

    @Override
    public boolean isRecipeLiked(String username, Long id) {
        UserEntity user = this.userService.findUserByUsername(username);
        RecipeEntity recipe = this.findRecipeById(id);

        return user.getLikedRecipes().contains(recipe);
    }

    @Override
    public boolean isRecipeSaved(String username, Long id) {
        UserEntity user = this.userService.findUserByUsername(username);
        RecipeEntity recipe = this.findRecipeById(id);

        return user.getSavedRecipes().contains(recipe);
    }

    @Override
    public boolean isRecipeCooked(String username, Long id) {
        UserEntity user = this.userService.findUserByUsername(username);
        RecipeEntity recipe = this.findRecipeById(id);

        return user.getCookedRecipes().contains(recipe);
    }

    private RecipeDetailsViewModel mapToRecipeDetailsViewModel(RecipeEntity recipe) {
        RecipeDetailsViewModel recipeDetailsViewModel = this.modelMapper.map(recipe, RecipeDetailsViewModel.class);
        recipeDetailsViewModel.setHours(recipe.getPreparationTime() / 60);
        recipeDetailsViewModel.setMinutes(recipe.getPreparationTime() % 60);
        recipeDetailsViewModel.setCreatedOn(recipe.getCreatedOn().toLocalDate());
        recipeDetailsViewModel.setComments(this.commentService.getCountOfRecipeComments(recipe.getId()));

        return recipeDetailsViewModel;
    }
}