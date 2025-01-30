package com.liqaa.server.controllers.reposotories.interfaces;

import com.liqaa.shared.models.entities.Notification;

import java.sql.SQLException;
import java.util.List;

public interface NotificationRepo {
    /** int? can be used to return the id of the last inserted row, the number of rows inserted successfully (helpful especially in case of using batch execution) */
    public int createNotification(Notification notification) throws SQLException;
    public Notification getNotificationById(int id) throws SQLException;
    public List<Notification> getAllNotifications() throws SQLException;
    public boolean updateNotification(Notification notification) throws SQLException;
    public boolean deleteNotification(int id) throws SQLException;
    public List<Notification> getAllFriendRequests(int recipientId) throws SQLException;
    public List<Notification> getAllAcceptedRequests() throws SQLException;
    public List<Notification> getAllDeclinedRequests() throws SQLException;
}