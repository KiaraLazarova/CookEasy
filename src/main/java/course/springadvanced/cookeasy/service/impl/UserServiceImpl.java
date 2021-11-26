package course.springadvanced.cookeasy.service.impl;

import course.springadvanced.cookeasy.model.entity.GenderEntity;
import course.springadvanced.cookeasy.model.entity.LevelEntity;
import course.springadvanced.cookeasy.model.entity.RoleEntity;
import course.springadvanced.cookeasy.model.entity.UserEntity;
import course.springadvanced.cookeasy.model.entity.enumeration.GenderNameEnum;
import course.springadvanced.cookeasy.model.entity.enumeration.LevelNameEnum;
import course.springadvanced.cookeasy.model.entity.enumeration.RoleNameEnum;
import course.springadvanced.cookeasy.model.service.UserProfileEditServiceModel;
import course.springadvanced.cookeasy.model.service.UserRegisterServiceModel;
import course.springadvanced.cookeasy.model.view.UserProfileDetailsViewModel;
import course.springadvanced.cookeasy.repository.UserRepository;
import course.springadvanced.cookeasy.service.GenderService;
import course.springadvanced.cookeasy.service.LevelService;
import course.springadvanced.cookeasy.service.RoleService;
import course.springadvanced.cookeasy.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final GenderService genderService;
    private final LevelService levelService;
    private final CookEasyUserServiceImpl cookEasyUserService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, GenderService genderService, LevelService levelService, CookEasyUserServiceImpl cookEasyUserService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.genderService = genderService;
        this.levelService = levelService;
        this.cookEasyUserService = cookEasyUserService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void initializeUsers() {
        if(this.userRepository.count() != 0) return;

        UserEntity admin = new UserEntity();

        admin.setUsername("kiara_lazarova");

        admin.setFirstName("Kiara");

        admin.setLastName("Lazarova");

        admin.setEmail("kiara_lazarova@gmail.com");

        admin.setPassword(this.passwordEncoder.encode("12345"));

        GenderEntity gender = this.genderService.findGenderByGenderName(GenderNameEnum.FEMALE);
        admin.setGenderEntity(gender);

        LevelEntity level = this.levelService.findLevelByLevelName(LevelNameEnum.ADVANCED);
        admin.setLevelEntity(level);

        RoleEntity userRole = this.roleService.findRoleByRoleName(RoleNameEnum.USER);
        RoleEntity adminRole = this.roleService.findRoleByRoleName(RoleNameEnum.ADMIN);
        admin.setRoles(List.of(userRole, adminRole));

        this.userRepository.saveAndFlush(admin);
    }

    @Override
    public void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel) {
        UserEntity newUser = this.modelMapper.map(userRegisterServiceModel, UserEntity.class);

        newUser.setPassword(this.passwordEncoder.encode(userRegisterServiceModel.getPassword()));

        RoleEntity userRole = this.roleService.findRoleByRoleName(RoleNameEnum.USER);
        newUser.setRoles(List.of(userRole));

        this.updateUserEntityAndUserDetailsObject(
                userRegisterServiceModel.getGenderNameEnum(),
                userRegisterServiceModel.getLevelNameEnum(),
                newUser
        );
    }

    @Override
    public boolean isUsernameOccupied(String username) {
        return this.userRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean isEmailOccupied(String email) {
        return this.userRepository.findByEmail(email).isPresent();
    }

    @Override
    public UserProfileDetailsViewModel getUserProfileDetails(Long id) {
        UserEntity user = this.findUserById(id);

        return this.mapToUserProfileDetailsViewModel(user);
    }

    @Override
    public void editUserProfile(Long id, UserProfileEditServiceModel userProfileEditServiceModel) {
        UserEntity user = this.findUserById(id);

        /* Partial update of user entity => no model mapper used here */
        user.setFirstName(userProfileEditServiceModel.getFirstName());
        user.setLastName(userProfileEditServiceModel.getLastName());

        this.updateUserEntityAndUserDetailsObject(
                userProfileEditServiceModel.getGenderNameEnum(),
                userProfileEditServiceModel.getLevelNameEnum(),
                user
        );
    }

    @Override
    public void deleteUserProfile(Long id) {
        this.userRepository.deleteById(id);

        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        //TODO add error handling - object not found exception
        return this.userRepository.findByUsername(username).get();
    }

    private UserEntity findUserById(Long id) {
        //TODO add error handling - object not found exception
        return this.userRepository.findById(id).get();
    }

    private UserProfileDetailsViewModel mapToUserProfileDetailsViewModel(UserEntity user) {
        return this.modelMapper.map(user, UserProfileDetailsViewModel.class);
    }

    private void updateUserEntityAndUserDetailsObject(GenderNameEnum genderNameEnum, LevelNameEnum levelNameEnum, UserEntity user) {
        /* Update user entity */
        GenderEntity gender = this.genderService.findGenderByGenderName(genderNameEnum);
        user.setGenderEntity(gender);

        LevelEntity level = this.levelService.findLevelByLevelName(levelNameEnum);
        user.setLevelEntity(level);

        this.userRepository.saveAndFlush(user);

        /* Update spring user details object */
        UserDetails principal = this.cookEasyUserService.loadUserByUsername(user.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, user.getPassword(), principal.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}