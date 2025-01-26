package org.example.mywork;

import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class UserInfoController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void editNameMouseClicked(MouseEvent mouseEvent) {
        System.out.println("edit name is clicked");
    }

    public void editImgMouseClicked(MouseEvent mouseEvent) {
        System.out.println("edit image is clicked");
    }

    public void editBioMouseClicked(MouseEvent mouseEvent) {
        System.out.println("edit bio is clicked");
    }

    public void editStatusMouseClicked(MouseEvent mouseEvent) {
        System.out.println("edit status is clicked");
    }
}
