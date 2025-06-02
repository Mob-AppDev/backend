package com.slackclone.message_service.service;

import com.slackclone.message_service.client.UserClient;
import com.slackclone.message_service.client.ChannelClient;
import com.slackclone.message_service.model.Message;
import com.slackclone.message_service.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private ChannelClient channelClient;

    public Message saveMessage(Message message) {
        try {
            // Validate sender and channel by calling the other services
            userClient.getUserById(message.getSenderId());
            channelClient.getChannelById(message.getChannelId());
        } catch (Exception e) {
            throw new RuntimeException("Invalid sender or channel ID");
        }

        // Set the timestamp and save
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByChannel(Long channelId) {
        return messageRepository.findByChannelId(channelId);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
