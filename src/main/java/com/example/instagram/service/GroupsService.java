package com.example.instagram.service;

import com.example.instagram.dto.request.GroupRequest;
import com.example.instagram.dto.response.GroupResponse;
import com.example.instagram.mapper.edit.EditGroupResponse;
import com.example.instagram.mapper.view.ViewChatResponse;
import com.example.instagram.mapper.view.ViewGroupResponse;
import com.example.instagram.model.Groups;
import com.example.instagram.model.User;
import com.example.instagram.repository.GroupsRepository;
import com.example.instagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupsService {
    private final GroupsRepository groupsRepository;
    private final EditGroupResponse editChatResponse;
    private final ViewGroupResponse viewGroupResponse;
    private final UserRepository userRepository;
    private final CrudService crudService;


    public GroupResponse save(String email, GroupRequest request) {
        Groups groups = editChatResponse.editGroup(request);
        User user = crudService.findByEmail(email);
        groups.setCreator(user);
        groups.setUsers(List.of(user));
        groupsRepository.save(groups);
        return viewGroupResponse.groupResponse(groups);
    }
}