package com.liqaa;

import com.liqaa.util.FilePaths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import com.liqaa.util.SceneManager;
import java.io.IOException;

public class HelloApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(FilePaths.LOGIN_SCREEN_FXML));
        Pane root = fxmlLoader.load();
        Scene scene = new Scene(root, 890, 610);
        SceneManager.inilialize(stage);

        stage.setTitle("Liqaa!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}