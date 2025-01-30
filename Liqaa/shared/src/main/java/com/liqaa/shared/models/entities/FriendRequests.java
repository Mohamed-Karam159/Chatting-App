package com.liqaa.shared.models.entities;

import java.io.Serializable;
import java.time.LocalTime;

import com.liqaa.shared.models.enums.FriendRequestStatus;

public class FriendRequests implements Serializable {

    private final int senderId; // Made final for immutability
    private final int receiverId; // Made final for immutability
    private FriendRequestStatus requestStatus = FriendRequestStatus.PENDING; // Default value
    private final LocalTime createdAt; // Made final for immutability
    private LocalTime updatedAt;

    // Constructor
    public FriendRequests(int senderId, int receiverId, LocalTime createdAt, LocalTime updatedAt) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Setter (only if mutability is required)
    public void setRequestStatus(FriendRequestStatus requestStatus) {
        this.requestStatus = requestStatus;
        this.updatedAt = LocalTime.now(); // Update the timestamp when status changes
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public FriendRequestStatus getRequestStatus() {
        return requestStatus;
    }

    public LocalTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalTime getCreatedAt() {
        return createdAt;
    }

    // toString() method for debugging
    @Override
    public String toString() {
        return "FriendRequests{"
                + "senderId=" + senderId
                + ", receiverId=" + receiverId
                + ", requestStatus=" + requestStatus
                + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt
                + '}';
    }

}
