package gov.iti.jets;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.control.ListCell;
import javafx.util.Callback;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.scene.shape.Polygon;

public class PrimaryController {

    @FXML
    private ListView<HBox> contactsListView;  // ListView for contacts

    @FXML
    private VBox chatBox;

    @FXML
    private TextField messageField;

    @FXML
    private ImageView chatUserImage;

    @FXML
    private Label chatUserName;

    @FXML
    private Label chatUserStatus;

    @FXML
    private StackPane logoPane; // منطقة اللوجو

    @FXML
    private VBox chatArea; // منطقة الشات

    @FXML
    private StackPane rightPane; // الجزء الأيمن من الشاشة

    @FXML
    private ImageView videoCallIcon; // أيقونة مكالمة الفيديو

    @FXML
    private ImageView voiceCallIcon; // أيقونة مكالمة الصوت

    @FXML
    public void initialize() {
        try {
            // Set up contacts list with images, status, last message time, and unread count
            addContact("Hazim Karam", "Online", "6:32 PM", 2, "/gov/iti/jets/images/user1.png");
            addContact("Salma Abdelnasser", "Offline", "6:17 PM", 0, "/gov/iti/jets/images/user2.png");
            addContact("Mohamed Karam", "Busy", "5:45 PM", 1, "/gov/iti/jets/images/user3.png");
            addContact("Alaa Hathout", "Away", "4:30 PM", 3, "/gov/iti/jets/images/user4.png");
            addContact("Ibrahim Diab", "Online", "3:15 PM", 0, "/gov/iti/jets/images/user5.png");
            addContact("Omar Khaled", "Offline", "2:00 PM", 5, "/gov/iti/jets/images/user6.png");
            addContact("Youssef Ahmed", "Online", "1:45 PM", 0, "/gov/iti/jets/images/user3.png");

            // Set up the ListView to handle cell clicks
            contactsListView.setCellFactory(new Callback<ListView<HBox>, ListCell<HBox>>() {
                @Override
                public ListCell<HBox> call(ListView<HBox> param) {
                    return new ListCell<HBox>() {
                        @Override
                        protected void updateItem(HBox item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setGraphic(null);
                            } else {
                                setGraphic(item);
                                setOnMouseClicked(event -> handleContactClick(item));
                            }
                        }
                    };
                }
            });

            // إخفاء منطقة الشات في البداية وإظهار اللوجو
            chatArea.setVisible(false);
            logoPane.setVisible(true);

            // إضافة معالج للأحداث لزر ESC
            chatArea.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ESCAPE) {
                    chatArea.setVisible(false);
                    logoPane.setVisible(true);
                }
            });

        } catch (Exception e) {
            System.err.println("Error initializing contacts: " + e.getMessage());
        }
    }

    private void addContact(String name, String status, String lastMessageTime, int unreadCount, String imagePath) {
        HBox contactItem = new HBox();
        contactItem.setSpacing(10);
        contactItem.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        contactItem.getStyleClass().add("contact-item");

        // تحميل الصورة
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(image);

        // تكبير الصورة مع زيادة الطول أكثر من العرض
        imageView.setFitHeight(100); // زيادة الطول إلى 100
        imageView.setFitWidth(70);  // زيادة العرض إلى 70
        imageView.setSmooth(true); // تمكين التجانس لتحسين الجودة
        imageView.setPreserveRatio(true); // الحفاظ على نسبة العرض إلى الارتفاع

        // جعل الصورة دائرية
        Circle clip = new Circle(50); // زيادة نصف القطر إلى 50 لتتناسب مع الطول الجديد
        clip.setCenterX(50);
        clip.setCenterY(50);
        imageView.setClip(clip);

        // VBox للاسم والحالة
        VBox vBox = new VBox();
        vBox.setSpacing(5);

        Label nameLabel = new Label(name);
        nameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        Label statusLabel = new Label(status);
        statusLabel.getStyleClass().add("status-label");
        if (status.equalsIgnoreCase("Offline")) {
            statusLabel.getStyleClass().add("offline");
        } else if (status.equalsIgnoreCase("Online")) {
            statusLabel.getStyleClass().add("online");
        } else if (status.equalsIgnoreCase("Busy")) {
            statusLabel.getStyleClass().add("busy");
        } else if (status.equalsIgnoreCase("Away")) {
            statusLabel.getStyleClass().add("away");
        }

        vBox.getChildren().addAll(nameLabel, statusLabel);

        // VBox للوقت وعدد الرسائل غير المقروءة
        VBox rightVBox = new VBox();
        rightVBox.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        rightVBox.setSpacing(5); // زيادة المسافة بين عدد الرسائل ووقت آخر رسالة
        rightVBox.setPadding(new Insets(0, 10, 0, 0));

        // وقت آخر رسالة
        Label timeLabel = new Label(lastMessageTime);
        timeLabel.getStyleClass().add("time-label");

        // عدد الرسائل غير المقروءة (إذا كان أكبر من 0)
        if (unreadCount > 0) {
            Label unreadLabel = new Label(String.valueOf(unreadCount));
            unreadLabel.getStyleClass().add("unread-count");
            rightVBox.getChildren().add(unreadLabel);
        }

        rightVBox.getChildren().add(timeLabel);

        // إضافة HBox فارغ لدفع rightVBox إلى أقصى اليمين
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS); // استخدام HBox.setHgrow بشكل صحيح

        contactItem.getChildren().addAll(imageView, vBox, spacer, rightVBox);
        contactsListView.getItems().add(contactItem);
    }

    private void handleContactClick(HBox contactItem) {
        // Extract user information from the contact item
        VBox vBox = (VBox) contactItem.getChildren().get(1);
        Label nameLabel = (Label) vBox.getChildren().get(0);
        Label statusLabel = (Label) vBox.getChildren().get(1);

        // Update the chat header with the selected user's information
        chatUserName.setText(nameLabel.getText());
        chatUserStatus.setText(statusLabel.getText());

        // تحديث لون الحالة بناءً على النص
        String status = statusLabel.getText();
        chatUserStatus.getStyleClass().clear(); // إزالة أي تنسيقات سابقة
        chatUserStatus.getStyleClass().add("chat-user-status"); // إضافة التنسيق الأساسي

        if (status.equalsIgnoreCase("Online")) {
            chatUserStatus.getStyleClass().add("online");
        } else if (status.equalsIgnoreCase("Offline")) {
            chatUserStatus.getStyleClass().add("offline");
        } else if (status.equalsIgnoreCase("Busy")) {
            chatUserStatus.getStyleClass().add("busy");
        } else if (status.equalsIgnoreCase("Away")) {
            chatUserStatus.getStyleClass().add("away");
        }

        // تحديث صورة المستخدم
        ImageView contactImageView = (ImageView) contactItem.getChildren().get(0);
        Image contactImage = contactImageView.getImage();
        chatUserImage.setImage(contactImage);
        chatUserImage.setFitHeight(60); // زيادة حجم الصورة
        chatUserImage.setFitWidth(60);  // زيادة حجم الصورة

        // Clear the chat box before starting a new conversation
        chatBox.getChildren().clear();

        // إخفاء اللوجو وإظهار منطقة الشات
        logoPane.setVisible(false);
        chatArea.setVisible(true);

        // جعل chatArea قابلة للتركيز
        chatArea.requestFocus();
    }

    @FXML
    private void handleSendMessage() {
        try {
            String message = messageField.getText().trim();
            if (!message.isEmpty()) {
                // User's message
                HBox messageBubble = createMessageBubble(message, true);
                chatBox.getChildren().add(messageBubble);

                // Clear the input field
                messageField.clear();

                // Add an automatic reply (optional)
                String reply = "This is an automatic reply.";
                HBox replyBubble = createMessageBubble(reply, false);
                chatBox.getChildren().add(replyBubble);
            }
        } catch (Exception e) {
            System.err.println("Error in handleSendMessage: " + e.getMessage());
        }
    }

    @FXML
    private void handleKeyPressed(KeyEvent event) {
        // عند الضغط على زر Enter
        if (event.getCode().toString().equals("ENTER")) {
            handleSendMessage(); // إرسال الرسالة
        }
    }

    private HBox createMessageBubble(String message, boolean isUser) {
        HBox bubbleContainer = new HBox();
        bubbleContainer.getStyleClass().add("bubble-container");
        bubbleContainer.setAlignment(isUser ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT); // استخدام Pos هنا

        StackPane bubbleWrapper = new StackPane();

        // إضافة الذيل
        Polygon tail = new Polygon();
        if (isUser) {
            tail.getPoints().addAll(20.0, 0.0, 10.0, 10.0, 20.0, 20.0);
            tail.getStyleClass().add("bubble-right-tail");
        } else {
            tail.getPoints().addAll(0.0, 0.0, 10.0, 10.0, 0.0, 20.0);
            tail.getStyleClass().add("bubble-left-tail");
        }

        StackPane bubble = new StackPane();
        bubble.getStyleClass().add(isUser ? "bubble-right" : "bubble-left");

        Label messageText = new Label(message);
        messageText.getStyleClass().add("bubble-text");
        bubble.getChildren().add(messageText);

        bubbleWrapper.getChildren().addAll(tail, bubble);
        bubbleContainer.getChildren().add(bubbleWrapper);
        return bubbleContainer;
    }

    // Event handlers for the sidebar buttons
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
}