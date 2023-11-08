package com.example.instagram.api;

import com.example.instagram.dto.request.RegisterUserRequest;
import com.example.instagram.dto.response.JWTResponse;
import com.example.instagram.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.validation.constraints.*;

// TODO: 12
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
@Tag(name = "Authentication", description = "Authentication for all")
public class MyApi {
    private final AuthService authService;

    @PostMapping("/register")
    @PermitAll
    public String register(
            @RequestBody @Valid RegisterUserRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    @PermitAll
    public JWTResponse login(@RequestParam
                             @Pattern(regexp = ".*@gmail.com$")
                             @NotEmpty(message = "It is empty")
                             String email,
                             @RequestParam
                             @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "The password does not meet the conditions")
                             @Size(min = 8, message = "The line length must be at least 8 characters.")
                             @NotEmpty(message = "It is empty")
                             String password) {
        return authService.login(email, password);
    }
}