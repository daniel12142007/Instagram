package com.example.instagram.service;

import com.example.instagram.dto.response.CommitResponse;
import com.example.instagram.model.Commit;
import com.example.instagram.model.Publication;
import com.example.instagram.model.User;
import com.example.instagram.repository.CommitRepository;
import com.example.instagram.repository.PublicationRepository;
import com.example.instagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommitService {
    private final CommitRepository commitRepository;
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;

    public List<CommitResponse> commitResponses(String email, Long publicationId) {
        User user = userRepository.findByEmail(email).orElseThrow(RuntimeException::new);
        return commitRepository.findAllCommitResponse(publicationId, user.getEmail()).stream()
                .peek(commitResponse -> commitResponse.setCommitResponses(
                        commitRepository.findByIdAnswerCommit(commitResponse.getCommitId(), publicationId, email)
                )).collect(Collectors.toList());
    }

    public List<CommitResponse> committed(String email, Long publicationId, String myCommit) {
        User user = userRepository.findByEmail(email).orElseThrow(RuntimeException::new);
        Publication publication = publicationRepository.findById(publicationId).orElseThrow(RuntimeException::new);
        Commit commit = Commit.builder()
                .commit(myCommit)
                .userCommit(user)
                .publication(publication)
                .dataNow(LocalDateTime.now())
                .build();
        commitRepository.save(commit);
        return commitResponses(email, publicationId);
    }

    public List<CommitResponse> committedAnswer(String email, Long publicationId, Long commitId, String myCommit) {
        User user = userRepository.findByEmail(email).orElseThrow(RuntimeException::new);
        Publication publication = publicationRepository.findById(publicationId).orElseThrow(RuntimeException::new);
        Commit mainCommit = commitRepository.findById(commitId).orElseThrow(RuntimeException::new);
        Commit commit = Commit.builder()
                .commit(myCommit)
                .userCommit(user)
                .publication(publication)
                .dataNow(LocalDateTime.now())
                .mainCommit(mainCommit)
                .build();
        commitRepository.save(commit);
        return commitResponses(email, publicationId);
    }
}