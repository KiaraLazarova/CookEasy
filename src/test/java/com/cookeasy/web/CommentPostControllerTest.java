package com.cookeasy.web;

import com.cookeasy.constant.GlobalTestConstants;
import com.cookeasy.model.entity.*;
import com.cookeasy.model.entity.enumeration.CategoryNameEnum;
import com.cookeasy.model.entity.enumeration.GenderNameEnum;
import com.cookeasy.model.entity.enumeration.LevelNameEnum;
import com.cookeasy.model.entity.enumeration.RoleNameEnum;
import com.cookeasy.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentPostControllerTest {
    private RecipeEntity recipe;
    private final MockMvc mockMvc;
    private final UserRepository userRepository;
    private final GenderRepository genderRepository;
    private final LevelRepository levelRepository;
    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentPostControllerTest(MockMvc mockMvc, UserRepository userRepository, GenderRepository genderRepository, LevelRepository levelRepository, RoleRepository roleRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository, CommentRepository commentRepository) {
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
    }

    @AfterEach
    public void tearDown() {
        List<CommentEntity> comments = this.commentRepository.findAllByRecipeEntity(this.recipe.getId());
        if(!comments.isEmpty()) this.commentRepository.deleteAll(comments);

        this.recipeRepository.delete(this.recipe);

        Optional<UserEntity> user = this.userRepository.findByUsername(GlobalTestConstants.USERNAME);
        user.ifPresent(this.userRepository::delete);
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    public void testRetrieveCommentPageShouldReturnCorrectView() throws Exception {
        this.mockMvc.
                perform(get("/recipes/" + this.recipe.getId() + "/details/comment")).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("recipeId")).
                andExpect(model().attributeExists("recipeTitle")).
                andExpect(model().attributeExists("authorUsername")).
                andExpect(view().name(GlobalTestConstants.COMMENT_POST_PAGE_VIEW_NAME));
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    public void testRetrieveCommentPageShouldPostComment() throws Exception {
        this.mockMvc.
                perform(post("/recipes/" + this.recipe.getId() + "/details/comment").
                        param("content", GlobalTestConstants.COMMENT_CONTENT).
                        with(csrf()).
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)
                ).
                andExpect(status().is3xxRedirection());

        List<CommentEntity> comments = this.commentRepository.findAllByRecipeEntity(this.recipe.getId());

        Assertions.assertFalse(comments.isEmpty());

        CommentEntity comment = comments.get(0);

        Assertions.assertEquals(GlobalTestConstants.COMMENT_CONTENT, comment.getContent());
        Assertions.assertEquals(GlobalTestConstants.USERNAME, comment.getAuthor().getUsername());
        Assertions.assertEquals(GlobalTestConstants.RECIPE_TITLE, comment.getRecipeEntity().getTitle());
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    public void testRetrieveCommentPageShouldRedirectWhenInvalidInputIsPresent() throws Exception {
        this.mockMvc.
                perform(post("/recipes/" + this.recipe.getId() + "/details/comment").
                        param("content", "").
                        with(csrf()).
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)
                ).
                andExpect(status().is3xxRedirection());

        List<CommentEntity> comments = this.commentRepository.findAllByRecipeEntity(this.recipe.getId());

        Assertions.assertTrue(comments.isEmpty());
    }
}