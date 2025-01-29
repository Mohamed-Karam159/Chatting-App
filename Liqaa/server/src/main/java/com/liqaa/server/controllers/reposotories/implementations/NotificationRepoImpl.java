package com.liqaa.server.controllers.reposotories.implementations;

import com.liqaa.server.controllers.reposotories.interfaces.NotificationRepo;
import com.liqaa.shared.models.entities.Notification;
import java.util.List;

public class NotificationRepoImpl implements NotificationRepo {
    @Override
    public int addNew(Notification notification) {
        return 0;
    }

    @Override
    public Notification getById(int id) {
        return null;
    }

    @Override
    public List<Notification> getAll() {
        return List.of();
    }

    @Override
    public boolean update(Notification notification) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
