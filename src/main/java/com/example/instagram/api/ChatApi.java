package com.example.instagram.api;

import com.example.instagram.dto.response.ChatOneResponse;
import com.example.instagram.service.ChatService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/chat")
@Tag(name = "Direct", description = "Chat,Direct for all")
public class ChatApi {
    private final ChatService chatService;

    @PostMapping("send/message")
    public List<ChatOneResponse> sendMessageInDirect(
            @RequestParam String email,
            @RequestParam String message) {
        return chatService.sendMessage(SecurityContextHolder.getContext().getAuthentication().getName(), email, message);
    }

    @GetMapping("find/message")
    public List<ChatOneResponse> findAllMessageInDirect(
            @RequestParam String email) {
        return chatService.findAll(SecurityContextHolder.getContext().getAuthentication().getName(), email);
    }
}