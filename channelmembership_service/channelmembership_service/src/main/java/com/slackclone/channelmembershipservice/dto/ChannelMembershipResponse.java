package com.slackclone.channelmembershipservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChannelMembershipResponse {
    private Long id;
    private Long channelId;
    private Long userId;
}
