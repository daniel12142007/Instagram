package com.example.instagram.dto.response;

import com.example.instagram.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// TODO: 10
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JWTResponse {

    private String email;
    private String token;
    private String message;
    private Role role;
}