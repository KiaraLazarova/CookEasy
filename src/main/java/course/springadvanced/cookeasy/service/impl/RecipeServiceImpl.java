package course.springadvanced.cookeasy.service.impl;

import course.springadvanced.cookeasy.model.entity.*;
import course.springadvanced.cookeasy.model.entity.enumeration.CategoryNameEnum;
import course.springadvanced.cookeasy.model.entity.enumeration.LevelNameEnum;
import course.springadvanced.cookeasy.model.service.RecipeAddServiceModel;
import course.springadvanced.cookeasy.model.service.RecipeEditServiceModel;
import course.springadvanced.cookeasy.model.view.RecipeBriefDescriptionViewModel;
import course.springadvanced.cookeasy.model.view.RecipeDetailsViewModel;
import course.springadvanced.cookeasy.repository.RecipeRepository;
import course.springadvanced.cookeasy.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
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

    @Transactional
    @Override
    public void initializeRecipes() {
        if(this.recipeRepository.count() != 0) return;

        /* Full set up of beginner recipe entity */
        RecipeEntity beginnerRecipe = new RecipeEntity();

        String beginnerRecipeDescription = """
                This recipe is made with a combination of cocoa powder and chocolate chips. The cocoa powder adds the distinct “hot cocoa” flavor, and the chocolate chips melt into the mixture making this drink extra creamy, rich and luxurious. A splash of vanilla extract rounds out all that chocolaty flavor and makes this what I consider the perfect Homemade Hot Chocolate.
                                
                Place the milk of your choice in a saucepan over medium-low heat. Using milk instead of water, makes this hot chocolate extra creamy and flavorful. I prefer whole milk or 2% milk, but you can choose any milk that you choose (I’ve even used unsweetened almond milk).
                Whisk in cocoa powder and sugar, and heat until warm.
                Once the milk is warm, add chocolate chips, whisking until they melt into the milk.
                Add a splash of vanilla extract.
                Serve immediately, topped with your favorite garnishes: marshmallows, whipped cream, chopped chocolate, crushed candy canes or more.
                """;

        this.fullSetUpOfRecipeEntity(
                0,
                30,
                beginnerRecipeDescription,
                "ivan_lazarov",
                "Ivan's Homemade Hot Chocolate",
                CategoryNameEnum.DRINK,
                LevelNameEnum.BEGINNER,
                beginnerRecipe
        );

        /* Full set up of intermediate recipe entity */
        RecipeEntity intermediateRecipe = new RecipeEntity();

        String intermediateRecipeDescription = """
                Ingredients
                                
                2 large free range eggs
                6 tbsp single cream or full cream milk
                a knob of butter
                                
                Method
                
                STEP 1
                Lightly whisk 2 large eggs, 6 tbsp single cream or full cream milk and a pinch of salt together until the mixture has just one consistency.
                                
                STEP 2
                Heat a small non-stick frying pan for a minute or so, then add a knob of butter and let it melt. Don’t allow the butter to brown or it will discolour the eggs.
                                
                STEP 3
                Pour in the egg mixture and let it sit, without stirring, for 20 seconds. Stir with a wooden spoon, lifting and folding it over from the bottom of the pan.
                                
                STEP 4
                Let it sit for another 10 seconds then stir and fold again.
                                
                STEP 5
                Repeat until the eggs are softly set and slightly runny in places. Remove from the heat and leave for a moment to finish cooking.
                                
                STEP 6
                Give a final stir and serve the velvety scramble without delay.
                """;

        this.fullSetUpOfRecipeEntity(
                1,
                0,
                intermediateRecipeDescription,
                "daniel_lazarov",
                "Daniel's Perfect Scrambled Eggs",
                CategoryNameEnum.OTHER,
                LevelNameEnum.INTERMEDIATE,
                intermediateRecipe
        );

        /* Full set up of advanced recipe entity */
        RecipeEntity advancedRecipe = new RecipeEntity();

        String advancedRecipeDescription = """
                INGREDIENTS
                                
                                
                Vanilla Cake:
                281.25 g all-purpose flour
                9.86 ml baking powder
                3.7 ml salt
                170.25 g unsalted butter room temperature
                300 g granulated sugar
                3 large eggs room temperature
                7.39 ml vanilla
                240 g buttermilk or whole milk, room temperature
                                
                Vanilla Buttercream:
                6 large egg whites
                300 g granulated sugar
                454 g unsalted butter room temperature
                9.86 ml vanilla
                                
                Assembly:
                confetti sprinkles
                nonpareils
                                
                INSTRUCTIONS
                                
                Vanilla Cake:
                Preheat oven to 350F. Grease and flour two 8″ cake rounds and line with parchment.
                In a medium bowl, whisk flour, baking powder, and salt until well combined. Set aside.
                Using a stand mixer fitted with a paddle attachment, cream the butter and sugar on med-high until pale and fluffy (about 3mins). Reduce speed and add eggs one at a time fully incorporating after each addition. Add vanilla.\s
                Alternate adding flour mixture and buttermilk, beginning and ending with flour (3 additions of flour and 2 of milk). Fully incorporating after each addition.
                Bake for 30-35 mins or until a toothpick inserted into the center comes out mostly clean.
                Place cakes on wire rack to cool for 10 mins then turn out onto wire rack.
                                
                Vanilla Buttercream:
                Place egg whites and sugar into the bowl of a stand mixer, whisk until combined.*
                Place bowl over a pot with 1-2″ of simmering water and whisk constantly until the mixture is hot and no longer grainy to the touch or reads 160F on a candy thermometer (approx. 3mins)
                Place bowl on your stand mixer and whisk on med-high until the meringue is stiff and cooled (the bowl is no longer warm to the touch (approx. 5-10mins)).
                Switch to paddle attachment. Slowly add cubed butter and mix until smooth.
                Add vanilla and whip until smooth.
                                
                Assembly:
                Place one layer of cake on a cake stand or serving plate. Top with approximately 1 cup of buttercream. Repeat with remaining layer and crumb coat the cake. Chill for 20mins.
                Frost the top and sides of the cake and smooth with a bench scraper.
                If desired, use a decorating comb to give texture to the sides.
                Mix confetti and nonpareils in a small bowl. Press sprinkles gently along the bottom of the cake and sprinkle along the top. For the speckled sides, I grabbed a pinch of sprinkles and tossed them randomly at the sides.
                Pipe rosettes using a 1M tip with remainder of frosting.
                                
                The nutritional information and metric conversions are calculated automatically. I cannot guarantee the accuracy of this data. If this is important to you, please verify with your favorite nutrition calculator and/or metric conversion tool.
                                
                Calories: 694kcal
                Carbohydrates: 69g
                Protein: 6g
                Fat: 44g
                Saturated Fat: 27g
                Cholesterol: 160mg
                Sodium: 219mg
                Potassium: 192mg
                Sugar: 51g
                Vitamin A: 1400IU
                Calcium: 83mg
                Iron: 1.4mg
                """;

        this.fullSetUpOfRecipeEntity(
                3,
                30,
                advancedRecipeDescription,
                "kiara_lazarova",
                "Kiara's Vanilla Cake",
                CategoryNameEnum.DESSERT,
                LevelNameEnum.ADVANCED,
                advancedRecipe
        );
    }

    @Override
    public void addRecipe(String username, RecipeAddServiceModel recipeAddServiceModel) {
        RecipeEntity recipe = this.modelMapper.map(recipeAddServiceModel, RecipeEntity.class);

        /* Full set up of recipe entity */
        this.fullSetUpOfRecipeEntity(
                recipeAddServiceModel.getHours(),
                recipeAddServiceModel.getMinutes(),
                recipeAddServiceModel.getDescription(),
                username,
                recipeAddServiceModel.getTitle(),
                recipeAddServiceModel.getCategoryNameEnum(),
                recipeAddServiceModel.getLevelNameEnum(),
                recipe
        );
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

    @Override
    public void editRecipe(Long id, RecipeEditServiceModel recipeEditServiceModel) {
        RecipeEntity recipe = this.findRecipeById(id);

        /* Partial update of recipe entity => no model mapper used here */
        this.partialUpdateOfRecipeEntity(
                recipeEditServiceModel.getHours(),
                recipeEditServiceModel.getMinutes(),
                recipeEditServiceModel.getDescription(),
                recipeEditServiceModel.getCategoryNameEnum(),
                recipeEditServiceModel.getLevelNameEnum(),
                recipe
        );

        this.recipeRepository.saveAndFlush(recipe);
    }

    @Override
    public void deleteRecipe(Long id) {
        RecipeEntity recipe = this.findRecipeById(id);

        List<UserEntity> users = this.userService.findAllUsers();

        users.forEach(u -> {
            u.getLikedRecipes().remove(recipe);
            u.getSavedRecipes().remove(recipe);
            u.getCookedRecipes().remove(recipe);
        });

        List<CommentEntity> comments = this.commentService.findAllCommentsByRecipeId(id);

        comments.forEach(c -> this.commentService.deleteComment(c.getId()));

        this.recipeRepository.delete(recipe);
    }

    @Override
    public String getRecipeTitle(Long id) {
        return this.findRecipeById(id).getTitle();
    }

    @Override
    public String getRecipeAuthorUsername(Long id) {
        return this.findRecipeById(id).getAuthor().getUsername();
    }

    private RecipeBriefDescriptionViewModel mapToRecipeBriefDescriptionViewModel(RecipeEntity recipe) {
        RecipeBriefDescriptionViewModel recipeBriefDescriptionViewModel =
                this.modelMapper.map(recipe, RecipeBriefDescriptionViewModel.class);
        recipeBriefDescriptionViewModel.setComments(this.commentService.getCountOfRecipeComments(recipe.getId()));

        return recipeBriefDescriptionViewModel;
    }

    private RecipeDetailsViewModel mapToRecipeDetailsViewModel(RecipeEntity recipe) {
        RecipeDetailsViewModel recipeDetailsViewModel = this.modelMapper.map(recipe, RecipeDetailsViewModel.class);
        recipeDetailsViewModel.setHours(recipe.getPreparationTime() / 60);
        recipeDetailsViewModel.setMinutes(recipe.getPreparationTime() % 60);
        recipeDetailsViewModel.setCreatedOn(recipe.getCreatedOn().toLocalDate());
        recipeDetailsViewModel.setComments(this.commentService.getCountOfRecipeComments(recipe.getId()));

        return recipeDetailsViewModel;
    }

    private void partialUpdateOfRecipeEntity(int hours, int minutes,
                                             String description,
                                             CategoryNameEnum categoryNameEnum,
                                             LevelNameEnum levelNameEnum,
                                             RecipeEntity recipe) {
        /* Update recipe entity */
        int preparationTime = hours * 60 + minutes;
        recipe.setPreparationTime(preparationTime);

        recipe.setDescription(description);

        CategoryEntity category = this.categoryService.findCategoryByCategoryName(categoryNameEnum);
        recipe.setCategoryEntity(category);

        LevelEntity level = this.levelService.findLevelByLevelName(levelNameEnum);
        recipe.setLevelEntity(level);
    }

    private void fullSetUpOfRecipeEntity(int hours, int minutes,
                                         String description,
                                         String username,
                                         String title,
                                         CategoryNameEnum categoryNameEnum,
                                         LevelNameEnum levelNameEnum,
                                         RecipeEntity recipe) {
        recipe.setCreatedOn(LocalDateTime.now());

        recipe.setTitle(title);

        UserEntity author = this.userService.findUserByUsername(username);
        recipe.setAuthor(author);

        this.partialUpdateOfRecipeEntity(hours, minutes, description, categoryNameEnum, levelNameEnum, recipe);

        this.recipeRepository.saveAndFlush(recipe);

        author.getAddedRecipes().add(recipe);

        this.userService.saveAndFlushUser(author);
    }
}