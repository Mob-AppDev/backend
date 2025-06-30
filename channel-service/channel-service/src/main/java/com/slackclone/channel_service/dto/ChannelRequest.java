package com.slackclone.channel_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChannelRequest {

    @NotBlank(message = "Channel name is required")
    private String name;

    private Boolean isPrivate = false;

    @NotNull(message = "createdBy is required")
    private Integer createdBy;
}

