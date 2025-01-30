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
    public boolean addNew(UserContactCategory userContactCategory) throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query = "INSERT INTO usercontactcategories (user_id, category_id) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        int rowsAffected = statement.executeUpdate();
        return (rowsAffected > 0);
    }

    @Override
    public List<UserContactCategory> getAll() throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query = "SELECT user_id, category_id, created_at FROM usercontactcategories";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<UserContactCategory> list = new ArrayList<>();
        while (resultSet.next()){
            UserContactCategory userContactCategory = new UserContactCategory(resultSet.getInt("user_id"), resultSet.getInt("category_id"), resultSet.getTimestamp("created_at").toLocalDateTime());
            list.add(userContactCategory);
        }
        return list;
    }

    @Override
    public boolean update(UserContactCategory userContactCategory) throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        String deleteQuery = "DELETE FROM usercontactcategories WHERE user_id = ? AND category_id = ?";
        PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
        deleteStatement.setInt(1, userContactCategory.getUserId());
        deleteStatement.setInt(2, userContactCategory.getCategoryId());
        deleteStatement.executeUpdate();

        String insertQuery = "INSERT INTO usercontactcategories (user_id, category_id) VALUES (?, ?)";
        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
        insertStatement.setInt(1, userContactCategory.getUserId());
        insertStatement.setInt(2, userContactCategory.getCategoryId());
        int rowsAffected = insertStatement.executeUpdate();
        return (rowsAffected > 0);
    }

    @Override
    public boolean delete(UserContactCategory userContactCategory) throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query = "DELETE FROM usercontactcategories WHERE user_id = ? AND category_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userContactCategory.getUserId());
        statement.setInt(2, userContactCategory.getCategoryId());
        int rowsAffected = statement.executeUpdate();
        return (rowsAffected > 0);
    }

    @Override /** like getAll() but this for a specific user only */
    public List<UserContactCategory> getSpecificUserWithCategories(int userId) throws SQLException {
        Connection connection = DatabaseManager.getInstance().getConnection();
        String query = "SELECT user_id, category_id, created_at FROM usercontactcategories WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        List<UserContactCategory> list = new ArrayList<>();
        while (resultSet.next()){
            UserContactCategory userContactCategory = new UserContactCategory(resultSet.getInt("user_id"), resultSet.getInt("category_id"), resultSet.getTimestamp("created_at").toLocalDateTime());
            list.add(userContactCategory);
        }
        return list;
    }
}
