package com.example.instagram.service;

import com.example.instagram.dto.response.ChatOneResponse;
import com.example.instagram.mapper.view.ViewChatResponse;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ChatService {
    private final CrudService crudService;
    private final UserRepository userRepository;
    private final DirectRepository directRepository;
    private final NotificationRepository notificationRepository;
    private final ViewChatResponse viewChatResponse;

    public List<ChatOneResponse> sendMessage(String myEmail, String email, String message) {
        Direct direct = directRepository.findByUserEmail(email);
        User user = crudService.findByEmail(myEmail);
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setDirect(direct);
        notification.setMessage(message);
        notification.setData_send(LocalDateTime.now());
        notificationRepository.save(notification);
        viewChatResponse.chatResponse(notification);
        return findAll(myEmail, email);
    }

    public List<ChatOneResponse> findAll(String myEmail, String email) {
        List<Notification> combinedList = new ArrayList<>();
        combinedList.addAll(notificationRepository.findAllBy(myEmail, email));
        combinedList.addAll(notificationRepository.findAllBy(email, myEmail));
        combinedList.sort(Comparator.comparing(Notification::getData_send));
        Collections.reverse(combinedList);
        return viewChatResponse.chatResponse(combinedList);
    }
}
