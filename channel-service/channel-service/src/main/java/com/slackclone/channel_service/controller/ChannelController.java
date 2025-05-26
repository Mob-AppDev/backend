package com.slackclone.channel_service.controller;

import com.slackclone.channel_service.dto.ChannelRequest;
import com.slackclone.channel_service.dto.ChannelResponse;
import com.slackclone.channel_service.model.Channel;
import com.slackclone.channel_service.service.ChannelService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.slackclone.channel_service.client.UserClient;


import java.util.List;
import java.util.stream.Collectors;
import com.slackclone.channel_service.client.UserClient;

@RestController
@RequestMapping("/api/channels")
public class ChannelController {

    private final ChannelService service;
    private final UserClient userClient;

    public ChannelController(ChannelService service, UserClient userClient) {
        this.service = service;
        this.userClient = userClient;
    }

    @PostMapping
    public ResponseEntity<ChannelResponse> createChannel(@Valid @RequestBody ChannelRequest request) {
        Channel channel = service.createChannel(request);
        return ResponseEntity.status(201).body(toResponse(channel));
    }

    @GetMapping
    public List<ChannelResponse> getAllChannels() {
        return service.getAllChannels().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChannelResponse> getChannelById(@PathVariable Long id) {
        Channel channel = service.getChannelById(id);
        return ResponseEntity.ok(toResponse(channel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChannelResponse> updateChannel(@PathVariable Long id, @Valid @RequestBody ChannelRequest request) {
        Channel updated = service.updateChannel(id, request);
        return ResponseEntity.ok(toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChannel(@PathVariable Long id) {
        service.deleteChannel(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/hello-from-user")
    public String getHelloFromUserService() {
        return userClient.sayHelloFromUserService();
    }


    private ChannelResponse toResponse(Channel c) {
        return new ChannelResponse(c.getId(), c.getName(), c.getDescription(), c.getCreatedByUserId());
    }
}

