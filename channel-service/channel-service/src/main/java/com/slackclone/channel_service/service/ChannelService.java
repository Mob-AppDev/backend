package com.slackclone.channel_service.service;

import com.slackclone.channel_service.client.UserClient;
import com.slackclone.channel_service.dto.ChannelRequest;
import com.slackclone.channel_service.model.Channel;
import com.slackclone.channel_service.repository.ChannelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ChannelService {

    private final ChannelRepository repository;
    private final UserClient userClient;

    public ChannelService(ChannelRepository repository, UserClient userClient) {
        this.repository = repository;
        this.userClient = userClient;
    }

    public Channel createChannel(ChannelRequest request) {
        try {
            userClient.getUserById(request.getCreatedByUserId());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }

        Channel channel = new Channel();
        channel.setName(request.getName());
        channel.setDescription(request.getDescription());
        channel.setCreatedByUserId(request.getCreatedByUserId());
        return repository.save(channel);
    }

    public Channel updateChannel(Long id, ChannelRequest request) {
        Channel channel = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel not found"));

        channel.setName(request.getName());
        channel.setDescription(request.getDescription());
        channel.setCreatedByUserId(request.getCreatedByUserId());
        return repository.save(channel);
    }

    public boolean deleteChannel(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel not found");
        }
        repository.deleteById(id);
        return true;
    }

    public List<Channel> getAllChannels() {
        return repository.findAll();
    }

    public Channel getChannelById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel not found"));
    }
}
