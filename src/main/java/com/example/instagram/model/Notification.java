package com.example.instagram.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime data_send;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "direct_id")
    private Direct direct;
}