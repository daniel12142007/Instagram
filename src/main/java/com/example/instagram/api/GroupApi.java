package com.example.instagram.api;

import com.example.instagram.dto.request.GroupRequest;
import com.example.instagram.dto.response.ChatOneResponse;
import com.example.instagram.dto.response.GroupResponse;
import com.example.instagram.service.GroupsService;
import com.example.instagram.service.ImageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/group")
@Tag(name = "Group", description = "Group for all group members")
public class GroupApi {
    private final GroupsService groupsService;
    private final ImageService imageService;

    @GetMapping("get/message")
    public List<ChatOneResponse> getMessage(@RequestParam Long groupId) {
        String name = SecurityContextHolder.getContext().getAuthentication() != null
                ? SecurityContextHolder.getContext().getAuthentication().getName()
                : null;
        return groupsService.findAllMessageGroup(groupId, name);
    }

    @GetMapping("find/image/message")
    public ResponseEntity<byte[]> getImage(@RequestParam Long id) {
        return imageService.findByImage(id);
    }

    @PostMapping(value = "send/image/group", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<ChatOneResponse> sendImage(@RequestPart MultipartFile image, @RequestParam Long groupId) throws IOException {
        String name = SecurityContextHolder.getContext().getAuthentication() != null
                ? SecurityContextHolder.getContext().getAuthentication().getName()
                : null;
        return groupsService.sendImage(name, groupId, image);
    }

    @PostMapping("create/group")
    public GroupResponse save(@RequestBody GroupRequest groupRequest) {
        String name = SecurityContextHolder.getContext().getAuthentication() != null
                ? SecurityContextHolder.getContext().getAuthentication().getName()
                : null;
        return groupsService.save(name, groupRequest);
    }

    @PostMapping("add/user")
    public GroupResponse addUser(@RequestParam String email, @RequestParam Long groupId) {
        String name = SecurityContextHolder.getContext().getAuthentication() != null
                ? SecurityContextHolder.getContext().getAuthentication().getName()
                : null;
        return groupsService.saveUserInGroup(email, groupId, name);
    }

    @PostMapping("send/message")
    public List<ChatOneResponse> sendMessage(@RequestParam Long groupId, @RequestParam String message) {
        String name = SecurityContextHolder.getContext().getAuthentication() != null
                ? SecurityContextHolder.getContext().getAuthentication().getName()
                : null;
        return groupsService.sendMessage(groupId, name, message);
    }
}