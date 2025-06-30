package com.slackclone.channelmembershipservice.controller;

import com.slackclone.channelmembershipservice.dto.ChannelMembershipRequest;
import com.slackclone.channelmembershipservice.dto.ChannelMembershipResponse;
import com.slackclone.channelmembershipservice.service.ChannelMembershipService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/channel-memberships")
public class ChannelMembershipController {

    private final ChannelMembershipService service;

    public ChannelMembershipController(ChannelMembershipService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ChannelMembershipResponse> addMember(@Valid @RequestBody ChannelMembershipRequest request) {
        return ResponseEntity.status(201).body(service.addMember(request));
    }

    @GetMapping("/channel/{channelId}")
    public List<ChannelMembershipResponse> getMembersByChannel(@PathVariable Long channelId) {
        return service.getMembersByChannel(channelId);
    }

    @GetMapping("/user/{userId}")
    public List<ChannelMembershipResponse> getChannelsByUser(@PathVariable Long userId) {
        return service.getChannelsByUser(userId);
    }

    @DeleteMapping("/{membershipId}")
    public ResponseEntity<Void> removeMember(@PathVariable Long membershipId) {
        service.removeMember(membershipId);
        return ResponseEntity.noContent().build();
    }
}
