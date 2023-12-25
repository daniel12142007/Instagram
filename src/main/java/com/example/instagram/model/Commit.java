package com.example.instagram.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Commit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String commit;
    private LocalDateTime dataNow = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userCommit;
    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;
    @OneToMany(mappedBy = "mainCommit", cascade = CascadeType.ALL)
    private List<Commit> answeredCommit;
    @ManyToOne
    @JoinColumn(name = "mainCommit_id")
    private Commit mainCommit;
    @ManyToMany(mappedBy = "commitsLikes")
    private List<User>users;
}