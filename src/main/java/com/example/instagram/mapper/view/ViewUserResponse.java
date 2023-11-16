package com.example.instagram.mapper.view;

import com.example.instagram.dto.response.UserResponse;
import com.example.instagram.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ViewUserResponse {
    public UserResponse userResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFullName(user.getFullName());
        return userResponse;
    }

    public List<UserResponse> userResponse(List<User> users) {
        List<UserResponse> list = new ArrayList<>();
        for (User user : users) {
            list.add(userResponse(user));
        }
        return list;
    }
}
