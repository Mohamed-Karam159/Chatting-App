package com.liqaa.client.controllers.services.implementations;

import com.liqaa.client.network.ServerConnection;
import com.liqaa.shared.models.entities.FileMessage;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class MessageServices
{
    public FileMessage getFileInfo(int messageId) {
        try {
            return ServerConnection.getServer().getFileInfo(messageId);
        } catch (SQLException e) {
            System.err.println("Error in getFileInfo: " + e.getMessage());
        } catch (RemoteException e) {
            System.err.println("Error in getFileInfo: " + e.getMessage());
        }
        return null;
    }
}
