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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserProfileControllerTest {
    private UserEntity user;
    private final MockMvc mockMvc;
    private final UserRepository userRepository;
    private final GenderRepository genderRepository;
    private final LevelRepository levelRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserProfileControllerTest(MockMvc mockMvc, UserRepository userRepository, GenderRepository genderRepository, LevelRepository levelRepository, RoleRepository roleRepository) {
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
        Optional<UserEntity> user = this.userRepository.findByUsername(GlobalTestConstants.USERNAME);
        user.ifPresent(this.userRepository::delete);
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    public void testRetrieveProfilePageShouldReturnCorrectView() throws Exception {
        this.mockMvc.
                perform(get("/users/" + this.user.getId() + "/profile")).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("viewModel")).
                andExpect(view().name(GlobalTestConstants.USER_PROFILE_PAGE_VIEW_NAME));
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    public void testRetrieveProfileEditPageShouldReturnCorrectView() throws Exception {
        this.mockMvc.
                perform(get("/users/" + this.user.getId() + "/profile/edit")).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("bindingModel")).
                andExpect(view().name(GlobalTestConstants.USER_PROFILE_EDIT_PAGE_VIEW_NAME));
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    public void testUpdateUserProfileShouldUpdateUser() throws Exception {
        this.mockMvc.
                perform(patch("/users/" + this.user.getId() + "/profile/edit").
                        param("fistName", GlobalTestConstants.FIRST_NAME).
                        param("lastName", GlobalTestConstants.UPDATED_LAST_NAME).
                        param("genderNameEnum", GlobalTestConstants.GENDER_NAME_ENUM).
                        param("levelNameEnum", GlobalTestConstants.LEVEL_NAME_ENUM).
                        with(csrf()).
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)
                ).
                andExpect(status().is3xxRedirection());

        Optional<UserEntity> optionalUpdatedUser = this.userRepository.findByUsername(GlobalTestConstants.USERNAME);

        Assertions.assertTrue(optionalUpdatedUser.isPresent());

        UserEntity updatedUser = optionalUpdatedUser.get();

        Assertions.assertEquals(GlobalTestConstants.UPDATED_LAST_NAME, updatedUser.getLastName());
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    public void testUpdateUserProfileShouldRedirectWhenInvalidInputIsPresent() throws Exception {
        this.mockMvc.
                perform(patch("/users/" + this.user.getId() + "/profile/edit").
                        param("fistName", GlobalTestConstants.FIRST_NAME).
                        param("lastName", GlobalTestConstants.INVALID_LAST_NAME).
                        param("genderNameEnum", GlobalTestConstants.GENDER_NAME_ENUM).
                        param("levelNameEnum", GlobalTestConstants.LEVEL_NAME_ENUM).
                        with(csrf()).
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)
                ).
                andExpect(status().is3xxRedirection());

        Optional<UserEntity> optionalNotUpdatedUser = this.userRepository.findByUsername(GlobalTestConstants.USERNAME);

        Assertions.assertTrue(optionalNotUpdatedUser.isPresent());

        UserEntity notUpdatedUser = optionalNotUpdatedUser.get();

        Assertions.assertNotEquals(GlobalTestConstants.INVALID_LAST_NAME, notUpdatedUser.getLastName());
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    public void testDeleteUserProfileShouldDeleteUser() throws Exception {
        this.mockMvc.
                perform(delete("/users/" + this.user.getId() + "/profile/delete").
                        with(csrf()).
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)
                ).
                andExpect(status().is3xxRedirection());

        Assertions.assertTrue(this.userRepository.findByUsername(GlobalTestConstants.USERNAME).isEmpty());
    }
}