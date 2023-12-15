package com.example.instagram.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    @Lob
    private byte[] data;
    @OneToOne
    @JoinColumn(name = "notification_id")
    private Notification notification;
    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;
}