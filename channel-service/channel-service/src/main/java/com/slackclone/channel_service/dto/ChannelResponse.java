package com.slackclone.channel_service.dto;

import lombok.Data;

@Data
public class ChannelResponse {
    private Long id;
    private String name;
    private String description;
    private Long createdByUserId;
}

