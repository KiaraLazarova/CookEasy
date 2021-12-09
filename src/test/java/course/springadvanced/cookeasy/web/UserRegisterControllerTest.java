package course.springadvanced.cookeasy.web;

import course.springadvanced.cookeasy.constant.GlobalTestConstants;
import course.springadvanced.cookeasy.model.entity.UserEntity;
import course.springadvanced.cookeasy.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegisterControllerTest {
    private final MockMvc mockMvc;
    private final UserRepository userRepository;

    @Autowired
    public UserRegisterControllerTest(MockMvc mockMvc, UserRepository userRepository) {
        this.mockMvc = mockMvc;
        this.userRepository = userRepository;
    }

    @Test
    public void testRetrieveRegisterPageShouldReturnCorrectView() throws Exception {
        this.mockMvc.
                perform(get("/users/register")).
                andExpect(status().isOk()).
                andExpect(view().name(GlobalTestConstants.REGISTER_PAGE_VIEW_NAME));
    }

    @Test
    public void testRegisterAndLoginUserShouldRegisterAndLoginUserWithValidCredentials() throws Exception {
        this.mockMvc.
                perform(post("/users/register").
                        param("firstName", GlobalTestConstants.FIRST_NAME).
                        param("lastName", GlobalTestConstants.LAST_NAME).
                        param("username", GlobalTestConstants.USERNAME).
                        param("email", GlobalTestConstants.EMAIL).
                        param("password", GlobalTestConstants.PASSWORD).
                        param("confirmPassword", GlobalTestConstants.PASSWORD).
                        param("genderNameEnum", GlobalTestConstants.GENDER_NAME_ENUM).
                        param("levelNameEnum", GlobalTestConstants.LEVEL_NAME_ENUM).
                        with(csrf()).
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)
                ).
                andExpect(status().is3xxRedirection());

        Optional<UserEntity> optionalRegisteredAndLoggedUser = this.userRepository.findByUsername(GlobalTestConstants.USERNAME);

        Assertions.assertTrue(optionalRegisteredAndLoggedUser.isPresent());

        UserEntity registeredAndLoggedUser = optionalRegisteredAndLoggedUser.get();

        Assertions.assertEquals(GlobalTestConstants.FIRST_NAME, registeredAndLoggedUser.getFirstName());
        Assertions.assertEquals(GlobalTestConstants.LAST_NAME, registeredAndLoggedUser.getLastName());
        Assertions.assertEquals(GlobalTestConstants.USERNAME, registeredAndLoggedUser.getUsername());
        Assertions.assertEquals(GlobalTestConstants.EMAIL, registeredAndLoggedUser.getEmail());
        Assertions.assertEquals(GlobalTestConstants.GENDER_NAME_ENUM, registeredAndLoggedUser.getGenderEntity().getGenderNameEnum().name());
        Assertions.assertEquals(GlobalTestConstants.LEVEL_NAME_ENUM, registeredAndLoggedUser.getLevelEntity().getLevelNameEnum().name());
    }

    @Test
    public void testRegisterAndLoginUserShouldNotRegisterAndLoginUserWithInvalidUsername() throws Exception {
        this.mockMvc.
                perform(post("/users/register").
                        param("firstName", GlobalTestConstants.FIRST_NAME).
                        param("lastName", GlobalTestConstants.LAST_NAME).
                        param("username", "").
                        param("email", GlobalTestConstants.EMAIL).
                        param("password", GlobalTestConstants.PASSWORD).
                        param("confirmPassword", GlobalTestConstants.PASSWORD).
                        param("genderNameEnum", GlobalTestConstants.GENDER_NAME_ENUM).
                        param("levelNameEnum", "").
                        with(csrf()).
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)
                ).
                andExpect(status().is3xxRedirection());

        Optional<UserEntity> optionalRegisteredAndLoggedUser = this.userRepository.findByUsername("");

        Assertions.assertTrue(optionalRegisteredAndLoggedUser.isEmpty());
    }

    @Test
    public void testRegisterAndLoginUserShouldNotRegisterAndLoginUserWithOccupiedUsernameAndOccupiedEmail() throws Exception {
        this.mockMvc.
                perform(post("/users/register").
                        param("firstName", GlobalTestConstants.FIRST_NAME).
                        param("lastName", GlobalTestConstants.LAST_NAME).
                        param("username", GlobalTestConstants.USERNAME).
                        param("email", GlobalTestConstants.EMAIL).
                        param("password", GlobalTestConstants.PASSWORD).
                        param("confirmPassword", GlobalTestConstants.PASSWORD).
                        param("genderNameEnum", GlobalTestConstants.GENDER_NAME_ENUM).
                        param("levelNameEnum", GlobalTestConstants.LEVEL_NAME_ENUM).
                        with(csrf()).
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)
                ).
                andExpect(status().is3xxRedirection());

        Optional<UserEntity> user = this.userRepository.findByUsername(GlobalTestConstants.USERNAME);
        user.ifPresent(this.userRepository::delete);
    }
}