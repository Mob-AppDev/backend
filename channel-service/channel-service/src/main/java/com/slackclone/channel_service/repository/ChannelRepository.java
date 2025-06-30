package com.slackclone.channel_service.repository;

import com.slackclone.channel_service.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
    List<Channel> findByArchivedIsTrueAndArchivedAtBefore(LocalDateTime cutoff);
}
