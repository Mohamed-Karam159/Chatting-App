package com.liqaa.client.network;


import com.liqaa.shared.network.Client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl extends UnicastRemoteObject implements Client {
    protected ClientImpl(int port) throws RemoteException {
        super(port);
    }
}