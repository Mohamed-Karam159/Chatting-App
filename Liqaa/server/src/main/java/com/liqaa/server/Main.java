package com.liqaa.server;

import com.liqaa.server.controllers.reposotories.implementations.NotificationRepoImpl;
import com.liqaa.server.network.ServerConnection;
import com.liqaa.server.util.FilePaths;
import com.liqaa.server.util.SceneManager;
import com.liqaa.shared.models.entities.AnnouncementNotification;
import com.liqaa.shared.models.entities.Notification;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class Main extends Application
{
    public static void main(String[] args) throws RemoteException, SQLException
    {
        startServer();
        launch(args);
    }

    public static void startServer() throws RemoteException, SQLException
    {
        ServerConnection serverConnection = ServerConnection.getInstance();
        serverConnection.start();
        System.out.println("Server started");

    }

    @Override
    public void start(Stage stage) throws Exception
    {
//        SceneManager.initialize(stage);
//        SceneManager.getInstance().switchScene(new Scene(FXMLLoader.load(getClass().getResource(FilePaths.MANAGER_SCREEN_FXML))));
//        stage.show();
    }
}