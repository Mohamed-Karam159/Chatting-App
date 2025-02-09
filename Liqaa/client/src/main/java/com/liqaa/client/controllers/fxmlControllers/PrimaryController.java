package com.liqaa.client.controllers.FXMLcontrollers;

import com.liqaa.client.controllers.FXMLcontrollers.components.CategoryListController;
import com.liqaa.client.controllers.FXMLcontrollers.components.ChatItemController;
import com.liqaa.client.controllers.services.implementations.BaseMessageController;
import com.liqaa.client.controllers.services.implementations.DataCenter;
import com.liqaa.shared.models.ChatInfo;
import com.liqaa.shared.models.entities.FileMessage;
import com.liqaa.shared.models.entities.Message;
import com.liqaa.shared.models.enums.ConversationType;
import com.liqaa.shared.models.enums.MessageType;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
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
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.scene.shape.Polygon;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import com.liqaa.shared.models.enums.CurrentStatus;

public class PrimaryController
{
    @FXML
    private TextField messageField;
    @FXML
    private ImageView chatUserImage;
    @FXML
    private Label chatUserName;
    @FXML
    private Label chatUserStatus;
    @FXML
    private StackPane logoPane;
    @FXML
    private VBox chatArea;
    @FXML
    private StackPane rightPane;
    @FXML
    private ImageView videoCallIcon;
    @FXML
    private ImageView voiceCallIcon;
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

       public static boolean isNewFileMessage= false;
       public static File selectedFile = null;

        @FXML private ListView<ChatInfo> chatListView;
        private ObservableList<ChatInfo> chatList= FXCollections.observableArrayList();
    @FXML
    private ListView<Message> chatBox;
    private ObservableList<Message> messages = FXCollections.observableArrayList();
    @FXML
    private ImageView attachFile;
    @FXML
    private ImageView emoji;
    @FXML
    private TextField search;
    @FXML
    private ImageView send;

    public int getId() {return 1;}
    private int currentUserId = getId();
    private int getCurrentConversationId() {return 1;}

//    private void loadDummyMessages() {
//        messages.addAll(
//                new Message(1, currentUserId, 1, "Hello!", MessageType.TEXT, LocalDateTime.now(),
//                        true, LocalDateTime.now(), LocalDateTime.now()),
//                new Message(2, 2, 1, "Hi there!", MessageType.TEXT, LocalDateTime.now(),
//                        true, LocalDateTime.now(), null),
//                new Message(3, currentUserId, 1, "How are you?", MessageType.TEXT, LocalDateTime.now(),
//                        true, null, null),
//                new Message(4, 2, 1, "I'm doing well, thank you!", MessageType.TEXT, LocalDateTime.now(),
//                        true, null, null),
//                new Message(5, currentUserId, 1, "What are you doing today?", MessageType.TEXT, LocalDateTime.now(),
//                        true, null, null),
//                new Message(6, 2, 1, "I'm working on a project.", MessageType.TEXT, LocalDateTime.now(),
//                        true, null, LocalDateTime.now())
//        );
//    }
    private ListCell<Message> createMessageCell(ListView<Message> param)
    {
        return new ListCell<>() {
            private Node currentRoot;
            private BaseMessageController currentController;

            @Override
            protected void updateItem(Message message, boolean empty)
            {
                super.updateItem(message, empty);

                if (empty || message == null) {
                    setGraphic(null);
                    return;
                }

                try {
                    String fxmlFile = null;
                    if(message.getSenderId() == currentUserId)
                    {
                        fxmlFile = switch (message.getType())
                        {
                            case TEXT -> "/com/liqaa/client/view/fxml/components/sentTextMessage.fxml";
                            default -> "/com/liqaa/client/view/fxml/components/sentFileMessage.fxml";
                        };
                    }
                    else
                    {
                        fxmlFile = switch (message.getType())
                        {
                            case TEXT -> "/com/liqaa/client/view/fxml/components/receivedTextMessage.fxml";
                            default -> "/com/liqaa/client/view/fxml/components/receivedFileMessage.fxml";
                        };
                    }

                    FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
                    Node root = loader.load();
                    BaseMessageController controller = loader.getController();

                    if (root != currentRoot) {
                        currentRoot = root;
                        currentController = controller;
                        setGraphic(root);
                    }

                    currentController.setMessage(message, currentUserId);

                    HBox container = (HBox) root;
                    container.setAlignment(message.getSenderId() == currentUserId ?
                            Pos.CENTER_RIGHT : Pos.CENTER_LEFT);

                } catch (IOException e)
                {
                    e.printStackTrace();
                    setGraphic(new Label("Error loading message"));
                }
            }
        };
    }


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

            chatBox.setItems(messages);
            chatBox.setCellFactory(param -> createMessageCell((ListView<Message>) param));

            // Rest of existing initialization
//            loadDummyMessages();

            chatList.setAll(DataCenter.getInstance().getChats());
//            loadChatData();
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
        chatUserName.setText(chatInfo.getName());
        CurrentStatus status = chatInfo.getStatus();
        chatUserStatus.setText(status != null ? status.name() : " ");
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
        chatBox.getItems().clear();

        messages.clear();

//        loadMessagesForChat(chatInfo.getConversationId());
        loadMessagesForChat(1);

        logoPane.setVisible(false);
        chatArea.setVisible(true);
        chatArea.requestFocus();

        Platform.runLater(() ->
        {
            if (!messages.isEmpty()) {
                chatBox.scrollTo(messages.size() - 1);
                System.out.println("Scrolling to last message");
            }
        });
    }

    private void loadMessagesForChat(int chatId) {
        // Replace with actual message loading logic
        messages.addAll(
                new Message(1, currentUserId, chatId, "Hello! (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(2, 2, chatId, "Hi there! (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(3, currentUserId, chatId, "What's up? (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(4, 2, chatId, "Not much. Just hanging out. (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(5, currentUserId, chatId, "Want to grab lunch? (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(6, 2, chatId, "I'm not hungry right now. (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(2, 2, chatId, "Hi there! (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(3, currentUserId, chatId, "What's up? (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(4, 2, chatId, "Not much. Just hanging out. (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(5, currentUserId, chatId, "Want to grab lunch? (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(6, 2, chatId, "I'm not hungry right now. (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(2, 2, chatId, "Hi there! (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(3, currentUserId, chatId, "What's up? (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(4, 2, chatId, "Not much. Just hanging out. (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(5, currentUserId, chatId, "Want to grab lunch? (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(6, 2, chatId, "I'm not hungry right now. (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(2, 2, chatId, "Hi there! (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(3, currentUserId, chatId, "What's up? (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(4, 2, chatId, "Not much. Just hanging out. (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(5, currentUserId, chatId, "Want to grab lunch? (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(6, 2, chatId, "I'm not hungry right now. (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(2, 2, chatId, "Hi there! (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(3, currentUserId, chatId, "What's up? (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(4, 2, chatId, "Not much. Just hanging out. (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(5, currentUserId, chatId, "Want to grab lunch? (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(6, 2, chatId, "I'm not hungry right now. (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(2, 2, chatId, "Hi there! (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(3, currentUserId, chatId, "What's up? (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(4, 2, chatId, "Not much. Just hanging out. (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(5, currentUserId, chatId, "Want to grab lunch? (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(6, 2, chatId, "I'm not hungry right now. (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(2, 2, chatId, "Hi there! (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(3, 2, chatId, "What's up? (Chat " + chatId + ")",
                        MessageType.TEXT, LocalDateTime.now(), true, null, null),
                new Message(4, 2, chatId, "Not much. Just hanging out. (Chat " + chatId + ")",
                        MessageType.IMAGE, LocalDateTime.now(), true, null, null),
                new Message(5, 2, chatId, "Want to grab lunch? (Chat " + chatId + ")",
                        MessageType.DOCUMENT, LocalDateTime.now(), true, null,LocalDateTime.now()),
                new Message(6, currentUserId, chatId, "I'm not hungry right now. (Chat " + chatId + ")",
                        MessageType.AUDIO, LocalDateTime.now(),false, null, null)
        );
    }

    @FXML
    private void handleSendMessage() {
        try {
            String content = messageField.getText().trim();
            if (!content.isEmpty())
            {
                Message message = new Message(currentUserId, getCurrentConversationId(), content, MessageType.TEXT, LocalDateTime.now(), true, null, null);
                messageField.clear();
                chatBox.getItems().add(message);

                Platform.runLater(() ->
                {
                    if (!messages.isEmpty()) {
                        chatBox.scrollTo(messages.size() - 1);
                        System.out.println("Scrolling to last message");
                    }
                });
                //todo: send text message
            }
        } catch (Exception e) {
            System.err.println("Error in handleSendMessage: " + e.getMessage());
        }
    }

    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            handleSendMessage();
        }
    }


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

    @FXML
    public void addEmoji(MouseEvent event)
    {
        ContextMenu emojiMenu = new ContextMenu();

        String[] emojis = {"😊", "😂", "👍", "🔥", "🎉", "🙏", "🤔","👏"};

        for (String emoji : emojis) {
            MenuItem item = new MenuItem(emoji);
            item.setOnAction(e -> messageField.appendText(emoji));
            emojiMenu.getItems().add(item);
        }

        emojiMenu.show(emoji, Side.BOTTOM, 0, 0);
    }

    @FXML
    public void attachFile(MouseEvent event)
    {
        ContextMenu attachMenu = new ContextMenu();

        String[] types = {"Document", "Image", "Audio", "Video"};

        for (String type : types) {
            MenuItem item = new MenuItem(type);
            item.setOnAction(e -> openFileChooser(type));
            attachMenu.getItems().add(item);
        }
        attachMenu.show(attachFile, Side.BOTTOM, 0, 0);
    }

    private void openFileChooser(String type)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select " + type + " File");
        MessageType messageType = null;
        switch (type) {
            case "Document":
                messageType = MessageType.DOCUMENT;
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Documents", "*.pdf", "*.docx", "*.txt"));
                break;
            case "Image":
                messageType = MessageType.IMAGE;
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
                break;
            case "Audio":
                messageType = MessageType.AUDIO;
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio", "*.mp3", "*.wav", "*.ogg"));
                break;
            case "Video":
                messageType = MessageType.VIDEO;
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Videos", "*.mp4", "*.avi", "*.mkv"));
                break;
        }

        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null)
        {
            Message message = new Message(currentUserId, getCurrentConversationId(), null, messageType, LocalDateTime.now(), true, null, null);
            int messageId=0; // todo: return of service
            FileMessage fileMessage = new FileMessage( messageId, selectedFile.getName(), selectedFile.length()/1024, null);
            //todo : send file info using service
            messageField.clear();
            chatBox.getItems().add(message);

            Platform.runLater(() ->
            {
                if (!messages.isEmpty()) {
                    chatBox.scrollTo(messages.size() - 1);
                }
            });
            isNewFileMessage = true;
            //TODO: call sendMessageFile function here and upload the file
        }
    }

}