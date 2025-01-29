package com.liqaa.server.controllers.FXMLcontrollers;

import com.liqaa.server.Main;
import com.liqaa.server.util.FilePaths;
import com.liqaa.server.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class LoginController
{
    @FXML
    private TextField passwordTextField;
    @FXML
    private ImageView passwordIcon;
    @FXML
    private TextField adminIdTextField;
    @FXML
    private Button AdminLogInButton;

    boolean isPasswordVisiable= true;

    @FXML
    public void handlePasswordVisability(Event event)
    {
        isPasswordVisiable = !isPasswordVisiable;
//        passwordTextField. (isPasswordVisiable);
        passwordIcon.setVisible(isPasswordVisiable);
    }

    @FXML
    public void handleAdminLogInButton(ActionEvent actionEvent)
    {
        try {
            SceneManager.getInstance().switchScene(new Scene(FXMLLoader.load(Main.class.getResource(FilePaths.MANAGER_SCREEN_FXML))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
