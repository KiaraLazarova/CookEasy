package course.springadvanced.cookeasy.service.impl;

import course.springadvanced.cookeasy.constant.GlobalTestConstants;
import course.springadvanced.cookeasy.model.entity.GenderEntity;
import course.springadvanced.cookeasy.model.entity.LevelEntity;
import course.springadvanced.cookeasy.model.entity.RoleEntity;
import course.springadvanced.cookeasy.model.entity.UserEntity;
import course.springadvanced.cookeasy.model.entity.enumeration.GenderNameEnum;
import course.springadvanced.cookeasy.model.entity.enumeration.LevelNameEnum;
import course.springadvanced.cookeasy.model.entity.enumeration.RoleNameEnum;
import course.springadvanced.cookeasy.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(value = MockitoExtension.class)
public class CookEasyUserServiceImplTest {
    private UserEntity user;
    @Mock
    private UserRepository userRepository;
    private CookEasyUserServiceImpl cookEasyUserService;

    @BeforeEach
    public void init() {
        this.cookEasyUserService = new CookEasyUserServiceImpl(this.userRepository);

        this.user = new UserEntity();

        this.user.setFirstName(GlobalTestConstants.FIRST_NAME);

        this.user.setLastName(GlobalTestConstants.LAST_NAME);

        this.user.setUsername(GlobalTestConstants.USERNAME);

        this.user.setEmail(GlobalTestConstants.EMAIL);

        this.user.setPassword(GlobalTestConstants.PASSWORD);

        GenderEntity gender = new GenderEntity();
        gender.setGenderNameEnum(GenderNameEnum.FEMALE);
        this.user.setGenderEntity(gender);

        LevelEntity level = new LevelEntity();
        level.setLevelNameEnum(LevelNameEnum.ADVANCED);
        this.user.setLevelEntity(level);

        RoleEntity userRole = new RoleEntity();
        userRole.setRoleNameEnum(RoleNameEnum.USER);

        RoleEntity adminRole = new RoleEntity();
        adminRole.setRoleNameEnum(RoleNameEnum.ADMIN);

        this.user.setRoles(List.of(userRole, adminRole));
    }

    @Test
    public void testLoadUserByUsernameThrowUsernameNotFoundEx() {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> this.cookEasyUserService.loadUserByUsername(GlobalTestConstants.INVALID_USERNAME)
        );
    }

    @Test
    public void testLoadUserByUsernameReturnUserDetailsObj() {
        Mockito
                .when(this.userRepository.findByUsername(this.user.getUsername()))
                .thenReturn(Optional.of(this.user));

        UserDetails actualUser = this.cookEasyUserService.loadUserByUsername(GlobalTestConstants.USERNAME);

        String actualRoles = actualUser
                .getAuthorities().
                stream().
                map(GrantedAuthority::getAuthority).
                collect(Collectors.joining(", "));

        Assertions.assertEquals(this.user.getUsername(), actualUser.getUsername());
        Assertions.assertEquals(GlobalTestConstants.EXPECTED_ROLES, actualRoles);
    }
}