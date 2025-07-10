package com.potodev.NapRoute.service.impl;

import static com.potodev.NapRoute.exceptions.NapErrorCode.BAD_CREDENTIALS;
import static com.potodev.NapRoute.exceptions.NapErrorCode.USERNAME_ALREADY_TAKEN;
import static com.potodev.NapRoute.exceptions.NapErrorCode.USERNAME_NOT_FOUND;

import com.potodev.NapRoute.dto.UserRegisterRequest;
import com.potodev.NapRoute.exceptions.NapException;
import com.potodev.NapRoute.model.User;
import com.potodev.NapRoute.repository.UserRepository;
import com.potodev.NapRoute.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserRegisterRequest request) {
        if (userRepository.existsByUserName(request.getUsername())) {
            throw NapException.of(USERNAME_ALREADY_TAKEN);
        }

        User user = new User();
        user.setUserName(request.getUsername());
        user.setName(request.getName());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User authenticateUser(String username, String rawPassword) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> NapException.of(USERNAME_NOT_FOUND));

        if (!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
            throw NapException.of(BAD_CREDENTIALS);
        }

        return user;
    }

    @Override
    public User getUserByUserName(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> NapException.of(USERNAME_NOT_FOUND));
    }
}
