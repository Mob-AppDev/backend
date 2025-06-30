// 1. User.java
package com.slackclone.message_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String passwordHash;
    private String profilePictureUrl;
    private String status;
    private LocalDateTime lastActive;
    private LocalDateTime createdAt;

    // Getters and setters
}
