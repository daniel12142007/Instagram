package com.example.instagram.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

// TODO: 9
@Data
@AllArgsConstructor
public class RegisterUserRequest {
    @NotNull(message = "Your fullName it is null!")
    @NotBlank(message = "Your fullName it is blank!")
    @NotBlank(message = "Your fullName it is empty!")
    private String fullName;
    @NotNull(message = "Your email it is null!")
    @NotBlank(message = "Your email it is blank!")
    @NotBlank(message = "Your email it is empty!")
    private String email;
    @NotNull(message = "Your password it is null!")
    @NotBlank(message = "Your password it is empty!")
    @NotBlank(message = "Your password it is blank!")
    private String password;
}