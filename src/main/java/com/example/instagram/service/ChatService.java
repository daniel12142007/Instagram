package com.example.instagram.service;

import com.example.instagram.exception.NotFoundEmail;
import com.example.instagram.model.Direct;
import com.example.instagram.model.Notification;
import com.example.instagram.model.User;
import com.example.instagram.repository.DirectRepository;
import com.example.instagram.repository.NotificationRepository;
import com.example.instagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Slf4j
public class ChatService {
    private final CrudService crudService;
    private final UserRepository userRepository;
    private final DirectRepository directRepository;
    private final NotificationRepository notificationRepository;

    public void sudo(String myEmail, String email, String message) {
        Direct direct = directRepository.findByUserEmail(email);
        User user = crudService.findByEmail(myEmail);
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setDirect(direct);
        notification.setMessage(message);
        notification.setData_send(LocalDateTime.now());

    }
}