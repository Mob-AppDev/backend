package com.slackclone.message_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "channel-service", url = "http://localhost:8083") // update port
public interface ChannelClient {
    @GetMapping("/api/channels/{id}")
    Object getChannelById(@PathVariable Long id);
}