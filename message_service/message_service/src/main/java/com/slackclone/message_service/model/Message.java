package com.slackclone.message_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Long senderId;   // user ID (from user-service)
    private Long channelId;  // channel ID (from channel-service)

    private LocalDateTime timestamp;

    // Constructors
    public Message() {}

    public Message(String content, Long senderId, Long channelId) {
        this.content = content;
        this.senderId = senderId;
        this.channelId = channelId;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Long getSenderId() { return senderId; }
    public void setSenderId(Long senderId) { this.senderId = senderId; }

    public Long getChannelId() { return channelId; }
    public void setChannelId(Long channelId) { this.channelId = channelId; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
