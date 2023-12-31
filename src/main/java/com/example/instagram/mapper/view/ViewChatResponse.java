package com.example.instagram.mapper.view;

import com.example.instagram.dto.response.ChatOneResponse;
import com.example.instagram.dto.response.GroupResponse;
import com.example.instagram.dto.response.UserResponse;
import com.example.instagram.model.Groups;
import com.example.instagram.model.Notification;
import com.example.instagram.model.User;
import com.example.instagram.model.enums.FormatMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ViewChatResponse {
    public ChatOneResponse chatResponse(Notification notification) {
        ChatOneResponse chatOneResponse = new ChatOneResponse();
        chatOneResponse.setId(notification.getId());
        chatOneResponse.setMessage(notification.getMessage());
        chatOneResponse.setLocalDateTime(notification.getData_send());
        if (notification.getFormatMessage() == FormatMessage.IMAGE)
            chatOneResponse.setImage_id(notification.getImage().getId());
        chatOneResponse.setSender(notification.getUser() == null ? null : notification.getUser().getFullName());
        return chatOneResponse;
    }

    public List<ChatOneResponse> chatResponse(List<Notification> notifications) {
        List<ChatOneResponse> list = new ArrayList<>();
        for (Notification notification : notifications) {
            list.add(chatResponse(notification));
        }
        return list;
    }
}