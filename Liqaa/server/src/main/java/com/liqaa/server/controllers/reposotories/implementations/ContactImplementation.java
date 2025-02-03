package com.liqaa.server.controllers.reposotories.implementations;
import com.liqaa.server.controllers.reposotories.interfaces.ContactInterface;
import com.liqaa.shared.models.entities.Contacts;
import com.liqaa.server.util.DatabaseManager;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ContactImplementation implements ContactInterface {

    @Override
    public boolean isContact(int userId, int contactId)
    {
        boolean exists=false;
        String query= "SELECT EXISTS (SELECT 1 FROM contacts WHERE user_id = ? AND contact_id = ?)";
        try(PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query))
        {
                statement.setInt(1, userId);
                statement.setInt(2, contactId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                     exists = resultSet.getBoolean(1);
                    System.out.println("Record exists: " + exists);}

        }catch (SQLException e)
        {
            System.err.println("Is contact throws Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return exists;
    }
    @Override
    public  int createContact (Contacts contact)
    {
        //isContact(int userId, int contactId)
        if(isContact(contact.getUserId(),contact.getContactId()))
        {
            System.out.println("Contact already exists");
            return 0;
        }
        String query = "INSERT INTO contacts (user_id, contact_id,  is_blocked) VALUES (?, ?, ?)";
        try(PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query))
        {
                statement.setInt(1, contact.getUserId());
                statement.setInt(2, contact.getContactId());
                statement.setBoolean(3, contact.getIsBlocked());
                System.out.println("Contact created successfully");
                return statement.executeUpdate(); // if it is created it will return 1
        }catch(SQLException e)
        {
            System.err.println("Error creating contact: " + e.getMessage());
            e.printStackTrace();
        }
        return 0; // // Error occurred during creation
    }
    @Override
    public int deleteById(int userId, int contactId)
    {
        if (!isContact(userId, contactId))
        {
            System.out.println("Contact does not exist");
            return 0;
        }
        String query = "DELETE FROM contacts WHERE user_id = ? AND contact_id = ?";
        try(PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, contactId);
            System.out.println("Contact deleted successfully");
            return statement.executeUpdate();
        }catch(SQLException e)
        {
            System.err.println("Error Deleting contact: " + e.getMessage());
            e.printStackTrace();
        }
        return  0;
    }

    @Override
    public boolean blockContact(int userId, int contactId) {
        if (!isContact(userId, contactId)) {
            System.out.println("Contact does not exist");
            return false; // Contact does not exist
        }
        String query = "UPDATE contacts SET is_blocked = true WHERE user_id = ? AND contact_id = ?";
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, contactId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Contact blocked successfully");
                return true; // Contact blocked successfully
            }
        } catch (SQLException e) {
            System.err.println("Error blocking contact: " + e.getMessage());
            e.printStackTrace();
        }
        return false; // Error occurred during update
    }

    @Override
    public  boolean unblockContact(int userId, int contactId)
    {
        if (!isContact(userId, contactId)) {
            System.out.println("Contact does not exist");
            return false; // Contact does not exist
        }
        String query = "UPDATE contacts SET is_blocked = false WHERE user_id = ? AND contact_id = ?";
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, contactId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Contact un blocked successfully");
                return true; // Contact unblocked successfully
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return false; // Error occurred during update
    }
    @Override
    public List<Contacts> getAllContacts (int id)
    {
        String query = "SELECT * FROM contacts WHERE user_id = ?";
        List<Contacts> contactsList = new ArrayList<>();
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    int contactId = rs.getInt("contact_id");
                   Date createdAt = rs.getObject("created_at", Date.class);
                    boolean isBlocked = rs.getBoolean("is_blocked");
                    contactsList.add(new Contacts(userId, contactId, createdAt, isBlocked));

            }
        }
        catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return contactsList;
    }

}
