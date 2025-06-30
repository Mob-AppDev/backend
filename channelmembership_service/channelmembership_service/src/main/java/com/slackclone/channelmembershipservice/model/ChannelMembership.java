package com.slackclone.channelmembershipservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "channel_memberships")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChannelMembership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "channel_id", nullable = false)
    private Long channelId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "joined_at")
    private LocalDateTime joinedAt;

    @Column(name = "role")
    private String role;  // e.g., member, admin

    @PrePersist
    public void onJoin() {
        this.joinedAt = LocalDateTime.now();
    }
}
