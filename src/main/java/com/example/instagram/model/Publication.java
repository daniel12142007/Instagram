package com.example.instagram.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private final LocalDateTime dataCreated = LocalDateTime.now();
    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL)
    private List<Image> images;
    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL)
    private List<Notification> comments;
    @ManyToOne
    @JoinColumn(name = "user_likes")
    private User user_like;
    @ManyToOne
    @JoinColumn(name = "user_favorites")
    private User user_favorites;
    @ManyToOne
    @JoinColumn(name = "user_posted")
    private User user;
}