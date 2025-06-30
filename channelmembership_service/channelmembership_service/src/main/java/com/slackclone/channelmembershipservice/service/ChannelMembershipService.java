package com.slackclone.channelmembershipservice.service;

import com.slackclone.channelmembershipservice.dto.ChannelMembershipRequest;
import com.slackclone.channelmembershipservice.dto.ChannelMembershipResponse;
import com.slackclone.channelmembershipservice.model.ChannelMembership;
import com.slackclone.channelmembershipservice.repository.ChannelMembershipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChannelMembershipService {

    private final ChannelMembershipRepository repository;

    public ChannelMembershipService(ChannelMembershipRepository repository) {
        this.repository = repository;
    }

    public ChannelMembershipResponse addMember(ChannelMembershipRequest request) {
        boolean exists = repository.existsByChannelIdAndUserId(request.getChannelId(), request.getUserId());
        if (exists) {
            throw new IllegalArgumentException("User is already a member of the channel");
        }

        ChannelMembership membership = ChannelMembership.builder()
                .channelId(request.getChannelId())
                .userId(request.getUserId())
                .build();

        repository.save(membership);

        return new ChannelMembershipResponse(
                membership.getId(),
                membership.getChannelId(),
                membership.getUserId()
        );
    }

    public List<ChannelMembershipResponse> getMembersByChannel(Long channelId) {
        return repository.findByChannelId(channelId)
                .stream()
                .map(m -> new ChannelMembershipResponse(m.getId(), m.getChannelId(), m.getUserId()))
                .collect(Collectors.toList());
    }

    public List<ChannelMembershipResponse> getChannelsByUser(Long userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(m -> new ChannelMembershipResponse(m.getId(), m.getChannelId(), m.getUserId()))
                .collect(Collectors.toList());
    }

    public void removeMember(Long membershipId) {
        repository.deleteById(membershipId);
    }
}
