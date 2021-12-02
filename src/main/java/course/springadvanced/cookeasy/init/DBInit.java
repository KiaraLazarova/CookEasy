package course.springadvanced.cookeasy.init;

import course.springadvanced.cookeasy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {
    private final CategoryService categoryService;
    private final GenderService genderService;
    private final LevelService levelService;
    private final RoleService roleService;
    private final UserService userService;
    private final RecipeService recipeService;
    private final CommentService commentService;

    @Autowired
    public DBInit(CategoryService categoryService, GenderService genderService, LevelService levelService, RoleService roleService, UserService userService, RecipeService recipeService, @Lazy CommentService commentService) {
        this.categoryService = categoryService;
        this.genderService = genderService;
        this.levelService = levelService;
        this.roleService = roleService;
        this.userService = userService;
        this.recipeService = recipeService;
        this.commentService = commentService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.initializeCategories();
        this.genderService.initializeGenders();
        this.levelService.initializeLevels();
        this.roleService.initializeRoles();
        this.userService.initializeUsers();
        this.recipeService.initializeRecipes();
        this.commentService.initializeComments();
    }
}