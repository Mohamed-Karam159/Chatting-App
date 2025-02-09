package com.liqaa.client.controllers.FXMLcontrollers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ContactsController implements Initializable {
    @FXML
    private ListView<Contact> contactsList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupListView();
        populateContacts();
    }

    private void setupListView() {
        contactsList.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Contact> call(ListView<Contact> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Contact item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setGraphic(null);
                        } else {
                            setGraphic(createContactCell(item));
                        }
                    }
                };
            }
        });
    }

    private HBox createContactCell(Contact item) {
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER_LEFT);

        ImageView imageView = new ImageView(item.getPhoto());
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);

        Circle circle = new Circle(4);
        circle.setFill(item.getStatus().equals("Available") ? Color.GREEN : Color.RED);
        StackPane photoWithStatus = new StackPane(imageView, circle);
        StackPane.setAlignment(circle, Pos.BOTTOM_RIGHT);

        Text nameText = new Text(item.getName());
        Text phoneText = new Text(item.getPhoneNumber());
        Text bioText = new Text(item.getBio());
        Text categoryText = new Text(item.getCategory());

        ImageView blockIcon = new ImageView(item.getBlock());
        blockIcon.setFitHeight(20);
        blockIcon.setFitWidth(20);

        ImageView editIcon = new ImageView(item.getEdit());
        editIcon.setFitHeight(20);
        editIcon.setFitWidth(20);

        ImageView deleteIcon = new ImageView(item.getDelete());
        deleteIcon.setFitHeight(20);
        deleteIcon.setFitWidth(20);

        hBox.getChildren().addAll(photoWithStatus, nameText, phoneText, bioText, categoryText, blockIcon, editIcon, deleteIcon);
        return hBox;
    }

    private void populateContacts() {
        ObservableList<Contact> contacts = FXCollections.observableArrayList(
                new Contact(new Image(getClass().getResource("/com/liqaa/client/view/images/user1.png").toString()), "Lindsey Stroud", "+201000333511", "Muslim", "Available", "Friend", new Image(getClass().getResource("/com/liqaa/client/view/images/block.png").toString()), new Image(getClass().getResource("/com/liqaa/client/view/images/edit.png").toString()), new Image(getClass().getResource("/com/liqaa/client/view/images/delete.png").toString())),
                new Contact(new Image(getClass().getResource("/com/liqaa/client/view/images/user2.png").toString()), "Sarah brown", "+201033325420", "Feel free", "Busy", "Family", new Image(getClass().getResource("/com/liqaa/client/view/images/block.png").toString()), new Image(getClass().getResource("/com/liqaa/client/view/images/edit.png").toString()), new Image(getClass().getResource("/com/liqaa/client/view/images/delete.png").toString())),
                new Contact(new Image(getClass().getResource("/com/liqaa/client/view/images/user3.png").toString()), "Micheal Owen", "+201088865308", "Fueled by coffee & code", "Away", "Friend, Work", new Image(getClass().getResource("/com/liqaa/client/view/images/block.png").toString()), new Image(getClass().getResource("/com/liqaa/client/view/images/edit.png").toString()), new Image(getClass().getResource("/com/liqaa/client/view/images/delete.png").toString())),
                new Contact(new Image(getClass().getResource("/com/liqaa/client/view/images/user4.png").toString()), "Mary Jane", "+201077554274", "Just an ITIan :)", "Available", "Work", new Image(getClass().getResource("/com/liqaa/client/view/images/block.png").toString()), new Image(getClass().getResource("/com/liqaa/client/view/images/edit.png").toString()), new Image(getClass().getResource("/com/liqaa/client/view/images/delete.png").toString())),
                new Contact(new Image(getClass().getResource("/com/liqaa/client/view/images/user5.png").toString()), "Peter dodle", "+201045632103", "Sleeping...", "Away", "Work", new Image(getClass().getResource("/com/liqaa/client/view/images/block.png").toString()), new Image(getClass().getResource("/com/liqaa/client/view/images/edit.png").toString()), new Image(getClass().getResource("/com/liqaa/client/view/images/delete.png").toString()))
        );

        contactsList.setItems(contacts);
    }

    // Event handlers for the buttons
    @FXML
    private void profile_action(MouseEvent event) {
        System.out.println("Profile button clicked!");
    }

    @FXML
    private void home_action(MouseEvent event) {
        System.out.println("Home button clicked!");
    }

    @FXML
    private void notification_action(MouseEvent event) {
        System.out.println("Notification button clicked!");
    }

    @FXML
    private void contact_action(MouseEvent event) {
        System.out.println("Contact button clicked!");
    }

    @FXML
    private void chatbot_action(MouseEvent event) {
        System.out.println("Chatbot button clicked!");
    }

    @FXML
    private void settings_action(MouseEvent event) {
        System.out.println("Settings button clicked!");
    }

    @FXML
    private void logout_action(MouseEvent event) {
        System.out.println("Logout button clicked!");
    }

    @FXML
    private void addContactAction() {
        try {
            Dialog<Contact> dialog = new Dialog<>();
            dialog.setTitle("Add Contact");
            dialog.setHeaderText("Enter the contact details:");

            // Set the button types
            ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

            // Apply custom styles to the dialog
            dialog.getDialogPane().getStylesheets().add(
                    getClass().getResource("/com/liqaa/client/view/styles/style1.css").toExternalForm()
            );

            // Create the fields
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField nameField = new TextField();
            nameField.setPromptText("Name");
            TextField phoneField = new TextField();
            phoneField.setPromptText("Phone Number");

            grid.add(new Label("Name:"), 0, 0);
            grid.add(nameField, 1, 0);
            grid.add(new Label("Phone:"), 0, 1);
            grid.add(phoneField, 1, 1);

            dialog.getDialogPane().setContent(grid);

            // Convert the result to a Contact object
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == addButton) {
                    return new Contact(
                            new Image(getClass().getResource("/com/liqaa/client/view/images/user1.png").toString()),
                            nameField.getText(),
                            phoneField.getText(),
                            "", // Bio
                            "Available", // Status
                            "", // Category
                            new Image(getClass().getResource("/com/liqaa/client/view/images/block.png").toString()),
                            new Image(getClass().getResource("/com/liqaa/client/view/images/edit.png").toString()),
                            new Image(getClass().getResource("/com/liqaa/client/view/images/delete.png").toString())
                    );
                }
                return null;
            });

            Optional<Contact> result = dialog.showAndWait();
            result.ifPresent(contact -> {
                contactsList.getItems().add(contact); // Add the new contact to the list
                System.out.println("New Contact: " + contact.getName() + " - " + contact.getPhoneNumber());
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error in addContactAction: " + e.getMessage());
        }
    }
    @FXML
    private void addCategoryAction() {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Category");
            dialog.setHeaderText("Enter the new category name:");
            dialog.setContentText("Category:");

            // Apply custom styles to the dialog
            dialog.getDialogPane().getStylesheets().add(
                    getClass().getResource("/com/liqaa/client/view/styles/style1.css").toExternalForm()
            );

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(categoryName -> {
                System.out.println("New Category: " + categoryName);
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error in addCategoryAction: " + e.getMessage());
        }
    }

    @FXML
    private void removeCategoryAction() {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Remove Category");
            dialog.setHeaderText("Enter the category name to remove:");
            dialog.setContentText("Category:");

            // Apply custom styles to the dialog
            dialog.getDialogPane().getStylesheets().add(
                    getClass().getResource("/com/liqaa/client/view/styles/style1.css").toExternalForm()
            );

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(categoryName -> {
                System.out.println("Category to remove: " + categoryName);
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error in removeCategoryAction: " + e.getMessage());
        }
    }
}