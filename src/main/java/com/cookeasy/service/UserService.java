package com.cookeasy.service;

import com.cookeasy.model.entity.UserEntity;
import com.cookeasy.model.service.UserProfileEditServiceModel;
import com.cookeasy.model.service.UserRegisterServiceModel;
import com.cookeasy.model.view.UserAdminPanelViewModel;
import com.cookeasy.model.view.UserProfileDetailsViewModel;

import java.util.List;
import java.util.Optional;

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
    Optional<UserEntity> findOptionalUserByUsername(String username);
}