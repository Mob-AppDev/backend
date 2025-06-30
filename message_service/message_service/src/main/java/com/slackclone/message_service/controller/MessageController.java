package com.slackclone.message_service.controller;

import com.slackclone.message_service.dto.MessageRequestDTO;
import com.slackclone.message_service.model.Message;
import com.slackclone.message_service.model.MessageRead;
import com.slackclone.message_service.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // Send message + mentions
    @PostMapping
    public ResponseEntity<Message> sendMessage(@Valid @RequestBody MessageRequestDTO request) {
        Message msg = new Message(request.getContent(), request.getSenderId(), request.getChannelId());
        return ResponseEntity.ok(messageService.saveMessage(msg, request.getMentionedUserIds()));
    }

    // Retrieve all messages (not recommended for large data)
    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    // Retrieve non-paginated messages by channel
    @GetMapping("/channel/{channelId}")
    public ResponseEntity<List<Message>> getMessagesByChannel(@PathVariable Long channelId) {
        return ResponseEntity.ok(messageService.getMessagesByChannel(channelId));
    }

    // Retrieve paginated messages by channel
    @GetMapping("/channel/{channelId}/paged")
    public ResponseEntity<Page<Message>> getPaginatedMessages(
            @PathVariable Long channelId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(messageService.getPaginatedMessagesByChannel(channelId, page, size));
    }

    // Search messages by keyword
    @GetMapping("/search")
    public ResponseEntity<List<Message>> searchMessages(@RequestParam String keyword) {
        return ResponseEntity.ok(messageService.searchMessages(keyword));
    }

    // Update message
    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody MessageRequestDTO request) {
        return ResponseEntity.ok(messageService.updateMessage(id, request));
    }

    // Delete message
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }

    // Mark a message as seen
    @PostMapping("/{messageId}/seen/{userId}")
    public ResponseEntity<Void> markAsSeen(@PathVariable Long messageId, @PathVariable Long userId) {
        messageService.markMessageAsSeen(messageId, userId);
        return ResponseEntity.ok().build();
    }

    // Get users who have seen a message
    @GetMapping("/{messageId}/seen")
    public ResponseEntity<List<MessageRead>> getSeenBy(@PathVariable Long messageId) {
        return ResponseEntity.ok(messageService.getSeenBy(messageId));
    }
}
