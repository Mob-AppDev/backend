package com.slackclone.message_service.service;

import com.slackclone.message_service.model.Message;
import com.slackclone.message_service.model.Reaction;
import com.slackclone.message_service.repository.MessageRepository;
import com.slackclone.message_service.repository.ReactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionService {

    private final ReactionRepository reactionRepo;
    private final MessageRepository messageRepo;

    public ReactionService(ReactionRepository reactionRepo, MessageRepository messageRepo) {
        this.reactionRepo = reactionRepo;
        this.messageRepo = messageRepo;
    }

    public Reaction addReaction(Long messageId, String emoji, Long userId) {
        Message message = messageRepo.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));

        Reaction reaction = new Reaction();
        reaction.setEmoji(emoji);
        reaction.setUserId(userId);
        reaction.setMessage(message);

        return reactionRepo.save(reaction);
    }

    public void removeReaction(Long messageId, String emoji, Long userId) {
        reactionRepo.deleteByMessageIdAndUserIdAndEmoji(messageId, userId, emoji);
    }

    public List<Reaction> getReactionsForMessage(Long messageId) {
        return reactionRepo.findByMessageId(messageId);
    }
}
