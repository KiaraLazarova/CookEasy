package course.springadvanced.cookeasy.service;

import course.springadvanced.cookeasy.model.service.UserProfileEditServiceModel;
import course.springadvanced.cookeasy.model.service.UserRegisterServiceModel;
import course.springadvanced.cookeasy.model.view.UserProfileDetailsViewModel;

public interface UserService {
    void initializeUsers();
    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);
    boolean isUsernameOccupied(String username);
    boolean isEmailOccupied(String email);
    UserProfileDetailsViewModel getUserProfileDetails(Long id);
    void editUserProfile(Long id, UserProfileEditServiceModel userProfileEditServiceModel);
    void deleteUserProfile(Long id);
}