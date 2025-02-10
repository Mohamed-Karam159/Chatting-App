package com.liqaa.client.controllers.services.interfaces;

import com.liqaa.shared.models.entities.User;
import com.liqaa.shared.models.entities.Category;
import javafx.collections.ObservableList;

public interface ContactService {
    ObservableList<User> getContacts(int userId); // الحصول على قائمة الاتصالات
    ObservableList<Category> getCategoriesForContact(int userId, int contactId); // الحصول على الفئات الخاصة بالاتصال
    boolean deleteContact(int userId, int contactId); // حذف الاتصال
    boolean renameCategory(int categoryId, String newName); // تغيير اسم الفئة
}