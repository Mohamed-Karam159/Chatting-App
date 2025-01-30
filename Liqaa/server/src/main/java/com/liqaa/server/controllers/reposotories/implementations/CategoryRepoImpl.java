package com.liqaa.server.controllers.reposotories.implementations;

import com.liqaa.server.controllers.reposotories.interfaces.CategoryRepo;
import com.liqaa.server.util.DatabaseManager;
import com.liqaa.shared.models.entities.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepoImpl implements CategoryRepo
{

    @Override
    public int createCategory(Category category)
    {
        if (category == null)
        {
            System.err.println("Error creating category: Category is null");
            return 0;
        }

        String query = "INSERT INTO categories (user_id, category_name) VALUES (?, ?)";

        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, category.getUserId());
            statement.setString(2, category.getCategoryName());

            if (statement.executeUpdate() == 0)
            {
                System.err.println("Error creating category: No rows affected");
                return 0;
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys())
            {
                if (generatedKeys.next())
                {
                    int id=generatedKeys.getInt(1);
                    category.setId(id);
                    return id;
                }
                else
                {
                    System.err.println("Error creating category: No ID returned");
                    return 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error creating category, Failed to create statement: " + e.getMessage());
            return 0;
        }
    }


    @Override
    public Category getCategory(int id)
    {
        if(id <= 0)
        {
            System.err.println("Error getting category: Invalid ID");
            return null;
        }

        String query = "SELECT * FROM categories WHERE id = ?;";
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query))
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                return new Category(resultSet.getInt("id"),
                                    resultSet.getInt("user_id"),
                                    resultSet.getString("category_name"),
                                    resultSet.getTimestamp("created_at"));
            }
            else
                System.err.println("Error getting category: No category found with ID: " + id);
        } catch (SQLException e) {
            System.err.println("Error getting category, Failed to create statement: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateCategoryName(int id, String name)
    {
        if(id <= 0)
        {
            System.err.println("Error updating category: Invalid ID");
            return false;
        }
        if(name == null || name.isEmpty())
        {
            System.err.println("Error updating category: Invalid name");
            return false;
        }
        String query = "UPDATE categories SET category_name = ? WHERE id = ?;";
        try (PreparedStatement statement= DatabaseManager.getConnection().prepareStatement(query))
        {
            statement.setString(1, name);
            statement.setInt(2, id);
            if(statement.executeUpdate() == 0)
            {
                System.err.println("Error updating category: No rows affected");
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteCategory(int id)
    {
        if(id <= 0)
        {
            System.err.println("Error deleting category: Invalid ID");
            return false;
        }
        String query = "DELETE FROM categories WHERE id = ?;";
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query))
        {
            statement.setInt(1, id);
            if(statement.executeUpdate() == 0)
            {
                System.err.println("Error deleting category: No rows affected");
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Category> getUserCategories(int id)
    {
        if(id <= 0)
        {
            System.err.println("Error getting categories: Invalid ID");
            return List.of();
        }
        String query = "SELECT * FROM categories WHERE user_id = ?;";
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query))
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Category> categories = new ArrayList<>();
            while (resultSet.next())
            {
                categories.add(new Category(resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("category_name"),
                        resultSet.getTimestamp("created_at")));
            }
            return categories;

        } catch (SQLException e) {
            System.err.println("Error getting categories, Failed to create statement: " + e.getMessage());
        }
        return List.of();
    }

    @Override
    public List<Category> getAllCategories(int[] ids)
    {
        if(ids == null || ids.length == 0)
        {
            System.err.println("Error getting categories: Invalid IDs");
            return List.of();
        }
        StringBuilder query = new StringBuilder("SELECT * FROM categories WHERE id IN (");
        for (int i = 0; i < ids.length; i++)
        {
            query.append("?");
            if(i != ids.length - 1)
                query.append(", ");
        }
        query.append(");");
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query.toString()))
        {
            for (int i = 0; i < ids.length; i++)
                statement.setInt(i + 1, ids[i]);
            ResultSet resultSet = statement.executeQuery();
            List<Category> categories = new ArrayList<>();
            while (resultSet.next())
            {
                categories.add(new Category(resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("category_name"),
                        resultSet.getTimestamp("created_at") != null ? Timestamp.valueOf(resultSet.getTimestamp("created_at").toLocalDateTime()) : null));
            }
            return categories;

        } catch (SQLException e) {
           System.err.println("Error getting categories, Failed to create statement: " + e.getMessage());
        }
        return List.of();
    }
}
