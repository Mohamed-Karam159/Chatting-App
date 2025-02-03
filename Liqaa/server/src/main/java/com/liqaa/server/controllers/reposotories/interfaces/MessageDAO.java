package com.liqaa.server.controllers.reposotories.interfaces;

import com.liqaa.shared.models.entities.Message;
import java.util.List;
import java.util.Optional;

public interface MessageDAO {
    void save(Message message);
    Optional<Message> findById(int id);
    List<Message> findAll();
    void update(Message message);
    void delete(int id);
}
