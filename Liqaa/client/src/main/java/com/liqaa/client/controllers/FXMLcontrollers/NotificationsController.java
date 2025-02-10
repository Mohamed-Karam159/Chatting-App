package com.liqaa.client.controllers.FXMLcontrollers;

import com.liqaa.client.controllers.FXMLcontrollers.components.NotificationCard1Controller;
import com.liqaa.client.controllers.FXMLcontrollers.components.NotificationCard2Controller;
import com.liqaa.client.controllers.services.implementations.NotificationServiceImpl;
import com.liqaa.shared.models.entities.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class NotificationsController implements Initializable {
    static Stage myStage;
    @FXML
    private Button allBtn;

    @FXML
    private Button reqBtn;

    @FXML
    private Button annBtn;

    @FXML
    private MenuButton invitStatusBtn;

    @FXML
    private VBox notificationsContainer;

    ObservableList<Notification> notificationList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        notificationList = FXCollections.observableArrayList();
        notificationList.addListener((ListChangeListener<Notification>) change -> {
            updateUI();
        });
        loadNotifications();
    }

    private void updateUI() {
        notificationsContainer.getChildren().clear();
        for (Notification notification : notificationList) {
            FXMLLoader loader;
            try {
                if(notification.getType().toString().equals("FRIEND_REQUEST")){
                    loader = new FXMLLoader(getClass().getResource("/com/liqaa/client/view/fxml/components/NotificationCard2.fxml"));
                }
                else {
                    loader = new FXMLLoader(getClass().getResource("/com/liqaa/client/view/fxml/components/NotificationCard1.fxml"));
                }
                HBox notificationCard = loader.load();

                Object controller = loader.getController();
                if (controller instanceof NotificationCard1Controller) {
                    ((NotificationCard1Controller) controller).setNotificationData(notification);
                } else if (controller instanceof NotificationCard2Controller) {
                    ((NotificationCard2Controller) controller).setNotificationData(notification);
                }

                notificationsContainer.getChildren().add(notificationCard);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void loadNotifications() {
        try {
            List<Notification> newNotifications = NotificationServiceImpl.getInstance().getAllNotifications(7); // replace it with the current user id
            notificationList.setAll(newNotifications);
        } catch (SQLException | RemoteException e) {
            e.printStackTrace();
        }
    }


    public void profile_action(MouseEvent mouseEvent) throws IOException {
        System.out.println("profile btn is clicked");
        Parent parent = FXMLLoader.load(getClass().getResource("/com/liqaa/client/view/fxml/userInfo.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        myStage = stage;
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
