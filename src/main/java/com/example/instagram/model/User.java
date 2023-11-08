package com.example.instagram.model;

import com.example.instagram.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

// TODO: 2
@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private LocalDateTime dateNow;
    @Enumerated(EnumType.STRING)
    private Role role;
}
