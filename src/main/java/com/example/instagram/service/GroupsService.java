package com.example.instagram.service;

import com.example.instagram.dto.request.GroupRequest;
import com.example.instagram.dto.response.ChatOneResponse;
import com.example.instagram.dto.response.GroupResponse;
import com.example.instagram.exception.AccessIsDenied;
import com.example.instagram.exception.IsUserNotPartOfGroup;
import com.example.instagram.mapper.edit.EditGroupResponse;
import com.example.instagram.mapper.view.ViewChatResponse;
import com.example.instagram.mapper.view.ViewGroupResponse;
import com.example.instagram.model.*;
import com.example.instagram.model.enums.FormatMessage;
import com.example.instagram.repository.GroupsRepository;
import com.example.instagram.repository.ImageRepository;
import com.example.instagram.repository.NotificationRepository;
import com.example.instagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupsService {
    private final GroupsRepository groupsRepository;
    private final EditGroupResponse editChatResponse;
    private final ViewGroupResponse viewGroupResponse;
    private final ViewChatResponse viewChatResponse;
    private final UserRepository userRepository;
    private final CrudService crudService;
    private final NotificationRepository notificationRepository;
    private final ImageRepository imageRepository;

    public List<ChatOneResponse> sendImage(String myEmail, Long groupId, MultipartFile file) throws IOException {
        Groups groups = crudService.findByIdGroup(groupId);
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
        notification.setGroup(groups);
        notification.setMessage(null);
        notification.setData_send(LocalDateTime.now());
        notification.setFormatMessage(FormatMessage.IMAGE);
        notificationRepository.save(notification);
        return findAllMessageGroup(groupId, myEmail);
    }


    public GroupResponse save(String email, GroupRequest request) {
        Groups groups = editChatResponse.editGroup(request);
        User user = crudService.findByEmail(email);
        groups.setCreator(user);
        groups.setUsers(List.of(user));
        groupsRepository.save(groups);
        return viewGroupResponse.groupResponse(groups);
    }

    public GroupResponse saveUserInGroup(String email, Long groupId, String myEmail) {
        Groups groups = crudService.findByIdGroup(groupId);
        groups.getUsers().add(crudService.findByEmail(email));
        if (!isUserInGroup(myEmail, groupId)) {
            throw new IsUserNotPartOfGroup();
        }
        if (!groups.getCreator().getEmail().equals(myEmail)) {
            throw new AccessIsDenied();
        }
        groupsRepository.save(groups);
        return viewGroupResponse.groupResponse(groups);
    }

    public List<ChatOneResponse> sendMessage(Long groupId, String email, String message) {
        Notification notification = new Notification();
        User user = crudService.findByEmail(email);
        Groups groups = crudService.findByIdGroup(groupId);
        if (!isUserInGroup(email, groupId)) {
            throw new IsUserNotPartOfGroup();
        }
        notification.setUser(user);
        notification.setGroup(groups);
        notification.setMessage(message);
        notification.setData_send(LocalDateTime.now());
        notification.setFormatMessage(FormatMessage.TEXT);
        notificationRepository.save(notification);
        return viewChatResponse.chatResponse(notificationRepository.findAllNotificationByGroupId(groups.getId()));
    }

    public List<ChatOneResponse> findAllMessageGroup(Long groupId, String email) {
        if (!isUserInGroup(email, groupId)) {
            throw new IsUserNotPartOfGroup();
        }
        return viewChatResponse.chatResponse(notificationRepository.findAllNotificationByGroupId(groupId));
    }

    public boolean isUserInGroup(String email, Long groupId) {
        User user = crudService.findByEmail(email);
        Groups group = crudService.findByIdGroup(groupId);
        if (user != null && group != null)
            return group.getUsers().contains(user);
        return false;
    }
}