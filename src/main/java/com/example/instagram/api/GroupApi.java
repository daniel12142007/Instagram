package com.example.instagram.api;

import com.example.instagram.dto.request.GroupRequest;
import com.example.instagram.dto.response.GroupResponse;
import com.example.instagram.service.GroupsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/group")
@Tag(name = "Group", description = "Group for all group members")
public class GroupApi {
    private final GroupsService groupsService;

    @PostMapping("create/group")
    public GroupResponse save(@RequestBody GroupRequest groupRequest) {
        String name = SecurityContextHolder.getContext().getAuthentication() != null
                ? SecurityContextHolder.getContext().getAuthentication().getName()
                : null;
        return groupsService.save(name, groupRequest);
    }
}