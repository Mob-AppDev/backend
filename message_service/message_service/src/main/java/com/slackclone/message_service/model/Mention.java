package com.slackclone.message_service.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "mentions")
@IdClass(Mention.MentionId.class)
public class Mention {

    @Id
    @Column(name = "message_id")
    private Long messageId;

    @Id
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", insertable = false, updatable = false)
    private Message message;

    // Constructors
    public Mention() {}

    public Mention(Long messageId, Long userId) {
        this.messageId = messageId;
        this.userId = userId;
    }

    // Getters and Setters
    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    // Composite Key Class
    public static class MentionId implements Serializable {
        private Long messageId;
        private Long userId;

        public MentionId() {}

        public MentionId(Long messageId, Long userId) {
            this.messageId = messageId;
            this.userId = userId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MentionId)) return false;
            MentionId that = (MentionId) o;
            return Objects.equals(messageId, that.messageId) &&
                    Objects.equals(userId, that.userId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(messageId, userId);
        }
    }
}
