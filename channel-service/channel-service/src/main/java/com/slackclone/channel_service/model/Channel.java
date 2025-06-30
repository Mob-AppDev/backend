package com.slackclone.channel_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "channels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates IDs
    @Column(name = "channel_id")
    private Integer id;

    @Column(name = "nname", nullable = false)
    private String name;

    @Column(name = "is_private")
    private Boolean isPrivate;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_archived")
    private Boolean archived = false;

    @Column(name = "archived_at")
    private LocalDateTime archivedAt;
}
