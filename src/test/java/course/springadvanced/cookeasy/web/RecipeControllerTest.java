package course.springadvanced.cookeasy.web;

import course.springadvanced.cookeasy.constant.GlobalTestConstants;
import course.springadvanced.cookeasy.model.entity.GenderEntity;
import course.springadvanced.cookeasy.model.entity.LevelEntity;
import course.springadvanced.cookeasy.model.entity.RoleEntity;
import course.springadvanced.cookeasy.model.entity.UserEntity;
import course.springadvanced.cookeasy.model.entity.enumeration.GenderNameEnum;
import course.springadvanced.cookeasy.model.entity.enumeration.LevelNameEnum;
import course.springadvanced.cookeasy.model.entity.enumeration.RoleNameEnum;
import course.springadvanced.cookeasy.repository.GenderRepository;
import course.springadvanced.cookeasy.repository.LevelRepository;
import course.springadvanced.cookeasy.repository.RoleRepository;
import course.springadvanced.cookeasy.repository.UserRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RecipeControllerTest {
    private final MockMvc mockMvc;
    private final UserRepository userRepository;
    private final GenderRepository genderRepository;
    private final LevelRepository levelRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public RecipeControllerTest(MockMvc mockMvc, UserRepository userRepository, GenderRepository genderRepository, LevelRepository levelRepository, RoleRepository roleRepository) {
        this.mockMvc = mockMvc;
        this.userRepository = userRepository;
        this.genderRepository = genderRepository;
        this.levelRepository = levelRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
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
    }

    @AfterEach
    public void tearDown() {
        Optional<UserEntity> user = this.userRepository.findByUsername(GlobalTestConstants.USERNAME);
        user.ifPresent(this.userRepository::delete);
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    public void testRetrieveRecipesPageShouldReturnCorrectView() throws Exception {
        this.mockMvc.
                perform(get("/recipes")).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("viewModels")).
                andExpect(view().name(GlobalTestConstants.RECIPES_PAGE_VIEW_NAME));
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    public void testRetrieveRecipeAddPageShouldReturnCorrectView() throws Exception {
        this.mockMvc.
                perform(get("/recipes/add")).
                andExpect(status().isOk()).
                andExpect(view().name(GlobalTestConstants.RECIPE_ADD_PAGE_VIEW_NAME));
    }
}