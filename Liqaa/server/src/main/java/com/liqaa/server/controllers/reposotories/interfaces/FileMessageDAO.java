package com.liqaa.server.controllers.reposotories.interfaces;

import com.liqaa.shared.models.entities.FileMessage;

import java.util.List;

public interface FileMessageDAO {
    void save(FileMessage fileMessage);
    FileMessage findById(int id);
    List<FileMessage> findAll();
    void update(FileMessage fileMessage);
    void delete(int id);
}
