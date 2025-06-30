package com.slackclone.message_service.repository;

import com.slackclone.message_service.model.MessageRead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageReadRepository extends JpaRepository<MessageRead, Long> {
    List<MessageRead> findByMessageId(Long messageId);
    boolean existsByMessageIdAndUserId(Long messageId, Long userId);
}
