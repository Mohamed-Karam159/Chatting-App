package com.liqaa.client.controllers.FXMLcontrollers;

import com.liqaa.client.controllers.FXMLcontrollers.components.CategoryListController;
import com.liqaa.client.controllers.FXMLcontrollers.components.ChatItemController;
import com.liqaa.shared.models.ChatInfo;
import com.liqaa.shared.models.enums.ConversationType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.shape.Circle;
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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import com.liqaa.shared.models.enums.CurrentStatus;

public class PrimaryController
{
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
    private ImageView contact_btn;
    @FXML
    private ImageView logout_btn;
    @FXML
    private ImageView chatbot_btn;
    @FXML
    private ImageView home_btn;
    @FXML
    private ImageView profile_btn;
    @FXML
    private ImageView notification_btn;
    @FXML
        private ImageView settings_btn;
        @FXML
        private CategoryListController nullController;

    //    @FXML
    //    private ListView chatListView;

        @FXML private ListView<ChatInfo> chatListView;
        private ObservableList<ChatInfo> chatList= FXCollections.observableArrayList();

        @FXML
        public void initialize()
        {
            chatListView.setCellFactory(new Callback<ListView<ChatInfo>, ListCell<ChatInfo>>()
            {
                @Override
                public ListCell<ChatInfo> call(ListView<ChatInfo> param) {
                    return new ListCell<>() {
                        @Override
                        protected void updateItem(ChatInfo chat, boolean empty) {
                            super.updateItem(chat, empty);
                            if (empty || chat == null) {
                                setGraphic(null);
                            } else {
                                setGraphic(createChatCard(chat));
                                setOnMouseClicked(event -> handleChatClick(chat));
                                // TODO: call service that get the conversation messages and clear unread messages on handleChatClick func
                            }
                        }
                    };
                }
            });
            try {
                chatArea.setVisible(false);
                logoPane.setVisible(true);
                chatArea.setOnKeyPressed(event ->
                {
                    if (event.getCode() == KeyCode.ESCAPE) {
                        chatArea.setVisible(false);
                        logoPane.setVisible(true);
                    }
                });
            } catch (Exception e) {
                System.err.println("Error initializing contacts: " + e.getMessage());
            }


            loadChatData();
            chatListView.setItems(chatList);
        }


        private Parent createChatCard(ChatInfo chatInfo)
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/liqaa/client/view/fxml/components/chatItem.fxml"));
                Parent chatCard = loader.load();

                ChatItemController controller = loader.getController();
                controller.setChatInfo(chatInfo);

                return chatCard;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        private void loadChatData() {
            // Sample data (replace with real database data)
            // TODO: load all chats here
            chatList.addAll(List.of(
                    new ChatInfo(1, ConversationType.DIRECT, 101, "Alice", CurrentStatus.OFFLINE, null, 2, LocalDateTime.now(), LocalDateTime.now()),
                    new ChatInfo(2, ConversationType.DIRECT, 102, "Ibrahim Diab", CurrentStatus.AVAILABLE, null, 0, LocalDateTime.now(), LocalDateTime.now()),
                    new ChatInfo(3, ConversationType.GROUP, 202, "Project Team", null, null, 0, LocalDateTime.now(), LocalDateTime.now()),
                    new ChatInfo(4, ConversationType.DIRECT, 103, "John Doe", CurrentStatus.BUSY, null, 1, LocalDateTime.now(), LocalDateTime.now()),
                    new ChatInfo(5, ConversationType.DIRECT, 104, "Jane Doe", CurrentStatus.AWAY, null, 3, LocalDateTime.now(), LocalDateTime.now()),
                    new ChatInfo(6, ConversationType.GROUP, 203, "Friends", null, null, 2, LocalDateTime.now(), LocalDateTime.now()),
                    new ChatInfo(7, ConversationType.DIRECT, 105, "Bob Smith", CurrentStatus.AVAILABLE, null, 0, LocalDateTime.now(), LocalDateTime.now()),
                    new ChatInfo(8, ConversationType.DIRECT, 106, "Michael Davis", CurrentStatus.OFFLINE, null, 1, LocalDateTime.now(), LocalDateTime.now()),
                    new ChatInfo(9, ConversationType.DIRECT, 107, "David Lee", CurrentStatus.BUSY, null, 2, LocalDateTime.now(), LocalDateTime.now()),
                    new ChatInfo(10, ConversationType.DIRECT, 108, "Sarah Johnson", CurrentStatus.AWAY, null, 3, LocalDateTime.now(), LocalDateTime.now()),
                    new ChatInfo(11, ConversationType.GROUP, 204, "Colleagues", null, null, 4, LocalDateTime.now(), LocalDateTime.now()),
                    new ChatInfo(12, ConversationType.DIRECT, 109, "Kevin White", CurrentStatus.AVAILABLE, null, 0, LocalDateTime.now(), LocalDateTime.now()),
                    new ChatInfo(13, ConversationType.DIRECT, 110, "Laura Brown", CurrentStatus.OFFLINE, null, 1, LocalDateTime.now(), LocalDateTime.now()),
                    new ChatInfo(14, ConversationType.DIRECT, 111, "Tommy Jackson", CurrentStatus.BUSY, null, 2, LocalDateTime.now(), LocalDateTime.now()),
                    new ChatInfo(15, ConversationType.DIRECT, 112, "Christine Martin", CurrentStatus.AWAY, null, 3, LocalDateTime.now(), LocalDateTime.now()),
                    new ChatInfo(16, ConversationType.GROUP, 205, "Family", null, null, 4, LocalDateTime.now(), LocalDateTime.now())
            ));
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
//        contactsListView.getItems().add(contactItem);
    }

    private void handleChatClick(ChatInfo chatInfo)
    {
        // Update the chat header with the selected user's information
        chatUserName.setText(chatInfo.getName());
        CurrentStatus status = chatInfo.getStatus();
        chatUserStatus.setText(status != null ? status.name() : " ");
        // Update status color based on the status
        chatUserStatus.getStyleClass().clear(); // Remove any previous styles
        chatUserStatus.getStyleClass().add("chat-user-status"); // Add the base style
        if (status == CurrentStatus.AVAILABLE) {
            chatUserStatus.getStyleClass().add("online");
        } else if (status == CurrentStatus.OFFLINE) {
            chatUserStatus.getStyleClass().add("offline");
        } else if (status == CurrentStatus.BUSY) {
            chatUserStatus.getStyleClass().add("busy");
        } else if (status == CurrentStatus.AWAY) {
            chatUserStatus.getStyleClass().add("away");
        }
        // Update user image
        if (chatInfo.getImage() != null) {
            chatUserImage.setImage(new Image(new ByteArrayInputStream(chatInfo.getImage())));
        } else {
            chatUserImage.setImage(new Image(getClass().getResourceAsStream("/com/liqaa/client/view/images/defaultProfileImage.png")));
        }
        chatUserImage.setFitHeight(60); // Increase image size
        chatUserImage.setFitWidth(60);  // Increase image size
        // Clear the chat box before starting a new conversation
        chatBox.getChildren().clear();
        // Hide the logo and show the chat area
        logoPane.setVisible(false);
        chatArea.setVisible(true);
        // Make chatArea focusable
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
    @FXML
    public void profile_action(MouseEvent mouseEvent) {
        System.out.println("profile btn is clicked");
    }
    @FXML
    public void home_action(MouseEvent mouseEvent) {
        System.out.println("home btn is clicked");
    }
    @FXML
    public void notification_action(MouseEvent mouseEvent) {
        System.out.println("notification btn is clicked");
    }
    @FXML
    public void contact_action(MouseEvent mouseEvent) {
        System.out.println("contact btn is clicked");
    }
    @FXML
    public void chatbot_action(MouseEvent mouseEvent) {
        System.out.println("chatbot btn is clicked");
    }
    @FXML
    public void settings_action(MouseEvent mouseEvent) {
        System.out.println("settings btn is clicked");
    }
    @FXML
    public void logout_action(MouseEvent mouseEvent) {
        System.out.println("logout btn is clicked");
    }
}