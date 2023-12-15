package com.example.instagram;

import com.example.instagram.dto.request.RegisterUserRequest;
import com.example.instagram.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class InstagramApplication {
    private final AuthService authService;

    public static void main(String[] args) {
        SpringApplication.run(InstagramApplication.class, args);
    }

    @PostConstruct
    public void init() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("string@gmail.com");
        request.setPassword("123");
        request.setFullName("Daniel Ahatzanov");
        authService.register(request);
    }
}