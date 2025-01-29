package com.liqaa.shared.models.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Notification implements Serializable {
    private int id, senderId, recipientId, AnnouncementId;
    private boolean isRead;
    private LocalDateTime sentAt;

    public Notification(int id, int senderId, int recipientId, int announcementId, boolean isRead, LocalDateTime sentAt) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        AnnouncementId = announcementId;
        this.isRead = isRead;
        this.sentAt = sentAt;
    }

    public Notification(int senderId, int recipientId, int announcementId, boolean isRead, LocalDateTime sentAt) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        AnnouncementId = announcementId;
        this.isRead = isRead;
        this.sentAt = sentAt;
    }

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

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public int getAnnouncementId() {
        return AnnouncementId;
    }

    public void setAnnouncementId(int announcementId) {
        AnnouncementId = announcementId;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}