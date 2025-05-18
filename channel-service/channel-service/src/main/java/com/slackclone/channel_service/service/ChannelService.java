package com.slackclone.channel_service.service;

import com.slackclone.channel_service.client.UserClient;
import com.slackclone.channel_service.dto.ChannelRequest;
import com.slackclone.channel_service.model.Channel;
import com.slackclone.channel_service.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class ChannelService {

    private final ChannelRepository repository;

    // UserClient userClient; // Uncomment when user-service is ready

    @Autowired
    public ChannelService(ChannelRepository repository /*, UserClient userClient */) {
        this.repository = repository;
        // this.userClient = userClient;
    }

    public Channel createChannel(ChannelRequest request) {
        // Temporarily disabled user validation until user-service DB is ready
        /*
        try {
            userClient.getUserById(request.getCreatedByUserId());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
        */

        Channel channel = new Channel();
        channel.setName(request.getName());
        channel.setDescription(request.getDescription());
        channel.setCreatedByUserId(request.getCreatedByUserId());

        return repository.save(channel);
    }

    public List<Channel> getAllChannels() {
        return repository.findAll();
    }

    public Channel getChannelById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
