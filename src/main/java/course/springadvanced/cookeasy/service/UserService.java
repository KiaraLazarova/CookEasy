package course.springadvanced.cookeasy.service;

import course.springadvanced.cookeasy.model.service.UserRegisterServiceModel;

public interface UserService {
    void initializeUsers();
    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);
    boolean isUsernameOccupied(String username);
    boolean isEmailOccupied(String email);
}