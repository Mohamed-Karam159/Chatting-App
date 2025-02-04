package com.liqaa.server.controllers.reposotories.implementations;

import com.liqaa.server.controllers.reposotories.interfaces.UserContactCategoryRepo;
import com.liqaa.server.util.DatabaseManager;
import com.liqaa.shared.models.entities.Category;
import com.liqaa.shared.models.entities.UserContactCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserContactCategoryRepoImpl implements UserContactCategoryRepo {
    @Override
    public boolean createUserContactCategory(UserContactCategory userContactCategory) throws SQLException {
        if (userContactCategory == null) {
            System.err.println("Error creating user_contact_category: UserContactCategory is null");
            return false;
        }
        try(Connection connection = DatabaseManager.getConnection();) {
            String query = "INSERT INTO usercontactcategories (user_id, category_id) VALUES (?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(query);) {
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected <= 0) {
                    System.err.println("Failed to create user_contact_category");
                    return false;
                } else {
                    System.out.println("user_contact_category is created successfully");
                    return true;
                }
            }
        }
    }

    @Override
    public List<UserContactCategory> getAllUserContactCategories() throws SQLException {
        try(Connection connection = DatabaseManager.getConnection();) {
            String query = "SELECT user_id, category_id, created_at FROM usercontactcategories";
            try(PreparedStatement statement = connection.prepareStatement(query);) {
                try(ResultSet resultSet = statement.executeQuery();) {
                    List<UserContactCategory> list = new ArrayList<>();
                    while (resultSet.next()) {
                        UserContactCategory userContactCategory = new UserContactCategory(resultSet.getInt("user_id"), resultSet.getInt("category_id"), resultSet.getTimestamp("created_at").toLocalDateTime());
                        list.add(userContactCategory);
                    }
                    return list;
                }
            }
        }
    }

    @Override
    public boolean updateUserContactCategory(UserContactCategory userContactCategory) throws SQLException {
        if (userContactCategory == null) {
            System.err.println("Error updating user_contact_category: UserContactCategory is null");
            return false;
        }
        try(Connection connection = DatabaseManager.getConnection();) {
            String deleteQuery = "DELETE FROM usercontactcategories WHERE user_id = ? AND category_id = ?";
            try(PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);) {
                deleteStatement.setInt(1, userContactCategory.getUserId());
                deleteStatement.setInt(2, userContactCategory.getCategoryId());
                deleteStatement.executeUpdate();

                String insertQuery = "INSERT INTO usercontactcategories (user_id, category_id) VALUES (?, ?)";
                try(PreparedStatement insertStatement = connection.prepareStatement(insertQuery);) {
                    insertStatement.setInt(1, userContactCategory.getUserId());
                    insertStatement.setInt(2, userContactCategory.getCategoryId());
                    int rowsAffected = insertStatement.executeUpdate();
                    if (rowsAffected <= 0) {
                        System.err.println("Failed to update user_contact_category");
                    } else {
                        System.out.println("user_contact_category is updated successfully");
                    }
                    return (rowsAffected > 0);
                }
            }
        }
    }

    @Override
    public boolean deleteUserContactCategory(UserContactCategory userContactCategory) throws SQLException {
        if (userContactCategory == null) {
            System.err.println("Error deleting user_contact_category: UserContactCategory is null");
            return false;
        }
        try (Connection connection = DatabaseManager.getConnection();) {
            String query = "DELETE FROM usercontactcategories WHERE user_id = ? AND category_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setInt(1, userContactCategory.getUserId());
                statement.setInt(2, userContactCategory.getCategoryId());
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected <= 0) {
                    System.err.println("Failed to delete user_contact_category");
                } else {
                    System.out.println("user_contact_category is deleted successfully");
                }
                return (rowsAffected > 0);
            }
        }
    }

    @Override  /** like getAll() but this for a specific user only */
    public List<UserContactCategory> getSpecificUserWithCategories(int userId) throws SQLException {
        if (userId <= 0) {
            System.err.println("Error getting categories: Invalid user ID");
            return null;
        }
        try (Connection connection = DatabaseManager.getConnection();) {
            String query = "SELECT user_id, category_id, created_at FROM usercontactcategories WHERE user_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setInt(1, userId);
                try (ResultSet resultSet = statement.executeQuery();) {
                    List<UserContactCategory> list = new ArrayList<>();
                    while (resultSet.next()) {
                        UserContactCategory userContactCategory = new UserContactCategory(resultSet.getInt("user_id"), resultSet.getInt("category_id"), resultSet.getTimestamp("created_at").toLocalDateTime());
                        list.add(userContactCategory);
                    }
                    return list;
                }
            }
        }
    }
}