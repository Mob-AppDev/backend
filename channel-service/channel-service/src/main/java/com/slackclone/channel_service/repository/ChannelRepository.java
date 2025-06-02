package com.slackclone.channel_service.repository;

import com.slackclone.channel_service.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
