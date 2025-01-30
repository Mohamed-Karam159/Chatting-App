package com.liqaa.server.controllers.reposotories.interfaces;

import com.liqaa.shared.models.entities.Notification;

import java.sql.SQLException;
import java.util.List;

public interface NotificationRepo {
    public int createNotification(Notification notification) throws SQLException;
    public Notification getNotificationById(int id) throws SQLException;
    public List<Notification> getAllNotifications() throws SQLException;
    public boolean updateNotification(Notification notification) throws SQLException;
    public boolean deleteNotification(int id) throws SQLException;
    public List<Notification> getAllFriendRequests(int recipientId) throws SQLException;
    public List<Notification> getAllAcceptedRequests() throws SQLException;
    public List<Notification> getAllDeclinedRequests() throws SQLException;
}