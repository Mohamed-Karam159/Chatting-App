package com.liqaa.server.controllers.reposotories.interfaces;

import com.liqaa.shared.models.entities.Announcement;

import java.sql.SQLException;
import java.util.List;

public interface AnnouncementRepo {
    /** int? can be used to return the id of the last inserted row, the number of rows inserted successfully (helpful especially in case of using batch execution) */
    public int addNew(Announcement announcement) throws SQLException;
    public Announcement getById(int id) throws SQLException;
    public List<Announcement> getAll() throws SQLException;
    public boolean update(Announcement announcement) throws SQLException;
    public boolean delete(int id) throws SQLException;
}