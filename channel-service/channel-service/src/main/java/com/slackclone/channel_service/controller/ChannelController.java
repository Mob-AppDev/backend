package com.slackclone.channel_service.controller;

import com.slackclone.channel_service.dto.ChannelRequest;
import com.slackclone.channel_service.dto.ChannelResponse;
import com.slackclone.channel_service.model.Channel;
import com.slackclone.channel_service.service.ChannelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/channels")
public class ChannelController {

    private final ChannelService service;

    public ChannelController(ChannelService service) {
        this.service = service;
    }

    @GetMapping
    public List<Channel> getAllChannels() {
        return service.getAllChannels();
    }

    @PostMapping
    public ResponseEntity<ChannelResponse> createChannel(@RequestBody ChannelRequest request) {
        Channel channel = service.createChannel(request);
        ChannelResponse response = new ChannelResponse();
        response.setId(channel.getId());
        response.setName(channel.getName());
        response.setDescription(channel.getDescription());
        response.setCreatedByUserId(channel.getCreatedByUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public Channel getChannelById(@PathVariable Long id) {
        return service.getChannelById(id);
    }
}
