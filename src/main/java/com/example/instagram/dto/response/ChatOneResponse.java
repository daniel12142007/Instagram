package com.example.instagram.dto.response;

import lombok.*;

import java.time.LocalDateTime;

//@Builder
@Getter
@Setter
@NoArgsConstructor
public class ChatOneResponse {
    private Long id;
    private String message;
    private String sender;
    private LocalDateTime localDateTime;

    public ChatOneResponse(Long id, String message) {
        this.id = id;
        this.message = message;
    }
}