package com.example.instagram.service;

import com.example.instagram.dto.response.CommitResponse;
import com.example.instagram.model.User;
import com.example.instagram.repository.CommitRepository;
import com.example.instagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommitService {
    private final CommitRepository commitRepository;
    private final UserRepository userRepository;

    public List<CommitResponse> commitResponses(String email, Long publicationId) {
        User user = userRepository.findByEmail(email).orElseThrow(RuntimeException::new);
        if (commitRepository.findAllCommitResponse(publicationId, user.getEmail()).isEmpty())
            throw new RuntimeException("Empty");
        return commitRepository.findAllCommitResponse(publicationId, user.getEmail());
    }
}
