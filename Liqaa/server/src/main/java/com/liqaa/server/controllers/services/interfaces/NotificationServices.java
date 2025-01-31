package com.liqaa.server.controllers.services.interfaces;

public interface NotificationServices
{
    getNotifications();
    markAsRead();
    deleteNotification();
    creatNotification()
    sendAnnouncement();
    getAnnouncement();
    deleteAnnouncement();
    cleanupOldNotifications();
    countUnreadNotifications();

}
