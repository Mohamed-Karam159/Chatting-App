package com.liqaa.client.controllers.FXMLcontrollers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.liqaa.client.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SignUp2Controller {

    @FXML
    private Button ChoosePicture;

    @FXML
    private Button RegistrationButton;

    @FXML
    private Button SignInButton;

    @FXML
    private Button SignUpButton;

    @FXML
    private Circle CircleImage;
    /**
     * Attributes
     */
    private File selectedFile;
    private byte[] UserImage;

    public void initialize() {
        System.out.println("Signup2");
        // Example: Load default image 

        try {
            Image userImage = new Image(getClass().getResource("/com/liqaa/client/view/images/Defaultimage.png").toExternalForm());
            Platform.runLater(() -> {
                CircleImage.setFill(new ImagePattern(userImage));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleProfilePictureButton(ActionEvent event) {
        System.out.println("Choose picture clicked");
        // choose photo 
        // Create a FileChooser to select an image
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        // Show the FileChooser and get the selected file
        Stage stage = (Stage) ChoosePicture.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            // Creating an image from the selected file
            Image image = new Image(selectedFile.toURI().toString());
            // Setting the image view
            CircleImage.setFill(new ImagePattern(image));


        }
    }

    @FXML
    void handleRegistrationButton(ActionEvent event) {
        System.out.println("Register clicked");
        // Register this user   boolean Registration (User user) ;
        //if true  nevigate to home page
        //if false ,show error Msg
    }

    @FXML
    void handleSignInButton(ActionEvent event) {
        SignInButton.setOnAction(e -> {
            System.out.println("Sign In button clicked.");
            try {
                Main.setRoot("/com/liqaa/client/view/fxml/SignIn");
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("signIn exception: " + ex.getMessage());
            }
        });
    }

    @FXML
    void handleSignUpButton(ActionEvent event) {
        SignUpButton.setOnAction(e -> {
            System.out.println("Sign up button clicked.");
            try {
                Main.setRoot("/com/liqaa/client/view/fxml/SignUp1");
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("signup exception: " + ex.getMessage());
            }
        });
    }
}
