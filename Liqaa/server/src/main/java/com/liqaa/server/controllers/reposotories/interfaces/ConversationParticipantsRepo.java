package com.liqaa.server.controllers.reposotories.interfaces;

import com.liqaa.shared.models.entities.ConversationParticipant;

import java.util.List;

public interface ConversationParticipantsRepo
{
  boolean addParticipant(int userId, int conversationId);
  boolean removeParticipant(int userId, int conversationId);
  ConversationParticipant getInfo(int userId, int conversationId);
  List<ConversationParticipant> getParticipants(int conversationId);
  List<ConversationParticipant> getConversations(int userId);
  boolean updateConversationInfo(int userId, int conversationId, int unreadMsgCount, String lastMsgTime);
  boolean updateUnreadMsgCount(int userId, int conversationId, int unreadMsgCount);
  boolean updateLastMsgTime(int userId, int conversationId, String lastMsgTime);
}
//conversation_id INT NOT NULL,
//user_id INT NOT NULL,
//joined_at DATETIME DEFAULT CURRENT_DATETIME,
//unread_msg_count INT DEFAULT 0,
//last_msg_time DATETIME NULL,