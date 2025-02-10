package com.liqaa.client.controllers.FXMLcontrollers;
import com.liqaa.client.Main;
import com.liqaa.client.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class SignInController {

    @FXML
    private Button LogInButton;

    @FXML
    private TextField PasswordField;

    @FXML
    private Button SignInButton;

    @FXML
    private Button SignUpButton;

    @FXML
    private TextField PhoneField;

    private String p="01143451668";
    private String pas="1234567sa";
    public void initialize() {

        System.out.println("sign in controller");
         SignInButton.setDisable(true);

}

    @FXML
    void handleLogInButton(ActionEvent event) {
       // User login(String phone , String password); >> return null or user info
        if (!PhoneField.getText().equals(p) || !PasswordField.getText().equals(pas)) {
            // Your code here for when the phone or password do not match

            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Invalid Phone Number or Password.");
            a.show(); // Add this line to display the alert
            PasswordField.clear();
            PhoneField.clear();
        }
        else  if (PhoneField.getText().equals(p) && PasswordField.getText().equals(pas))
            System.out.println("LogInButton");
        //nevigate to home

    }

    @FXML
    void handleSignUpButton(ActionEvent event) {
        System.out.println("SignUpButton");
        SignUpButton.setOnAction(e -> {
            System.out.println("Sign up button clicked.");
            try {
                SceneManager.getInstance().showSignUpScene1();
            }  catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("signIn exception: " + ex.getMessage());
            }});

    }

}
