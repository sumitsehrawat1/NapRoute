package com.potodev.NapRoute.controller;

import com.potodev.NapRoute.dto.LoginRequest;
import com.potodev.NapRoute.dto.LoginResponse;
import com.potodev.NapRoute.dto.UserRegisterRequest;
import com.potodev.NapRoute.dto.UserResponse;
import com.potodev.NapRoute.model.User;
import com.potodev.NapRoute.service.UserService;
import com.potodev.NapRoute.util.JwtUtil;
import com.potodev.NapRoute.util.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterRequest request) {
        userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        User user = userService.authenticateUser(request.getUsername(), request.getPassword());
        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMe() {
        User user = SecurityUtils.getCurrentUser();
        return ResponseEntity.ok(new UserResponse(userService.getUserByUserName(user.getUserName())));
    }
}
