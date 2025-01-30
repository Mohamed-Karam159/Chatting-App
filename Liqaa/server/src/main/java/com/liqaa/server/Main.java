package com.liqaa.server;

import com.liqaa.server.controllers.reposotories.implementations.ConversationParticipantsRepoImpl;
import com.liqaa.shared.models.entities.ConversationParticipant;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application
{
    public static void main(String[] args) 
    {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception
    {
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(FilePaths.LOGIN_SCREEN_FXML));
//        Pane root = fxmlLoader.load();
//        Scene scene = new Scene(root, 890, 610);
//        SceneManager.initialize(stage);
//        stage.setTitle("Liqaa!");
//        stage.setScene(scene);
        stage.show();
    }
}