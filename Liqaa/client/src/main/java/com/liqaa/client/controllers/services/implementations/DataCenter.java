package com.liqaa.client.controllers.services.implementations;

import com.liqaa.shared.models.ChatInfo;
import com.liqaa.shared.models.entities.Category;
import com.liqaa.shared.models.entities.Conversation;
import com.liqaa.shared.models.entities.Message;
import com.liqaa.shared.models.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataCenter
{
    private static DataCenter instance = null;

    private ObservableList<Category> categories = FXCollections.observableArrayList();
    private ObservableList<ChatInfo> chatList = FXCollections.observableArrayList();
    private ObservableList<Message> messages = FXCollections.observableArrayList();

    private Category currentCategory ;
    private User currentUser = new User(1,"Ibrahim","ibrahim@gmail.com","password",null);
    private ChatInfo currentChat;
    private int currentConversationId;

    private DataCenter() {}

    public static DataCenter getInstance()
    {
        if (instance == null)
            instance = new DataCenter();

        return instance;
    }

    public ObservableList getCategories()
    {
        return categories;
    }

    public ObservableList<ChatInfo> getChats()
    {
        return chatList;
    }

    public ObservableList<Message> getMessages()
    {
        return messages;
    }

    public User getCurrentUser()
    {
        return currentUser;
    }

    public int getcurrentUserId()
    {
        return currentUser.getId();
    }

    public ChatInfo getCurrentChat()
    {
        return currentChat;
    }

    public void setCurrentChat(ChatInfo chat)
    {
        currentChat = chat;
    }

    public int getCurrentConversationId()
    {
        return currentChat.getConversationId();
    }

    public Category getCurrentCategory()
    {
        return currentCategory;
    }


    public void setCurrentCategory(Category category)
    {
        currentCategory = category;
    }

    public void setCurrentUser(User user)
    {
        currentUser = user;
    }

    public void setCurrentConversationId(int conversationId)
    {
        currentConversationId = conversationId;
    }

    public void flushData()
    {
        chatList.clear();
        messages.clear();
        categories.clear();
//        initializeDefaultCategories();
    }
    public void initializeDefaultCategories()
    {
        categories.setAll(
                new Category(-2, 0, "All"),
                new Category(-3, 0, "Unread"),
                new Category(-4, 0, "Groups")
        );
        setCurrentCategory(categories.get(0));
    }

}
