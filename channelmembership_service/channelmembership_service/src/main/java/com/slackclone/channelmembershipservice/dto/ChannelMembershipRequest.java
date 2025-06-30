package com.slackclone.channelmembershipservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChannelMembershipRequest {

    @NotNull(message = "Channel ID is required")
    private Long channelId;

    @NotNull(message = "User ID is required")
    private Long userId;
}

