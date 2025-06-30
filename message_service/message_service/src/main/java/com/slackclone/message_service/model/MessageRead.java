package com.slackclone.message_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message_reads")
public class MessageRead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message_id", nullable = false)
    private Long messageId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "read_at")
    private LocalDateTime readAt;

    // Constructors
    public MessageRead() {}

    public MessageRead(Long messageId, Long userId, LocalDateTime readAt) {
        this.messageId = messageId;
        this.userId = userId;
        this.readAt = readAt;
    }

    // Setters
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setReadAt(LocalDateTime readAt) {
        this.readAt = readAt;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Long getMessageId() {
        return messageId;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getReadAt() {
        return readAt;
    }
}
