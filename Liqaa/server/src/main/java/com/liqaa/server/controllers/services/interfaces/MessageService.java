package com.liqaa.server.controllers.services.interfaces;

import com.liqaa.shared.models.entities.FileMessage;
import com.liqaa.shared.models.entities.Message;

import java.nio.file.Path;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface MessageService {
    List<Message> getMessagesByConversationId(int conversationId); // Without pagination
    List<Message> getMessagesByConversationId(int conversationId, int offset, int limit); // With pagination
    int sendMessage(Message message); // Send a message
    int getUnreadMessageCount(int conversationId, int userId); // Get unread message count
    void markMessagesAsSeen(int conversationId, int userId); // Mark messages as seen
    FileMessage getFileInfo(int messageId);
    public int sendFile(FileMessage fileInfo);
    void deleteMessage(int messageId);
    void updateFileInfo(int messageId, Path filePath);
}