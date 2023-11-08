package com.example.instagram.service;

import com.example.instagram.exception.NotFoundEmail;
import com.example.instagram.model.User;
import com.example.instagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CrudService {
    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                {
                    throw new NotFoundEmail(String.valueOf(id));
                }
        );
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                {
                    throw new NotFoundEmail(email);
                }
        );
    }
}