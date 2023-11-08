package com.example.instagram.service;

import com.example.instagram.config.JwtUtils;
import com.example.instagram.dto.request.RegisterUserRequest;
import com.example.instagram.dto.response.JWTResponse;
import com.example.instagram.model.User;
import com.example.instagram.model.enums.Role;
import com.example.instagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

// TODO: 11
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public String register(RegisterUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);
        user.setDateNow(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        if (userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("found email:" + request.getEmail() + " email");
        userRepository.save(user);
        jwtUtils.generateToken(user.getEmail());
        return "Registered";
    }

    public JWTResponse login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            throw new RuntimeException("not found:" + email + " email");
        });
        System.out.println(user.getPassword());
        System.out.println(passwordEncoder.encode(password));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        String token = jwtUtils.generateToken(user.getEmail());
        return new JWTResponse(
                user.getEmail(),
                token,
                "login",
                user.getRole()
        );
    }
}