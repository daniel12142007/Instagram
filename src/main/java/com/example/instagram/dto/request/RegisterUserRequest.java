package com.example.instagram.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

// TODO: 9
@Data
@AllArgsConstructor
public class RegisterUserRequest {
    @NotNull(message = "Your fullName it is null!")
    @NotBlank(message = "Your fullName it is blank!")
    @NotEmpty(message = "Your fullName it is empty!")
    private String fullName;
    @NotNull(message = "Your email it is null!")
    @NotBlank(message = "Your email it is blank!")
    @NotEmpty(message = "Your email it is empty!")
    @Pattern(regexp = ".*@gmail.com$")
    private String email;
    @NotNull(message = "Your password it is null!")
    @NotBlank(message = "Your password it is empty!")
    @NotBlank(message = "Your password it is blank!")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "The password does not meet the conditions")
    @Size(min = 8, message = "The line length must be at least 8 characters.")
    private String password;
}