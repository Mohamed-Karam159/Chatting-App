package com.liqaa.client.controllers.FXMLcontrollers.components;

import com.liqaa.client.controllers.services.implementations.BaseMessageController;
import com.liqaa.shared.models.entities.FileMessage;
import com.liqaa.shared.models.entities.Message;
import com.liqaa.shared.models.enums.MessageType;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;

import java.io.File;

import static com.liqaa.client.controllers.FXMLcontrollers.PrimaryController.isNewFileMessage;
import static com.liqaa.client.controllers.FXMLcontrollers.PrimaryController.selectedFile;


public class FileMessageController extends BaseMessageController {
    @FXML private Label timeLabel;
    @FXML private ImageView statusIcon;
    @FXML
    private AnchorPane footerContainer;
    @FXML
    private ImageView download;
    @FXML
    private Text fileName;
    @FXML
    private Text fileSize;
    @FXML
    private HBox messageBubble;
    @FXML
    private ImageView fileIcon;
    @FXML
    private Text fileType;

FileMessage fileMessage = new FileMessage();

    @Override
    public void setMessage(Message message,int currentUserId)
    {

        if (messageBubble == null) {
            throw new IllegalStateException("messageBubble not initialized! Check FXML file");
        }

        if(message.getType()!= MessageType.TEXT)
        {
            if(isNewFileMessage)
            {
                fileMessage = new FileMessage(1, message.getId(), "example.txt", 1024, "/path/to/file/example.txt");
                fileMessage.setFileName(selectedFile.getName());
//                System.out.println(fileMessage.getFileName());
                fileMessage.setFileSize((int)(selectedFile.length()/1024));
//                System.err.println(fileMessage.getFileSize());
                // todo: call the service that set file info in the database
                isNewFileMessage=false;
            }
            else
            {
                // todo: call the service that gets file info from sender
                fileMessage = new FileMessage(2, message.getId(), "example.txt", 1024, "/path/to/file/example.txt");

            }
        }

        boolean isCurrentUser = message.getSenderId() == currentUserId;

        fileName.setText(fileMessage.getFileName());
//        System.out.println(fileMessage.getFileName());
        fileSize.setText(fileMessage.getFileSize() + " KB");
        fileType.setText(message.getType().toString());
        if(isCurrentUser)
            timeLabel.setText(formatTime(message.getSentAt()));
        else
            timeLabel.setText(formatTime(message.getReceivedAt()));
        setFileIcon(fileIcon,message);
        setStatusIndicator(statusIcon, message);

        // Ensure the text flows naturally inside the bubble
        HBox.setHgrow(messageBubble, Priority.NEVER);

        }


    @FXML
    public void downloadFile(MouseEvent event)
    {
        System.out.println("Download button clicked!"); // Debugging output

        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);

        System.out.println("Selected directory: " + selectedDirectory.getAbsolutePath());
        if(selectedDirectory != null)
        {
            //TODO: call download function here and pass the file path
        }
    }
}