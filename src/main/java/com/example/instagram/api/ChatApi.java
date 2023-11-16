package com.example.instagram.api;

import com.example.instagram.dto.response.ChatOneResponse;
import com.example.instagram.service.ChatService;
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
@RequestMapping("api/v1/chat")
@Tag(name = "Direct", description = "Chat,Direct for all")
public class ChatApi {
    private final ChatService chatService;
    private final ImageService imageService;

    @GetMapping("find/image/message")
    public ResponseEntity<byte[]> getImage(@RequestParam Long id) {
        return imageService.findByImage(id);
    }

    @GetMapping("find/message")
    public List<ChatOneResponse> findAllMessageInDirect(
            @RequestParam String email) {
        return chatService.findAll(SecurityContextHolder.getContext().getAuthentication().getName(), email);
    }

    @PostMapping(value = "send/image/direct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<ChatOneResponse> sendImage(@RequestPart MultipartFile file, @RequestParam String email) throws IOException {
        return chatService.sendImage(SecurityContextHolder.getContext().getAuthentication().getName(), email, file);
    }

    @PostMapping("send/message")
    public List<ChatOneResponse> sendMessageInDirect(
            @RequestParam String email,
            @RequestParam String message) {
        return chatService.sendMessage(SecurityContextHolder.getContext().getAuthentication().getName(), email, message);
    }
}