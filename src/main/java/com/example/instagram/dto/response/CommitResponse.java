package com.example.instagram.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommitResponse {
    private Long publicationId;
    private String fullName;
    private String email;
    private String commit;
    private int likeCount;
    private boolean myLike;
    private LocalDateTime localDateTime;
}