package com.example.GameImdb.service;

import com.example.GameImdb.model.entity.UserEntity;
import com.example.GameImdb.model.service.UserChangePasswordServiceModel;
import com.example.GameImdb.model.service.UserEditProfileServiceModel;
import com.example.GameImdb.model.service.UserServiceModel;

public interface UserService {


    void registerAndLoginUser(UserServiceModel userServiceModel);
    boolean isUsernameFree(String username);

    UserEntity findByUsername(String username);
    boolean isCurrentUser(String currentUserUsername, String username);
    boolean isCurrentPasswordValid(String password,String username);

    void userChangePasswordServiceModel(UserChangePasswordServiceModel newPassword, String username);

    void editProfile(UserEditProfileServiceModel userEditProfileServiceModel, String username);
}
