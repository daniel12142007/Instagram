package com.example.instagram.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Direct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "direct")
    private List<Notification> notifications;
}