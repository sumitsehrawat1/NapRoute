package com.potodev.NapRoute.dto;

import com.potodev.NapRoute.model.User;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private UUID id;
    private String username;
    private String name;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUserName();
        this.name = user.getName();
    }
}
