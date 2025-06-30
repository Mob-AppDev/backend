package com.slackclone.message_service.repository;

import com.slackclone.message_service.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChannelId(Long channelId);  // For non-paginated
    List<Message> findByContentContainingIgnoreCase(String keyword);

    Page<Message> findByChannelId(Long channelId, Pageable pageable);  // For paginated
}
