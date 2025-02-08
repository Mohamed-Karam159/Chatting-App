package com.liqaa.client;

import com.liqaa.client.controllers.services.implementations.CurrentUserImpl;
import com.liqaa.client.controllers.services.implementations.NotificationServiceImpl;
import com.liqaa.client.controllers.services.implementations.TryServiceImpl;
import com.liqaa.client.controllers.services.implementations.UserInfoServiceImpl;
import com.liqaa.client.network.ServerConnection;
import com.liqaa.client.util.DateFormatter;
import com.liqaa.shared.models.entities.User;
import com.liqaa.shared.network.Server;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/liqaa/client/view/fxml/primary.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Liqaa");
        stage.setScene(scene);
        stage.show();
        
        testServerConnection();
    }

    private void testServerConnection() throws RemoteException {
        Server server = ServerConnection.getServer();
        if (server != null) {
            String message = server.ping();
            System.out.println("Connected to server successfully. Server responded: " + message);
        } else {
            System.err.println("Failed to connect to server.");
        }
    }

    public static void main(String[] args) throws SQLException, RemoteException {
        //System.out.println(new CurrentUserImpl().getCurrentUser());
       // System.out.println(NotificationServiceImpl.getInstance().getAllNotifications(3));
//        User user = UserInfoServiceImpl.getInstance().getUserById(3);
//        System.out.println("before update -> " + user + "\n");
//        user.setDisplayName("Laila");
//        user.setBio("any new bio....");
//        System.out.println("after update -> " + user + "\n");
//        UserInfoServiceImpl.getInstance().updateUser(user);
        launch();
    }
}