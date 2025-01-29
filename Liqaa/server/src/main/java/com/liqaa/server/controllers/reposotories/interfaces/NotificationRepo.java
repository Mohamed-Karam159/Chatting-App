package com.liqaa.server.controllers.reposotories.interfaces;

import com.liqaa.shared.models.entities.Notification;
import java.util.List;

public interface NotificationRepo {
    /** int? can be used to return the id of the last inserted row, the number of rows inserted successfully (helpful especially in case of using batch execution) */
    public int addNew(Notification notification);
    public Notification getById(int id);
    public List<Notification> getAll();
    public boolean update(Notification notification);
    public boolean delete(int id);
}