package com.liqaa.client.controllers.FXMLcontrollers;

import java.io.IOException;
import java.time.LocalDate;

import com.liqaa.client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SignUp1Controller {

    @FXML
    private Button SignInButton;

    @FXML
    private Button SignUpButton;

    @FXML
    private Button NextButton;

    @FXML
    private TextField NameField;

    @FXML
    private TextField PhoneField;

    @FXML
    private TextField EmailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField ConfirmationpasswordField;

    @FXML
    private TextField CountryField;

    @FXML
    private ComboBox<String> GenderField;

    @FXML
    private DatePicker DateField;

    @FXML
    private Label PhoneLabel;

    @FXML
    private Label EmailLabel;

    @FXML
    private Label PasswordLabel;

    @FXML
    private Label ConfirmationPasswordLabel;

    @FXML
    private Label CountryLabel;
    @FXML
    private Label NameLabel;

    private String name;
    private String phone;
    private String email;
    private String password=null;
    private String confirmPassword;
    private String country;
    private String gender;
    private LocalDate date;

    public void initialize() {

        GenderField.getItems().addAll("Male", "Female");
        SignUpButton.setDisable(true);
        password=null;
        confirmPassword=null;

    }

    // Event Handlers
//    @FXML
//    public void setSignInButtonOnAction() {
//        SignInButton.setOnAction(e -> {
//            System.out.println("Sign In button clicked.");
//            try {
//                Main.setRoot("/com/liqaa/client/view/fxml/SignIn");
//            } catch (IOException ex) {
//                ex.printStackTrace();
//                System.out.println("signIn exception: " + ex.getMessage());
//            }
//
//            // Add logic to handle sign-in action
//        });
//    }

//    @FXML
//    public void setNextButtonOnAction() {
//        NextButton.setOnAction(e -> {
//            System.out.println("Next button clicked.");
//
//            name = NameField.getText();
//            phone = PhoneField.getText();
//            email = EmailField.getText();
//            password = passwordField.getText();
//            confirmPassword = ConfirmationpasswordField.getText();
//            country = CountryField.getText();
//            gender = GenderField.getValue();
//            // Check if all sections are filled or not
//            if (checkAllData()) {
//                try {
//                    Main.setRoot("/com/liqaa/client/view/fxml/SignUp2");
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                    System.out.println("signup2 exception: " + ex.getMessage());
//                }
//            } else {
//                Alert a = new Alert(Alert.AlertType.NONE);
//                a.setAlertType(Alert.AlertType.INFORMATION);
//                a.setContentText("Please complete all required fields before proceeding.");
//                a.show(); // Add this line to display the alert
//            }
//
//            // Test output
//            System.out.println(name);
//            System.out.println(phone);
//            System.out.println(password);
//            System.out.println(country);
//            System.out.println(confirmPassword);
//            System.out.println(gender);
//            System.out.println(email);
//            System.out.println(date);
//        });
//    }

    @FXML
    public void handleNameField() {

        NameLabel.setText("");
        if (!UserInfoValidation.isVaildName( NameField.getText())) {
            NameLabel.setFont(new Font("Arial", 10)); // Set font and size
            NameLabel.setTextFill(Color.RED); // Set text color
            NameLabel.setText("Please Enter Characters Only"); //Message to user
            NameField.clear();
            name=null;
        }

    }

    @FXML
    public void handlePhoneField() {
        PhoneLabel.setText("");
        if (!UserInfoValidation.isValidPhoneNumber(PhoneField.getText())) {
            PhoneLabel.setFont(new Font("Arial", 10)); // Set font and size
            PhoneLabel.setTextFill(Color.RED); // Set text color
            PhoneLabel.setText("Please Enter a Valid Number"); //Message to user
            PhoneField.clear();
            phone=null;
        }
    }

    @FXML
    public void handleEmailField() {
        EmailLabel.setText("");
        if (!UserInfoValidation.isVaildEmail(EmailField.getText())) {
            EmailLabel.setFont(new Font("Arial", 10)); // Set font and size
            EmailLabel.setTextFill(Color.RED); // Set text color
            EmailLabel.setText("Please Enter a Valid Email"); //Message to user
            EmailField.clear();
            email=null;
        }
    }

    @FXML
    public void handlePassword() {
        PasswordLabel.setText("");
        if (!UserInfoValidation.isValidPassword(passwordField.getText())) {
            PasswordLabel.setFont(new Font("Arial", 9)); // Set font and size
            PasswordLabel.setTextFill(Color.RED); // Set text color
            PasswordLabel.setText("Password must be at least 6 characters long and contain both letters and numbers");    //Message to user
            passwordField.clear();
            password=null;

        }
    }

    @FXML
    public void handleConfirmationpassword()  {
        ConfirmationPasswordLabel.setText("");
        if ((passwordField.getText() == null) || !passwordField.getText().equals(ConfirmationpasswordField.getText()))
        {
            ConfirmationPasswordLabel.setFont(new Font("Arial", 10)); // Set font and size
            ConfirmationPasswordLabel.setTextFill(Color.RED); // Set text color
            ConfirmationPasswordLabel.setText("Passwords do not match. Please re-enter your password.");    //Message to user
            ConfirmationpasswordField.clear();
            //confirmPassword=null;
        }
    }

    @FXML
    public void handleCountryField() {
        country = CountryField.getText();
        System.out.println("Country entered: " + country);
    }

    @FXML
    public void handleGenderField() {
        gender = GenderField.getValue();
        System.out.println("Gender selected: " + gender);
    }

    @FXML
    public void handleDateField() {
        date = DateField.getValue() != null ? DateField.getValue() : null;
        System.out.println("Date selected: " + date);
    }
    public boolean checkAllData()
    {
        if((name!=null)&&(phone!=null)&&(password!=null)&&(confirmPassword!=null)&&(gender!=null)&&(country!=null)&&(email!=null)&&(date!=null))
        {
            return true;
        }
        return false;
    }
}
