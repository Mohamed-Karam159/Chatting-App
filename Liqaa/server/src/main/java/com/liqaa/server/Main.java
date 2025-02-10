package com.liqaa.server;

import com.liqaa.server.controllers.reposotories.implementations.NotificationRepoImpl;
import com.liqaa.server.network.ServerConnection;
import com.liqaa.shared.models.entities.AnnouncementNotification;
import com.liqaa.shared.models.entities.Notification;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class Main
{
    public static void main(String[] args) throws RemoteException, SQLException
    {
        startServer();
    }

    public static void startServer() throws RemoteException, SQLException
    {
        ServerConnection serverConnection = ServerConnection.getInstance();
        serverConnection.start();
        System.out.println("Server started");
    }

}