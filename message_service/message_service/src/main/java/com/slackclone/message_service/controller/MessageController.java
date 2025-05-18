package com.slackclone.message_service.controller;

import com.slackclone.message_service.dto.MessageRequestDTO;
import com.slackclone.message_service.model.Message;
import com.slackclone.message_service.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<Message> sendMessage(@Valid @RequestBody MessageRequestDTO request) {
        Message msg = new Message(request.getContent(), request.getSenderId(), request.getChannelId());
        return ResponseEntity.ok(messageService.saveMessage(msg));
    }

    @GetMapping("/channel/{channelId}")
    public ResponseEntity<List<Message>> getMessagesByChannel(@PathVariable Long channelId) {
        return ResponseEntity.ok(messageService.getMessagesByChannel(channelId));
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }
}