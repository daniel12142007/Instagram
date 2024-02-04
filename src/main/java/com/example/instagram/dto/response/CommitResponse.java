package com.example.instagram.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommitResponse {
    private Long publicationId;
    private Long commitId;
    private String fullName;
    private String email;
    private String commit;
    private long likeCount;
    private boolean myLike;
    private LocalDateTime localDateTime;
    private List<CommitResponse> commitResponses;

    public CommitResponse(Long publicationId,
                          Long commitId,
                          String fullName,
                          String email,
                          String commit,
                          long likeCount,
                          boolean myLike,
                          LocalDateTime localDateTime) {
        this.publicationId = publicationId;
        this.commitId = commitId;
        this.fullName = fullName;
        this.email = email;
        this.commit = commit;
        this.likeCount = likeCount;
        this.myLike = myLike;
        this.localDateTime = localDateTime;
    }
}