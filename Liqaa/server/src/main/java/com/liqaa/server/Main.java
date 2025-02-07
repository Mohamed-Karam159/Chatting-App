package com.liqaa.server;

import com.liqaa.server.network.ServerConnection;
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