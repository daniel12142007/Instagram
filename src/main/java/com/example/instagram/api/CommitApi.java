package com.example.instagram.api;

import com.example.instagram.dto.response.CommitResponse;
import com.example.instagram.service.CommitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/commit/publication")
@RequiredArgsConstructor
public class CommitApi {
    private final CommitService commitService;

    @GetMapping("find/all/commit")
    public List<CommitResponse> findAllCommitPublication(@RequestParam Long publicationId,
                                                         @RequestParam String email) {
        return commitService.commitResponses(email, publicationId);
    }
}