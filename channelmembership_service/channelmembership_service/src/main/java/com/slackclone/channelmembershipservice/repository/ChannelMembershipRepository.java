package com.slackclone.channelmembershipservice.repository;

import com.slackclone.channelmembershipservice.model.ChannelMembership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChannelMembershipRepository extends JpaRepository<ChannelMembership, Long> {

    List<ChannelMembership> findByChannelId(Long channelId);

    List<ChannelMembership> findByUserId(Long userId);

    boolean existsByChannelIdAndUserId(Long channelId, Long userId);
}

