package com.liqaa.client;


import com.liqaa.server.controllers.reposotories.implementations.AnnouncementRepoImpl;
import com.liqaa.shared.models.entities.Announcement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException
    {
        // todo: insert the first login page and remove comments
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
        //stage.show();
    }

    public static void main(String[] args) throws SQLException {

        //launch();
//        Announcement announcement = new Announcement(1, "try1", "thhhhh", LocalDateTime.now());
//        AnnouncementRepoImpl impl = new AnnouncementRepoImpl();
//        impl.addNew(announcement);
    }
}