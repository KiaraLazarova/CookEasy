package course.springadvanced.cookeasy.service.impl;

import course.springadvanced.cookeasy.model.entity.*;
import course.springadvanced.cookeasy.model.entity.enumeration.GenderNameEnum;
import course.springadvanced.cookeasy.model.entity.enumeration.LevelNameEnum;
import course.springadvanced.cookeasy.model.entity.enumeration.RoleNameEnum;
import course.springadvanced.cookeasy.model.service.UserProfileEditServiceModel;
import course.springadvanced.cookeasy.model.service.UserRegisterServiceModel;
import course.springadvanced.cookeasy.model.view.UserProfileDetailsViewModel;
import course.springadvanced.cookeasy.repository.UserRepository;
import course.springadvanced.cookeasy.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
    private final CommentService commentService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, GenderService genderService, LevelService levelService, CookEasyUserServiceImpl cookEasyUserService, @Lazy CommentService commentService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.genderService = genderService;
        this.levelService = levelService;
        this.cookEasyUserService = cookEasyUserService;
        this.commentService = commentService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void initializeUsers() {
        if(this.userRepository.count() != 0) return;

        this.initializeUser(
                "ivan_lazarov",
                "Ivan",
                "Lazarov",
                "ivan_lazarov@gmail.com",
                "12345",
                GenderNameEnum.MALE,
                LevelNameEnum.BEGINNER
        );

        this.initializeUser(
                "daniel_lazarov",
                "Daniel",
                "Lazarov",
                "daniel_lazarov@gmail.com",
                "12345",
                GenderNameEnum.MALE,
                LevelNameEnum.INTERMEDIATE
        );

        this.initializeAdmin(
                "kiara_lazarova",
                "Kiara",
                "Lazarova",
                "kiara_lazarova@gmail.com",
                "12345",
                GenderNameEnum.FEMALE,
                LevelNameEnum.ADVANCED
        );
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
        UserEntity user = this.findUserById(id);

        user.getLikedRecipes().forEach(r -> r.setLikes(r.getLikes() - 1));
        user.getSavedRecipes().forEach(r -> r.setSaves(r.getSaves() - 1));
        user.getCookedRecipes().forEach(r -> r.setCooks(r.getCooks() - 1));

        user.setLikedRecipes(null);
        user.setSavedRecipes(null);
        user.setCookedRecipes(null);

        List<CommentEntity> comments = this.commentService.findAllCommentsByAuthorId(id);

        comments.forEach(c -> this.commentService.deleteComment(c.getId()));

        this.userRepository.delete(user);

        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        //TODO add error handling - object not found exception
        return this.userRepository.findByUsername(username).get();
    }

    @Override
    public void saveAndFlushUser(UserEntity user) {
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return this.userRepository.findAll();
    }

    private UserEntity initializeUser(String username, String firstName, String lastName, String email, String password,
                                GenderNameEnum genderNameEnum, LevelNameEnum levelNameEnum) {
        UserEntity user = new UserEntity();

        user.setUsername(username);

        user.setFirstName(firstName);

        user.setLastName(lastName);

        user.setEmail(email);

        user.setPassword(this.passwordEncoder.encode(password));

        GenderEntity gender = this.genderService.findGenderByGenderName(genderNameEnum);
        user.setGenderEntity(gender);

        LevelEntity level = this.levelService.findLevelByLevelName(levelNameEnum);
        user.setLevelEntity(level);

        RoleEntity userRole = this.roleService.findRoleByRoleName(RoleNameEnum.USER);
        user.getRoles().add(userRole);

        return this.userRepository.saveAndFlush(user);
    }

    private void initializeAdmin(String username, String firstName, String lastName, String email, String password,
                                 GenderNameEnum genderNameEnum, LevelNameEnum levelNameEnum) {
        UserEntity user = this.initializeUser(username, firstName, lastName, email, password, genderNameEnum, levelNameEnum);

        RoleEntity adminRole = this.roleService.findRoleByRoleName(RoleNameEnum.ADMIN);
        user.getRoles().add(adminRole);

        this.userRepository.saveAndFlush(user);
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