package org.example.mywork;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class NotificationsController implements Initializable {
    @FXML
    private Button allBtn;

    @FXML
    private Button reqBtn;

    @FXML
    private Button annBtn;

    @FXML
    private MenuButton invitStatusBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void profile_action(MouseEvent mouseEvent) {
        System.out.println("profile btn is clicked");
    }

    public void home_action(MouseEvent mouseEvent) {
        System.out.println("home btn is clicked");
    }

    public void notification_action(MouseEvent mouseEvent) {
        System.out.println("notification btn is clicked");
    }

    public void contact_action(MouseEvent mouseEvent) {
        System.out.println("contact btn is clicked");
    }

    public void chatbot_action(MouseEvent mouseEvent) {
        System.out.println("chatbot btn is clicked");
    }

    public void settings_action(MouseEvent mouseEvent) {
        System.out.println("settings btn is clicked");
    }

    public void logout_action(MouseEvent mouseEvent) {
        System.out.println("logout btn is clicked");
    }

    public void allAction(){
        System.out.println("all clicked");
    }

    public void acceptedAction(){
        System.out.println("accepted clicked");
    }

    public void rejectedAction(){
        System.out.println("rejected clicked");
    }

    public void pendingAction(){
        System.out.println("pending clicked");
    }

    public void reqAction(){
        System.out.println("req clicked");
    }
    public void annAction(){
        System.out.println("ann clicked");
    }

    public void allMouseEntered(){
        allBtn.setStyle("-fx-background-color: #3498DB;");
    }
    public void allMouseExited(){
        allBtn.setStyle("-fx-background-color: #384E6A;");
    }

    public void statusMouseEntered(){
        invitStatusBtn.setStyle("-fx-background-color: #3498DB;");
    }

    public void statusMouseExited(){
        invitStatusBtn.setStyle("-fx-background-color: #384E6A;");
    }

    public void reqMouseEntered(){
        reqBtn.setStyle("-fx-background-color: #3498DB;");
    }

    public void reqMouseExited(){
        reqBtn.setStyle("-fx-background-color: #384E6A;");
    }

    public void annMouseEntered(){
        annBtn.setStyle("-fx-background-color: #3498DB;");
    }

    public void annMouseExited(){
        annBtn.setStyle("-fx-background-color: #384E6A;");
    }

}