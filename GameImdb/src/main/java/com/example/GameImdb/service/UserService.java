package com.example.GameImdb.service;

import com.example.GameImdb.model.service.UserServiceModel;

public interface UserService {


    void registerAndLoginUser(UserServiceModel userServiceModel);
    boolean isUsernameFree(String username);
}
