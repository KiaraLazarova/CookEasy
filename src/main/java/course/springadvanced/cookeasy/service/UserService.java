package course.springadvanced.cookeasy.service;

import course.springadvanced.cookeasy.model.entity.UserEntity;
import course.springadvanced.cookeasy.model.service.UserRegisterServiceModel;
import course.springadvanced.cookeasy.model.view.UserProfileDetailsViewModel;

public interface UserService {
    void initializeUsers();
    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);
    boolean isUsernameOccupied(String username);
    boolean isEmailOccupied(String email);
    UserEntity findUserById(Long id);
    UserProfileDetailsViewModel getUserProfileDetails(Long id);
}