package com.slackclone.channel_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ChannelResponse {
    private Integer id;
    private String name;
    private Boolean isPrivate;
    private Integer createdBy;
    private Boolean archived;
    private LocalDateTime archivedAt;
}
