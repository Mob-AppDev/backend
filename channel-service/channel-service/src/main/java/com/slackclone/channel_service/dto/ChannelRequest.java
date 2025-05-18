package com.slackclone.channel_service.dto;

import lombok.Data;

@Data
public class ChannelRequest {
    private String name;
    private String description;
    private Long createdByUserId;
}
