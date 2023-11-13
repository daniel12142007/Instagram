package com.example.instagram.mapper.view;

import com.example.instagram.dto.response.GroupResponse;
import com.example.instagram.model.Groups;
import com.example.instagram.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ViewGroupResponse {
    private final ViewUserResponse userResponse;

    public GroupResponse groupResponse(Groups groups) {
        GroupResponse groupResponse = new GroupResponse();
        groupResponse.setName(groups.getName());
        groupResponse.setCreator(userResponse.userResponse(groups.getCreator()));
        groupResponse.setDateCreate(groups.getDateNow());
        groupResponse.setDescription(groups.getDescription());
        groupResponse.setParticipants(userResponse.userResponse(groups.getUsers()));
        return groupResponse;
    }

}
