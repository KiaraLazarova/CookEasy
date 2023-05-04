package com.cookeasy.web;

import com.cookeasy.constant.GlobalTestConstants;
import com.cookeasy.model.entity.*;
import com.cookeasy.model.entity.enumeration.GenderNameEnum;
import com.cookeasy.model.entity.enumeration.LevelNameEnum;
import com.cookeasy.model.entity.enumeration.RoleNameEnum;
import com.cookeasy.repository.*;
import com.cookeasy.model.entity.enumeration.CategoryNameEnum;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentDisplayRestControllerTest {
    private RecipeEntity recipe;
    private CommentEntity comment;
    private final MockMvc mockMvc;
    private final UserRepository userRepository;
    private final GenderRepository genderRepository;
    private final LevelRepository levelRepository;
    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentDisplayRestControllerTest(MockMvc mockMvc, UserRepository userRepository, GenderRepository genderRepository, LevelRepository levelRepository, RoleRepository roleRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository, CommentRepository commentRepository) {
        this.mockMvc = mockMvc;
        this.userRepository = userRepository;
        this.genderRepository = genderRepository;
        this.levelRepository = levelRepository;
        this.roleRepository = roleRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.commentRepository = commentRepository;
    }

    @PostConstruct
    public void init() {
        // User entity set up
        UserEntity user = new UserEntity();

        user.setFirstName(GlobalTestConstants.FIRST_NAME);

        user.setLastName(GlobalTestConstants.LAST_NAME);

        user.setUsername(GlobalTestConstants.USERNAME);

        user.setEmail(GlobalTestConstants.EMAIL);

        user.setPassword(GlobalTestConstants.PASSWORD);

        GenderEntity gender = this.genderRepository.findByGenderNameEnum(GenderNameEnum.FEMALE);
        user.setGenderEntity(gender);

        LevelEntity level = this.levelRepository.findByLevelNameEnum(LevelNameEnum.ADVANCED);
        user.setLevelEntity(level);

        RoleEntity userRole = this.roleRepository.findByRoleNameEnum(RoleNameEnum.USER);
        RoleEntity adminRole = this.roleRepository.findByRoleNameEnum(RoleNameEnum.ADMIN);
        user.setRoles(List.of(userRole, adminRole));

        this.userRepository.saveAndFlush(user);

        // Recipe entity set up
        this.recipe = new RecipeEntity();

        this.recipe.setTitle(GlobalTestConstants.RECIPE_TITLE);

        this.recipe.setDescription(GlobalTestConstants.RECIPE_DESCRIPTION);

        this.recipe.setCreatedOn(GlobalTestConstants.RECIPE_CREATED_ON);

        this.recipe.setPreparationTime(GlobalTestConstants.RECIPE_PREPARATION_TIME);

        this.recipe.setAuthor(user);

        this.recipe.setLevelEntity(user.getLevelEntity());

        CategoryEntity category = this.categoryRepository.findByCategoryNameEnum(CategoryNameEnum.DESSERT);
        this.recipe.setCategoryEntity(category);

        this.recipeRepository.saveAndFlush(this.recipe);

        // Comment entity set up
        this.comment = new CommentEntity();

        this.comment.setCreatedOn(GlobalTestConstants.COMMENT_CREATED_ON);

        this.comment.setContent(GlobalTestConstants.COMMENT_CONTENT);

        this.comment.setArchived(GlobalTestConstants.COMMENT_ARCHIVED);

        this.comment.setApproved(GlobalTestConstants.COMMENT_APPROVED);

        this.comment.setRecipeEntity(this.recipe);

        this.comment.setAuthor(user);

        this.commentRepository.saveAndFlush(this.comment);
    }

    @AfterEach
    public void tearDown() {
        this.commentRepository.delete(this.comment);

        this.recipeRepository.delete(this.recipe);

        Optional<UserEntity> user = this.userRepository.findByUsername(GlobalTestConstants.USERNAME);
        user.ifPresent(this.userRepository::delete);
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    public void testRetrieveComments() throws Exception {
        this.mockMvc.
                perform(get("/api/" + this.recipe.getId() + "/comments")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(1))).
                andExpect(jsonPath("$.[0].content", is(GlobalTestConstants.COMMENT_CONTENT)));
    }
}