package com.example.instagram.model;

import com.example.instagram.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

// TODO: 2
@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private LocalDateTime dateNow;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(mappedBy = "user")
    private Direct direct;
    @OneToMany(mappedBy = "user_like")
    private List<Publication>likes;
    @OneToMany(mappedBy = "user_favorites")
    private List<Publication> favorites;
    @OneToMany(mappedBy = "user")
    private List<Publication> publications;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notification> notifications;
    @OneToMany(mappedBy = "creator")
    private List<Groups> group;
    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Groups> groups;
}