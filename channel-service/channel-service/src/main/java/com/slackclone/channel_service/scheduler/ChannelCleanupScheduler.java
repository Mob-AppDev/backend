package com.slackclone.channel_service.scheduler;

import com.slackclone.channel_service.model.Channel;
import com.slackclone.channel_service.repository.ChannelRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ChannelCleanupScheduler {

    private final ChannelRepository repository;

    public ChannelCleanupScheduler(ChannelRepository repository) {
        this.repository = repository;
    }

    @Scheduled(cron = "0 0 2 * * ?") // Daily at 2 AM
    @Transactional
    public void deleteOldArchivedChannels() {
        LocalDateTime cutoff = LocalDateTime.now().minusDays(30);
        List<Channel> oldChannels = repository.findByArchivedIsTrueAndArchivedAtBefore(cutoff);
        repository.deleteAll(oldChannels);
        System.out.println("Deleted " + oldChannels.size() + " old archived channels.");
    }
}
