package com.liqaa.client.controllers.services.implementations;

import com.liqaa.client.controllers.services.interfaces.ContactService;
import com.liqaa.client.network.ServerConnection;
import com.liqaa.shared.models.entities.User;
import com.liqaa.shared.models.entities.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactServiceImpl implements ContactService {
    private static ContactService instance;

    private ContactServiceImpl() {
    }

    public static ContactService getInstance() {
        if (instance == null) {
            instance = new ContactServiceImpl();
        }
        return instance;
    }

    @Override
    public ObservableList<User> getContacts(int userId) {
        ObservableList<User> contacts = FXCollections.observableArrayList();
        // التواصل مع السيرفر للحصول على قائمة الاتصالات
        try {
            contacts.addAll(ServerConnection.getServer().getUserFriends(userId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public ObservableList<Category> getCategoriesForContact(int userId, int contactId) {
        ObservableList<Category> categories = FXCollections.observableArrayList();
        // التواصل مع السيرفر للحصول على الفئات الخاصة بالاتصال
        try {
            categories.addAll(ServerConnection.getServer().getCategoriesForContact(userId, contactId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public boolean deleteContact(int userId, int contactId) {
        // التواصل مع السيرفر لحذف الاتصال
        try {
            ServerConnection.getServer().deleteContact(userId, contactId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean renameCategory(int categoryId, String newName) {
        try {
            ServerConnection.getServer().renameCategory(categoryId, newName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


}
