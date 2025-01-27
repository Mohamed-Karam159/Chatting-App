package com.liqaa.controllers;

import com.liqaa.HelloApplication;
import com.liqaa.util.FilePaths;
import com.liqaa.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class StatisticsController {

    @javafx.fxml.FXML
    public void logout(Event event) {
    }

    @javafx.fxml.FXML
    public void switchToAnnouncements(Event event)
    {
        try {
            SceneManager.getInstance().switchScene(new Scene(FXMLLoader.load(HelloApplication.class.getResource(FilePaths.ANNOUNCEMENTS_SCREEN_FXML))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void switchToManager(Event event)
    {
        try {
            SceneManager.getInstance().switchScene(new Scene(FXMLLoader.load(HelloApplication.class.getResource(FilePaths.MANAGER_SCREEN_FXML))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    public void sendAnnouncement(ActionEvent actionEvent)
    {

    }
}
