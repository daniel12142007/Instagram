package com.example.instagram.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private final LocalDateTime dataCreated = LocalDateTime.now();
    private boolean block;
    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL)
    private List<Image> images;
    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL)
    private List<Notification> comments;
    @ManyToMany(mappedBy = "likes")
    private List<User> user_like;
    @ManyToMany(mappedBy = "favorites")
    private List<User> user_favorites;
    @ManyToOne
    @JoinColumn(name = "user_posted")
    private User user;
}