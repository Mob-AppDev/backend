package com.slackclone.message_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Long senderId;
    private Long channelId;

    private LocalDateTime timestamp;

    // One message has many reactions
    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reaction> reactions = new ArrayList<>();

    // Constructors
    public Message() {}

    public Message(String content, Long senderId, Long channelId) {
        this.content = content;
        this.senderId = senderId;
        this.channelId = channelId;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Long getSenderId() { return senderId; }
    public void setSenderId(Long senderId) { this.senderId = senderId; }

    public Long getChannelId() { return channelId; }
    public void setChannelId(Long channelId) { this.channelId = channelId; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public List<Reaction> getReactions() { return reactions; }
    public void setReactions(List<Reaction> reactions) { this.reactions = reactions; }
}
