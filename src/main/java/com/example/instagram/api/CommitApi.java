package com.example.instagram.api;

import com.example.instagram.dto.response.CommitResponse;
import com.example.instagram.service.CommitService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.PastOrPresent;
import java.util.List;

@RestController
@RequestMapping("api/v1/commit/publication")
@RequiredArgsConstructor
public class CommitApi {
    private final CommitService commitService;

    @GetMapping("find/all/commit")
    public List<CommitResponse> findAllCommitPublication(@RequestParam Long publicationId) {
        String myEmail = SecurityContextHolder.getContext().getAuthentication() != null
                ? SecurityContextHolder.getContext().getAuthentication().getName()
                : null;
        return commitService.commitResponses(myEmail, publicationId);
    }

    @PostMapping("committed")
    public List<CommitResponse> saveCommit(@RequestParam Long publicationId,
                                           @RequestParam String commit) {
        String myEmail = SecurityContextHolder.getContext().getAuthentication() != null
                ? SecurityContextHolder.getContext().getAuthentication().getName()
                : null;
        return commitService.committed(myEmail, publicationId, commit);
    }
}