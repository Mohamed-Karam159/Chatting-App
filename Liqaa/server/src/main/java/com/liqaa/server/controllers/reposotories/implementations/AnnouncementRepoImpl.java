package com.liqaa.server.controllers.reposotories.implementations;

import com.liqaa.server.controllers.reposotories.interfaces.AnnouncementRepo;
import com.liqaa.server.util.DatabaseManager;
import com.liqaa.shared.models.entities.Announcement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.liqaa.server.util.DatabaseManager.*;

public class AnnouncementRepoImpl implements AnnouncementRepo {
    @Override
    public int createAnnouncement(Announcement announcement) throws SQLException {
        if (announcement == null) {
            System.err.println("Error creating announcement: Announcement is null");
            return -1;
        } /** try with resources to close connection, statement, and resultSet */
        try(Connection connection = DatabaseManager.getConnection();) {
            String query = "INSERT INTO announcements (title, content) VALUE (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setString(1, announcement.getTitle());
                statement.setString(2, announcement.getContent());
                statement.executeQuery();
                try (ResultSet resultSet = statement.getGeneratedKeys();) { /** To get all the auto-generated IDs created fot these inserted rows */
                    int id = -1;
                    while (resultSet.next()) {
                        id = resultSet.getInt("id");
                    }
                    if (id == -1) {
                        System.err.println("Failed to create announcement");
                    } else {
                        System.out.println("announcement is created successfully");
                    }
                    return id;
                }
            }
        }
    }

    @Override
    public Announcement getAnnouncementById(int id) throws SQLException {
        if(id <= 0) {
            System.err.println("Error getting announcement: Invalid ID");
            return null;
        }
        try(Connection connection = DatabaseManager.getConnection();) {
            String query = "SELECT id, title, content, sent_at FROM announcements where id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery();) {
                    if (resultSet.next()) {
                        return new Announcement(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getTimestamp("sent_at").toLocalDateTime());
                    }
                    return null;
                }
            }
        }
    }

    @Override
    public List<Announcement> getAllAnnouncements() throws SQLException {
        try(Connection connection = DatabaseManager.getConnection();) {
            String query = "SELECT id, title, content, sent_at FROM announcement";
            try (PreparedStatement statement = connection.prepareStatement(query);) {
                try (ResultSet resultSet = statement.executeQuery();) {
                    List<Announcement> announcements = new ArrayList<>();
                    while (resultSet.next()) {
                        Announcement announcement = new Announcement(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getTimestamp("sent_at").toLocalDateTime());
                        announcements.add(announcement);
                    }
                    return announcements;
                }
            }
        }
    }

    @Override
    public boolean updateAnnouncement(Announcement announcement) throws SQLException {
        if (announcement == null) {
            System.err.println("Error updating announcement: Announcement is null");
            return false;
        }
        try(Connection connection = DatabaseManager.getConnection();) {
            String query = "UPDATE announcements SET title = ?, content = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setString(1, announcement.getTitle());
                statement.setString(2, announcement.getContent());
                statement.setInt(4, announcement.getId());
                if (statement.executeUpdate() <= 0) {
                    System.err.println("Failed to update announcement");
                } else {
                    System.out.println("announcement is updated successfully");
                }
                return (statement.executeUpdate() > 0);
            }
        }
    }

    @Override
    public boolean deleteAnnouncement(int id) throws SQLException {
        if(id <= 0) {
            System.err.println("Error deleting announcement: Invalid ID");
            return false;
        }
        try(Connection connection = DatabaseManager.getConnection();) {
            String query = "DELETE FROM announcements WHERE id = ?";
            try(PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setInt(1, id);
                int rowsAffected = statement.executeUpdate(); /** number of deleted rows */
                if (rowsAffected <= 0) {
                    System.err.println("Failed to delete announcement");
                } else {
                    System.out.println("announcement is deleted successfully");
                }
                return (rowsAffected > 0);
            }
        }
    }
}