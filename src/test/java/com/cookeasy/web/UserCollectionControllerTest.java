package com.cookeasy.web;

import com.cookeasy.constant.GlobalTestConstants;
import com.cookeasy.model.entity.GenderEntity;
import com.cookeasy.model.entity.LevelEntity;
import com.cookeasy.model.entity.RoleEntity;
import com.cookeasy.model.entity.UserEntity;
import com.cookeasy.model.entity.enumeration.GenderNameEnum;
import com.cookeasy.model.entity.enumeration.LevelNameEnum;
import com.cookeasy.model.entity.enumeration.RoleNameEnum;
import com.cookeasy.repository.GenderRepository;
import com.cookeasy.repository.LevelRepository;
import com.cookeasy.repository.RoleRepository;
import com.cookeasy.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import javax.annotation.PostConstruct;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserCollectionControllerTest {
    private UserEntity user;
    private final MockMvc mockMvc;
    private final UserRepository userRepository;
    private final GenderRepository genderRepository;
    private final LevelRepository levelRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserCollectionControllerTest(MockMvc mockMvc, UserRepository userRepository, GenderRepository genderRepository, LevelRepository levelRepository, RoleRepository roleRepository) {
        this.mockMvc = mockMvc;
        this.userRepository = userRepository;
        this.genderRepository = genderRepository;
        this.levelRepository = levelRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        this.user = new UserEntity();

        this.user.setFirstName(GlobalTestConstants.FIRST_NAME);

        this.user.setLastName(GlobalTestConstants.LAST_NAME);

        this.user.setUsername(GlobalTestConstants.USERNAME);

        this.user.setEmail(GlobalTestConstants.EMAIL);

        this.user.setPassword(GlobalTestConstants.PASSWORD);

        GenderEntity gender = this.genderRepository.findByGenderNameEnum(GenderNameEnum.FEMALE);
        this.user.setGenderEntity(gender);

        LevelEntity level = this.levelRepository.findByLevelNameEnum(LevelNameEnum.ADVANCED);
        this.user.setLevelEntity(level);

        RoleEntity userRole = this.roleRepository.findByRoleNameEnum(RoleNameEnum.USER);
        RoleEntity adminRole = this.roleRepository.findByRoleNameEnum(RoleNameEnum.ADMIN);
        this.user.setRoles(List.of(userRole, adminRole));

        this.userRepository.saveAndFlush(this.user);
    }

    @AfterEach
    public void tearDown() {
        this.userRepository.delete(this.user);
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    public void testRetrieveAddedRecipesPageShouldReturnCorrectView() throws Exception {
        this.mockMvc.
                perform(get("/users/" + this.user.getId() + "/collections/added")).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("viewModels")).
                andExpect(view().name(GlobalTestConstants.ADDED_RECIPES_PAGE_VIEW_NAME));
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    public void testRetrieveLikedRecipesPageShouldReturnCorrectView() throws Exception {
        this.mockMvc.
                perform(get("/users/" + this.user.getId() + "/collections/liked")).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("viewModels")).
                andExpect(view().name(GlobalTestConstants.LIKED_RECIPES_PAGE_VIEW_NAME));
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    public void testRetrieveSavedRecipesPageShouldReturnCorrectView() throws Exception {
        this.mockMvc.
                perform(get("/users/" + this.user.getId() + "/collections/saved")).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("viewModels")).
                andExpect(view().name(GlobalTestConstants.SAVED_RECIPES_PAGE_VIEW_NAME));
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    public void testRetrieveCookedRecipesPageShouldReturnCorrectView() throws Exception {
        this.mockMvc.
                perform(get("/users/" + this.user.getId() + "/collections/cooked")).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("viewModels")).
                andExpect(view().name(GlobalTestConstants.COOKED_RECIPES_PAGE_VIEW_NAME));
    }
}