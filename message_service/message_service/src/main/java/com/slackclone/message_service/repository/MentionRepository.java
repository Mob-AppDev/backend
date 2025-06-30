package com.slackclone.message_service.repository;

import com.slackclone.message_service.model.Mention;
import com.slackclone.message_service.model.Mention.MentionId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MentionRepository extends JpaRepository<Mention, MentionId> {
    List<Mention> findByMessageId(Long messageId);
    List<Mention> findByUserId(Long userId);
}
