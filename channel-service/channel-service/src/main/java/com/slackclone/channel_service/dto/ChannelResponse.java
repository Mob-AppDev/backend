package com.slackclone.channel_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelResponse {
    private Long id;
    private String name;
    private String description;
    private Long createdByUserId;
}

