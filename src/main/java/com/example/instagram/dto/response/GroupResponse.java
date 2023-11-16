package com.example.instagram.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponse {
    private String name;
    private String description;
    private LocalDateTime dateCreate;
    private UserResponse creator;
    private List<UserResponse> participants;
}