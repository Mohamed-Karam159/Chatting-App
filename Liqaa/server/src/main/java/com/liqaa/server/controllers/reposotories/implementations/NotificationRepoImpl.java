package com.liqaa.server.controllers.reposotories.implementations;

import com.liqaa.server.controllers.reposotories.interfaces.NotificationRepo;
import com.liqaa.server.util.DatabaseManager;
import com.liqaa.shared.models.entities.Notification;
import com.liqaa.shared.models.enums.NotificationType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationRepoImpl implements NotificationRepo {

    @Override
    public int createNotification(Notification notification) throws SQLException {
        if (notification == null) {
            System.err.println("Error creating notification: Notification is null");
            return 0;
        }
        try(Connection connection = DatabaseManager.getConnection();) {
            String query = "INSERT INTO notifications (recipient_id, sender_id, announcement_id, type, is_read) VALUES (?, ?, ?, ?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setInt(1, notification.getRecipientId());
                statement.setInt(2, notification.getSenderId());
                statement.setInt(3, notification.getAnnouncementId());
                statement.setString(4, String.valueOf(notification.getType()));
                statement.setBoolean(5, notification.isRead());
                statement.executeQuery();
                int id = -1;
                try(ResultSet resultSet = statement.getGeneratedKeys();) {
                    while (resultSet.next()) {
                        id = resultSet.getInt("id");
                    }
                    if (id == -1) {
                        System.err.println("Failed to create notification");
                    } else {
                        System.out.println("notification is created successfully");
                    }
                    return id;
                }
            }
        }
    }

    @Override
    public Notification getNotificationById(int id) throws SQLException {
        if(id <= 0) {
            System.err.println("Error getting notification: Invalid ID");
            return null;
        }
        try(Connection connection = DatabaseManager.getConnection();) {
            String query = "SELECT id, recipient_id, sender_id, announcement_id, type, is_read, sent_at FROM notifications WHERE id = ?";
            try(PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setInt(1, id);
                try(ResultSet resultSet = statement.executeQuery();) {
                    if (resultSet.next()) {
                        return new Notification(resultSet.getInt("id"), resultSet.getInt("recipient_id"), resultSet.getInt("sender_id"), resultSet.getInt("announcement_id"), NotificationType.valueOf(resultSet.getString("type")), resultSet.getBoolean("is_read"), resultSet.getTimestamp("sent_at").toLocalDateTime());
                    }
                    return null;
                }
            }
        }
    }

    @Override
    public List<Notification> getAllNotifications() throws SQLException {
        try(Connection connection = DatabaseManager.getConnection();) {
            String query = "SELECT id, recipient_id, sender_id, announcement_id, type, is_read, sent_at FROM notifications";
            try(PreparedStatement statement = connection.prepareStatement(query);) {
                try(ResultSet resultSet = statement.executeQuery();) {
                    List<Notification> notifications = new ArrayList<>();
                    while (resultSet.next()) {
                        Notification notification = new Notification(resultSet.getInt("id"), resultSet.getInt("recipient_id"), resultSet.getInt("sender_id"), resultSet.getInt("announcement_id"), NotificationType.valueOf(resultSet.getString("type")), resultSet.getBoolean("is_read"), resultSet.getTimestamp("sent_at").toLocalDateTime());
                        notifications.add(notification);
                    }
                    return notifications;
                }
            }
        }
    }

    @Override
    public boolean updateNotification(Notification notification) throws SQLException {
        if (notification == null) {
            System.err.println("Error updating notification: Notification is null");
            return false;
        }
        try(Connection connection = DatabaseManager.getConnection();) {
            String query = "UPDATE notifications SET recipient_id = ?, sender_id = ?, announcement_id = ?, type = ?, is_read = ?";
            try(PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setInt(1, notification.getRecipientId());
                statement.setInt(2, notification.getSenderId());
                statement.setInt(3, notification.getAnnouncementId());
                statement.setString(4, String.valueOf(notification.getType()));
                statement.setBoolean(5, notification.isRead());
                if (statement.executeUpdate() <= 0) { /** executeUpdate() returns the number of rows be updated successfully */
                    System.err.println("Failed to update notification");
                } else {
                    System.out.println("notification is updated successfully");
                }
                return (statement.executeUpdate() > 0);
            }
        }
    }

    @Override
    public boolean deleteNotification(int id) throws SQLException {
        if (id <= 0) {
            System.err.println("Error deleting notification: Invalid ID");
            return false;
        }
        try (Connection connection = DatabaseManager.getConnection();) {
            String query = "DELETE FROM notifications WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setInt(1, id);
                int rowAffected = statement.executeUpdate();
                if (rowAffected <= 0) {
                    System.err.println("Failed to delete notification");
                    return false;
                } else {
                    System.out.println("notification is deleted successfully");
                }
                return true;
            }
        }
    }

    @Override
    public List<Notification> getAllFriendRequests(int recipientId) throws SQLException {
        Connection connection = DatabaseManager.getConnection();
        String query = "SELECT id, recipient_id, sender_id, announcement_id, type, is_read, sent_at FROM notifications WHERE type = 'FRIEND_REQUEST'";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<Notification> notifications = new ArrayList<>();
        while(resultSet.next()){
            Notification notification = new Notification(resultSet.getInt("id"), resultSet.getInt("recipient_id"), resultSet.getInt("sender_id"), resultSet.getInt("announcement_id"), NotificationType.valueOf(resultSet.getString("type")), resultSet.getBoolean("is_read"), resultSet.getTimestamp("sent_at").toLocalDateTime());
            notifications.add(notification);
        }
        return notifications;
    }

    @Override
    public List<Notification> getAllAcceptedRequests() throws SQLException {
        Connection connection = DatabaseManager.getConnection();
        String query = "SELECT id, recipient_id, sender_id, announcement_id, type, is_read, sent_at FROM notifications WHERE type = 'REQUEST_ACCEPTED'";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<Notification> notifications = new ArrayList<>();
        while(resultSet.next()){
            Notification notification = new Notification(resultSet.getInt("id"), resultSet.getInt("recipient_id"), resultSet.getInt("sender_id"), resultSet.getInt("announcement_id"), NotificationType.valueOf(resultSet.getString("type")), resultSet.getBoolean("is_read"), resultSet.getTimestamp("sent_at").toLocalDateTime());
            notifications.add(notification);
        }
        return notifications;
    }

    @Override
    public List<Notification> getAllDeclinedRequests() throws SQLException {
        Connection connection = DatabaseManager.getConnection();
        String query = "SELECT id, recipient_id, sender_id, announcement_id, type, is_read, sent_at FROM notifications WHERE type = 'REQUEST_DECLINED'";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<Notification> notifications = new ArrayList<>();
        while(resultSet.next()){
            Notification notification = new Notification(resultSet.getInt("id"), resultSet.getInt("recipient_id"), resultSet.getInt("sender_id"), resultSet.getInt("announcement_id"), NotificationType.valueOf(resultSet.getString("type")), resultSet.getBoolean("is_read"), resultSet.getTimestamp("sent_at").toLocalDateTime());
            notifications.add(notification);
        }
        return notifications;
    }
}