package com.example.instagram.api;

import com.example.instagram.dto.response.ChatOneResponse;
import com.example.instagram.service.ChatService;
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

    @GetMapping("find/image/message")
    public ResponseEntity<byte[]> getImage(@RequestParam Long id) {
        String myEmail = SecurityContextHolder.getContext().getAuthentication() != null
                ? SecurityContextHolder.getContext().getAuthentication().getName()
                : null;
        return chatService.findByImageForDirect(id, myEmail);
    }

    @GetMapping("find/message")
    public List<ChatOneResponse> findAllMessageInDirect(
            @RequestParam String email) {
        String myEmail = SecurityContextHolder.getContext().getAuthentication() != null
                ? SecurityContextHolder.getContext().getAuthentication().getName()
                : null;
        return chatService.findAll(myEmail, email);
    }

    @PostMapping(value = "send/image/direct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<ChatOneResponse> sendImage(@RequestPart MultipartFile file, @RequestParam String email) throws IOException {
        String myEmail = SecurityContextHolder.getContext().getAuthentication() != null
                ? SecurityContextHolder.getContext().getAuthentication().getName()
                : null;
        return chatService.sendImage(myEmail, email, file);
    }

    @PostMapping("send/message")
    public List<ChatOneResponse> sendMessageInDirect(
            @RequestParam String email,
            @RequestParam String message) {
        String myEmail = SecurityContextHolder.getContext().getAuthentication() != null
                ? SecurityContextHolder.getContext().getAuthentication().getName()
                : null;
        return chatService.sendMessage(myEmail, email, message);
    }
}