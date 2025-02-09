package com.liqaa.client.controllers.services.implementations;

import com.liqaa.shared.models.ChatInfo;
import com.liqaa.shared.models.entities.Category;
import com.liqaa.shared.models.entities.Conversation;
import com.liqaa.shared.models.entities.Message;
import com.liqaa.shared.models.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class DataCenter
{
    private static DataCenter instance = null;

    private ObservableList<Category> categories = FXCollections.observableArrayList();
    private ObservableList<ChatInfo> chatList = FXCollections.observableArrayList();
    private ObservableList<Message> messages = FXCollections.observableArrayList();

    private Category currentCategory ;
    private User currentUser = new User(1,"Ibrahim","ibrahim@gmail.com","password",null);
    private Conversation currentConversation;

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

    public Conversation getCurrentConversation()
    {
        return currentConversation;
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

    public void setCurrentConversation(Conversation conversation)
    {
        currentConversation = conversation;
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
                new Category(-2, "All"),
                new Category(-3, "Unread"),
                new Category(-4, "Groups")
        );
        setCurrentCategory(categories.get(0));
    }

}
