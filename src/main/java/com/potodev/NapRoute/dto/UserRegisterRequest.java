package com.potodev.NapRoute.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterRequest {
    @NotBlank(message = "userName cannot be blank")
    private String username;
    private String name;
    @NotBlank(message = "password cannot be blank")
    private String password;
}
