package com.liqaa.shared.models.entities;
import java.time.LocalDateTime;
public class Message {
    private int id;
    private int senderId;
    private int conversationId;
    private String content;
    private String type;
    private LocalDateTime sentAt;
    private boolean isSent;
    private LocalDateTime receivedAt;
    private LocalDateTime seenAt;

    // Constructors

    public Message(int id, int senderId, int conversationId, String content, String type, LocalDateTime sentAt, boolean isSent, LocalDateTime receivedAt, LocalDateTime seenAt) {
        this.id = id;
        this.senderId = senderId;
        this.conversationId = conversationId;
        this.content = content;
        this.type = type;
        this.sentAt = sentAt;
        this.isSent = isSent;
        this.receivedAt = receivedAt;
        this.seenAt = seenAt;
    }
    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }

    public LocalDateTime getSeenAt() {
        return seenAt;
    }

    public void setSeenAt(LocalDateTime seenAt) {
        this.seenAt = seenAt;
    }
    // Other methods

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", conversationId=" + conversationId +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", sentAt=" + sentAt +
                ", isSent=" + isSent +
                ", receivedAt=" + receivedAt +
                ", seenAt=" + seenAt +
                '}';
    }
}
