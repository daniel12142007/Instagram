package com.example.instagram.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublicationResponse {
    private Long id;
    private String fullName;
    private String email;
    private LocalDate date;
    private List<Long> idImages;
    private int countLikes;
}