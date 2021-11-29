package course.springadvanced.cookeasy.service.impl;

import course.springadvanced.cookeasy.model.entity.CategoryEntity;
import course.springadvanced.cookeasy.model.entity.LevelEntity;
import course.springadvanced.cookeasy.model.entity.RecipeEntity;
import course.springadvanced.cookeasy.model.entity.UserEntity;
import course.springadvanced.cookeasy.model.service.RecipeAddServiceModel;
import course.springadvanced.cookeasy.model.view.RecipeBriefDescriptionViewModel;
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

        recipe.setLikes(0);

        recipe.setSaves(0);

        recipe.setCooks(0);

        CategoryEntity category = this.categoryService.findCategoryByCategoryName(recipeAddServiceModel.getCategoryNameEnum());
        recipe.setCategoryEntity(category);

        LevelEntity level = this.levelService.findLevelByLevelName(recipeAddServiceModel.getLevelNameEnum());
        recipe.setLevelEntity(level);

        UserEntity author = this.userService.findUserByUsername(username);
        recipe.setAuthor(author);

        this.recipeRepository.saveAndFlush(recipe);

        author.getAddedRecipes().add(recipe);
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

    private RecipeBriefDescriptionViewModel mapToRecipeBriefDescriptionViewModel(RecipeEntity recipe) {
        RecipeBriefDescriptionViewModel recipeBriefDescriptionViewModel =
                this.modelMapper.map(recipe, RecipeBriefDescriptionViewModel.class);
        recipeBriefDescriptionViewModel.setComments(this.commentService.getCountOfRecipeComments(recipe.getId()));

        return recipeBriefDescriptionViewModel;
    }
}