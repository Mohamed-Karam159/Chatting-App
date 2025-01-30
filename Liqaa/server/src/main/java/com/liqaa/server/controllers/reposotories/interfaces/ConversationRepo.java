package com.liqaa.server.controllers.reposotories.interfaces;

import com.liqaa.shared.models.entities.Conversation;

import java.util.List;

public interface ConversationRepo
{
    int createDirectConversation();
    int createGroupConversation(int group_id);
    Conversation getConversation(int conversation_id);
    Conversation getConversationByGroup(int groupId);
    List<Conversation> getConversations(Integer[] ids);
    void deleteConversation(int conversation_id);
}
