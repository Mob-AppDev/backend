package com.slackclone.message_service.controller;

import com.slackclone.message_service.model.Reaction;
import com.slackclone.message_service.service.ReactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/reactions")
public class ReactionController {

    private final ReactionService reactionService;

    public ReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @PostMapping("/{messageId}")
    public ResponseEntity<Reaction> addReaction(
            @PathVariable Long messageId,
            @RequestParam String emoji,
            @RequestParam Long userId
    ) {
        try {
            return ResponseEntity.ok(reactionService.addReaction(messageId, emoji, userId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> removeReaction(
            @PathVariable Long messageId,
            @RequestParam String emoji,
            @RequestParam Long userId
    ) {
        try {
            reactionService.removeReaction(messageId, emoji, userId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<List<Reaction>> getReactions(@PathVariable Long messageId) {
        try {
            return ResponseEntity.ok(reactionService.getReactionsForMessage(messageId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnexpectedError(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred");
    }
}