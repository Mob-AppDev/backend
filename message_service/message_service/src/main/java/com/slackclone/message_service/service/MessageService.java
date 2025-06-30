package com.slackclone.message_service.service;

import com.slackclone.message_service.client.ChannelClient;
import com.slackclone.message_service.client.UserClient;
import com.slackclone.message_service.dto.MessageRequestDTO;
import com.slackclone.message_service.model.*;
import com.slackclone.message_service.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageReadRepository messageReadRepository;
    private final MentionRepository mentionRepository;
    private final UserClient userClient;
    private final ChannelClient channelClient;

    public MessageService(
            MessageRepository messageRepository,
            MessageReadRepository messageReadRepository,
            MentionRepository mentionRepository,
            UserClient userClient,
            ChannelClient channelClient
    ) {
        this.messageRepository = messageRepository;
        this.messageReadRepository = messageReadRepository;
        this.mentionRepository = mentionRepository;
        this.userClient = userClient;
        this.channelClient = channelClient;
    }

    public Message saveMessage(Message message, List<Long> mentionedUserIds) {
        userClient.getUserById(message.getSenderId());
        channelClient.getChannelById(message.getChannelId());

        message.setTimestamp(LocalDateTime.now());
        Message saved = messageRepository.save(message);

        // Store mentions
        if (mentionedUserIds != null) {
            for (Long userId : mentionedUserIds) {
                Mention mention = new Mention(saved.getId(), userId);
                mention.setMessage(saved);
                mentionRepository.save(mention);
            }
        }

        return saved;
    }

    public List<Message> getMessagesByChannel(Long channelId) {
        return messageRepository.findByChannelId(channelId);
    }

    public List<Message> searchMessages(String keyword) {
        return messageRepository.findByContentContainingIgnoreCase(keyword);
    }

    public Page<Message> getPaginatedMessagesByChannel(Long channelId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return messageRepository.findByChannelId(channelId, pageable);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message updateMessage(Long id, MessageRequestDTO request) {
        Message existing = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));

        existing.setContent(request.getContent());
        existing.setTimestamp(LocalDateTime.now());
        return messageRepository.save(existing);
    }

    public void deleteMessage(Long id) {
        if (!messageRepository.existsById(id)) {
            throw new RuntimeException("Message not found");
        }
        messageRepository.deleteById(id);
    }

    public void markMessageAsSeen(Long messageId, Long userId) {
        if (messageReadRepository.existsByMessageIdAndUserId(messageId, userId)) return;

        MessageRead read = new MessageRead();
        read.setMessageId(messageId);
        read.setUserId(userId);
        read.setReadAt(LocalDateTime.now());

        messageReadRepository.save(read);
    }

    public List<MessageRead> getSeenBy(Long messageId) {
        return messageReadRepository.findByMessageId(messageId);
    }
}
