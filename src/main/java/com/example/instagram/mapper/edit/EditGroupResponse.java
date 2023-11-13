package com.example.instagram.mapper.edit;

import com.example.instagram.dto.request.GroupRequest;
import com.example.instagram.model.Groups;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EditGroupResponse {
    public Groups editGroup(GroupRequest request) {
        Groups groups = new Groups();
        groups.setName(request.getName());
        groups.setDescription(request.getDescription());
        return groups;
    }
}