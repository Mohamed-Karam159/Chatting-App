package com.liqaa.server.controllers.reposotories.implementations;

import com.liqaa.server.controllers.reposotories.interfaces.FileMessageDAO;
import com.liqaa.shared.models.entities.FileMessage;
import com.liqaa.server.util.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileMessageDAOImpl implements FileMessageDAO {
    private Connection connection;

    public FileMessageDAOImpl() {
        try {
            this.connection = DatabaseManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(FileMessage fileMessage) {
        String sql = "INSERT INTO FileMessages (message_id, name, size, path) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, fileMessage.getMessageId());
            stmt.setString(2, fileMessage.getFileName());
            stmt.setInt(3, fileMessage.getFileSize());
            stmt.setString(4, fileMessage.getFilePath());

            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                fileMessage.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FileMessage findById(int id) {
        String sql = "SELECT * FROM FileMessages WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new FileMessage(
                        rs.getInt("id"),
                        rs.getInt("message_id"),
                        rs.getString("name"),
                        rs.getInt("size"),
                        rs.getString("path")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<FileMessage> findAll() {
        List<FileMessage> fileMessages = new ArrayList<>();
        String sql = "SELECT * FROM FileMessages";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                fileMessages.add(new FileMessage(
                        rs.getInt("id"),
                        rs.getInt("message_id"),
                        rs.getString("name"),
                        rs.getInt("size"),
                        rs.getString("path")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fileMessages;
    }

    @Override
    public void update(FileMessage fileMessage) {
        String sql = "UPDATE FileMessages SET message_id = ?, name = ?, size = ?, path = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, fileMessage.getMessageId());
            stmt.setString(2, fileMessage.getFileName());
            stmt.setInt(3, fileMessage.getFileSize());
            stmt.setString(4, fileMessage.getFilePath());
            stmt.setInt(5, fileMessage.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM FileMessages WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Set the ID parameter for the DELETE query
            stmt.setInt(1, id);

            // Execute the delete operation
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Deleted FileMessage with ID: " + id);
            } else {
                System.out.println("No FileMessage found with ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while deleting FileMessage with ID: " + id);
        }
    }


    // Main method to test the DAO methods
    public static void main(String[] args) {
        FileMessageDAOImpl fileMessageDAO = new FileMessageDAOImpl();

        // Save a new FileMessage
        FileMessage fileMessage = new FileMessage(0, 1, "test_file.txt", 1234, "/uploads/test_file.txt");
        fileMessageDAO.save(fileMessage);
        System.out.println("Saved FileMessage: " + fileMessage);

        // Find a FileMessage by ID
        FileMessage foundFileMessage = fileMessageDAO.findById(fileMessage.getId());
        System.out.println("Found FileMessage: " + foundFileMessage);

        // Update the FileMessage
        if (foundFileMessage != null) {
            foundFileMessage.setFileName("GO.txt");
            fileMessageDAO.update(foundFileMessage);
            System.out.println("Updated FileMessage: " + foundFileMessage);
        }

        // Delete the FileMessage
      /*  if (foundFileMessage != null) {
            fileMessageDAO.delete(1);
        }*/

        // Find all FileMessages
        List<FileMessage> allFileMessages = fileMessageDAO.findAll();
        System.out.println("All FileMessages: " + allFileMessages);
    }
}
