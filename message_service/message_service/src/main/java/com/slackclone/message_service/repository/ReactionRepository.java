package com.slackclone.message_service.repository;

import com.slackclone.message_service.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    List<Reaction> findByMessageId(Long messageId);
    void deleteByMessageIdAndUserIdAndEmoji(Long messageId, Long userId, String emoji);
}