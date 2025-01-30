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
    public int addNew(Notification notification) throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query = "INSERT INTO notifications (recipient_id, sender_id, announcement_id, type, is_read) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, notification.getRecipientId());
        statement.setInt(2, notification.getSenderId());
        statement.setInt(3, notification.getAnnouncementId());
        statement.setString(4, String.valueOf(notification.getType()));
        statement.setBoolean(5, notification.isRead());
        statement.executeQuery();
        int id = -1;
        ResultSet resultSet = statement.getGeneratedKeys();
        while (resultSet.next()){
            id = resultSet.getInt("id");
        }
        return id;
    }

    @Override
    public Notification getById(int id) throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query = "SELECT id, recipient_id, sender_id, announcement_id, type, is_read, sent_at FROM notifications WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return new Notification(resultSet.getInt("id"), resultSet.getInt("recipient_id"), resultSet.getInt("sender_id"), resultSet.getInt("announcement_id"), NotificationType.valueOf(resultSet.getString("type")), resultSet.getBoolean("is_read"), resultSet.getTimestamp("sent_at").toLocalDateTime());
        }
        return null;
    }

    @Override
    public List<Notification> getAll() throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query = "SELECT id, recipient_id, sender_id, announcement_id, type, is_read, sent_at FROM notifications";
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
    public boolean update(Notification notification) throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query = "UPDATE notifications SET recipient_id = ?, sender_id = ?, announcement_id = ?, type = ?, is_read = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, notification.getRecipientId());
        statement.setInt(2, notification.getSenderId());
        statement.setInt(3, notification.getAnnouncementId());
        statement.setString(4, String.valueOf(notification.getType()));
        statement.setBoolean(5, notification.isRead());
        return (statement.executeUpdate() > 0); /** executeUpdate() returns the number of rows be updated successfully */
    }

    @Override
    public boolean delete(int id) throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query = "DELETE FROM notifications WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        int rowAffected = statement.executeUpdate();
        return (rowAffected > 0);
    }

    @Override
    public List<Notification> getAllFriendRequests(int recipientId) throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
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

    public List<Notification> getAllAcceptedRequests() throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
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
        Connection connection = DatabaseManager.getInstance().getConnection();
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
