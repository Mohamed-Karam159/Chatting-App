package com.liqaa.client.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class SceneManager
{
    private static SceneManager instance;
    private final Stage stage;

    private SceneManager(Stage stage) {
        this.stage = stage;
    }

    public static void initialize(Stage stage)
    {
        if (instance == null) {
            instance = new SceneManager(stage);
        }
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("SceneManager is not initialized. Call initialize(Stage stage) first.");
        }
        return instance;
    }


    public void showUserInfoSceneInNewStage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/liqaa/client/view/fxml/userInfo.fxml"));
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.setScene(newScene);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPrimaryScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/liqaa/client/view/fxml/primary.fxml"));
            Scene newScene = new Scene(root);
            double width = stage.getWidth();
            double height = stage.getHeight();
            stage.setScene(newScene);
            stage.setWidth(width);
            stage.setHeight(height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showNotificationScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/liqaa/client/view/fxml/notifications.fxml"));
            Scene newScene = new Scene(root);
            double width = stage.getWidth();
            double height = stage.getHeight();
            stage.setScene(newScene);
            stage.setWidth(width);
            stage.setHeight(height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showContactScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/liqaa/client/view/fxml/contacts.fxml"));
            Scene newScene = new Scene(root);
            double width = stage.getWidth();
            double height = stage.getHeight();
            stage.setScene(newScene);
            stage.setWidth(width);
            stage.setHeight(height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showSignInScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/liqaa/client/view/fxml/signIn.fxml"));
            Scene newScene = new Scene(root);
            double width = stage.getWidth();
            double height = stage.getHeight();
            stage.setScene(newScene);
            stage.setWidth(width);
            stage.setHeight(height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showSignUpScene1() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/liqaa/client/view/fxml/signUp1.fxml"));
            Scene newScene = new Scene(root);
            double width = stage.getWidth();
            double height = stage.getHeight();
            stage.setScene(newScene);
            stage.setWidth(width);
            stage.setHeight(height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSignUpScene2() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/liqaa/client/view/fxml/signUp2.fxml"));
            Scene newScene = new Scene(root);
            double width = stage.getWidth();
            double height = stage.getHeight();
            stage.setScene(newScene);
            stage.setWidth(width);
            stage.setHeight(height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
