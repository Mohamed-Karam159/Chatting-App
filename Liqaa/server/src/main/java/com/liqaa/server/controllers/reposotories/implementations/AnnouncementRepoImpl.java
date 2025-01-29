package com.liqaa.server.controllers.reposotories.implementations;

import com.liqaa.server.controllers.reposotories.interfaces.AnnouncementRepo;
import com.liqaa.server.util.DatabaseManager;
import com.liqaa.shared.models.entities.Announcement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementRepoImpl implements AnnouncementRepo {
    @Override
    public int addNew(Announcement announcement) throws SQLException {
        try(Connection connection = DatabaseManager.getInstance().getConnection();) {
            try(PreparedStatement statement = connection.prepareStatement("INSERT INTO announcements (title, content) VALUE (?, ?)");) {
                statement.setString(1, announcement.getTitle());
                statement.setString(2, announcement.getContent());
                statement.executeQuery();
                ResultSet resultSet = statement.getGeneratedKeys(); /** To get all the auto-generated IDs created fot these inserted rows */
                int id = -1;
                while (resultSet.next()) {
                    id = resultSet.getInt("id");
                }
                return id;
            }
        }
    }

    @Override
    public Announcement getById(int id) throws SQLException {
        /** try with resources to close connection, statement, and resultSet */
        try(Connection connection = DatabaseManager.getInstance().getConnection();) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT id, title, content, sent_at FROM announcements where id = ?");) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                Announcement announcement = null;
                if (resultSet.next()) {
                    announcement = new Announcement(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getTimestamp("sent_at").toLocalDateTime());
                }
                return announcement;
            }
        }
    }

    @Override
    public List<Announcement> getAll() throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT id, title, content, sent_at FROM announcement");
        ResultSet resultSet = statement.executeQuery();
        List<Announcement> announcements = new ArrayList<> ();
        while (resultSet.next()){
            Announcement announcement = new Announcement(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getTimestamp("sent_at").toLocalDateTime());
            announcements.add(announcement);
        }
        return announcements;
    }

    @Override
    public boolean update(Announcement announcement) throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE announcements SET title = ?, content = ?, sent_at = ? WHERE id = ?");
        statement.setString(1, announcement.getTitle());
        statement.setString(2, announcement.getContent());
        statement.setTimestamp(3, Timestamp.valueOf(announcement.getSentAt()));
        statement.setInt(4, announcement.getId());
        return (statement.executeUpdate() == 1);
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
