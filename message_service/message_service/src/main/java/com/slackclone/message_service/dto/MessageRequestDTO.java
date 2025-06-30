package com.slackclone.message_service.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class MessageRequestDTO {

    @NotNull
    private String content;

    @NotNull
    private Long senderId;

    @NotNull
    private Long channelId;

    private List<Long> mentionedUserIds; // For mentions

    // Getters and Setters
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Long getSenderId() { return senderId; }
    public void setSenderId(Long senderId) { this.senderId = senderId; }

    public Long getChannelId() { return channelId; }
    public void setChannelId(Long channelId) { this.channelId = channelId; }

    public List<Long> getMentionedUserIds() { return mentionedUserIds; }
    public void setMentionedUserIds(List<Long> mentionedUserIds) { this.mentionedUserIds = mentionedUserIds; }
}
