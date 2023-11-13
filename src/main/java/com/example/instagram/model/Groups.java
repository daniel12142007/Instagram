package com.example.instagram.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private final LocalDateTime dateNow = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;
    @ManyToMany
    @JoinTable(name = "groups_users",
            joinColumns = @JoinColumn(name = "groups_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id"))
    private List<User> users;
}