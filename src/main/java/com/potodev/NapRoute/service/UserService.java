package com.potodev.NapRoute.service;

import com.potodev.NapRoute.dto.UserRegisterRequest;
import com.potodev.NapRoute.model.User;

public interface UserService {
    void registerUser(UserRegisterRequest request);
    User authenticateUser(String username, String rawPassword);
    User getUserByUserName(String username);
}
