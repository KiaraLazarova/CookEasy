package course.springadvanced.cookeasy.service;

import course.springadvanced.cookeasy.model.entity.UserEntity;
import course.springadvanced.cookeasy.model.service.UserProfileEditServiceModel;
import course.springadvanced.cookeasy.model.service.UserRegisterServiceModel;
import course.springadvanced.cookeasy.model.view.UserAdminPanelViewModel;
import course.springadvanced.cookeasy.model.view.UserProfileDetailsViewModel;
import java.util.List;

public interface UserService {
    void initializeUsers();
    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);
    boolean isUsernameOccupied(String username);
    boolean isEmailOccupied(String email);
    UserProfileDetailsViewModel getUserProfileDetails(Long id);
    void editUserProfile(Long id, UserProfileEditServiceModel userProfileEditServiceModel);
    void deleteUserProfile(Long id);
    UserEntity findUserByUsername(String username);
    void saveAndFlushUser(UserEntity user);
    List<UserEntity> findAllUsers();
    long getUserCount();
    List<UserAdminPanelViewModel> getUserAdminPanel();
    void makeUserAdmin(Long id);
    void deleteUserAdminPanel(Long id);
    boolean isProfileOwner(String callerUsername, Long ownerId);
}