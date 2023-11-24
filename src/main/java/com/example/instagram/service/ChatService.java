package com.example.instagram.service;

import com.example.instagram.dto.response.ChatOneResponse;
import com.example.instagram.exception.AccessIsDenied;
import com.example.instagram.mapper.view.ViewChatResponse;
import com.example.instagram.model.Direct;
import com.example.instagram.model.Image;
import com.example.instagram.model.Notification;
import com.example.instagram.model.User;
import com.example.instagram.model.enums.FormatMessage;
import com.example.instagram.repository.DirectRepository;
import com.example.instagram.repository.ImageRepository;
import com.example.instagram.repository.NotificationRepository;
import com.example.instagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private final ImageRepository imageRepository;

    public ResponseEntity<byte[]> findByImageForDirect(Long id, String email) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found with id: " + id));
        if (image == null) {
            throw new NullPointerException("image it is null");
        }
        Notification notification = image.getNotification();
        if (notification.getUser().getEmail().equals(email) || notification.getDirect().getUser().getEmail().equals(email)) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(image.getData(), headers, HttpStatus.OK);
        }
        throw new AccessIsDenied();
    }

    public List<ChatOneResponse> sendMessage(String myEmail, String email, String message) {
        Direct direct = directRepository.findByUserEmail(email);
        User user = crudService.findByEmail(myEmail);
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setDirect(direct);
        notification.setMessage(message);
        notification.setData_send(LocalDateTime.now());
        notification.setFormatMessage(FormatMessage.TEXT);
        notificationRepository.save(notification);
        return findAll(myEmail, email);
    }

    public List<ChatOneResponse> sendImage(String myEmail, String email, MultipartFile file) throws IOException {
        Direct direct = directRepository.findByUserEmail(email);
        User user = crudService.findByEmail(myEmail);
        if (file == null)
            throw new NullPointerException("image it is null!");
        Image image = new Image();
        image.setData(file.getBytes());
        image.setData(file.getBytes());
        image.setFileName(file.getOriginalFilename());
        Notification notification = new Notification();
        image.setNotification(notification);
        notification.setUser(user);
        notification.setImage(image);
        notification.setDirect(direct);
        notification.setMessage(null);
        notification.setData_send(LocalDateTime.now());
        notification.setFormatMessage(FormatMessage.IMAGE);
        notificationRepository.save(notification);
        return findAll(myEmail, email);
    }

    public List<ChatOneResponse> findAll(String myEmail, String email) {
        List<Notification> combinedList = new ArrayList<>();
        combinedList.addAll(notificationRepository.findAllByDirectNotification(myEmail, email));
        combinedList.addAll(notificationRepository.findAllByDirectNotification(email, myEmail));
        combinedList.sort(Comparator.comparing(Notification::getData_send));
        return viewChatResponse.chatResponse(combinedList);
    }
}