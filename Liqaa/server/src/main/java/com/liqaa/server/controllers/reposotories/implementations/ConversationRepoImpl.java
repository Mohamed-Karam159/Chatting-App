package com.liqaa.server.controllers.reposotories.implementations;

import com.liqaa.server.controllers.reposotories.interfaces.ConversationRepo;
import com.liqaa.server.util.DatabaseManager;
import com.liqaa.shared.models.entities.Conversation;
import com.liqaa.shared.models.enums.ConversationType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConversationRepoImpl implements ConversationRepo
{

    @Override
    public int createDirectConversation()
    {
        String query = "INSERT INTO conversations (type) VALUES ('Direct')";
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            if(statement.executeUpdate() == 1)
            {
                try(ResultSet generatedKeys = statement.getGeneratedKeys())
                {
                    if(generatedKeys.next())
                        return generatedKeys.getInt(1);
                }
            }
            else
                return 0;

        } catch (SQLException e) {
            System.err.println("Error in createDirectConversation: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public int createGroupConversation(int group_id)
    {
        if (group_id <= 0) {
            System.err.println("Error in createGroupConversation: group_id is invalid");
            return 0;
        }
        String query = "INSERT INTO conversations (type, group_id) VALUES ('Group', ?)";
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, group_id);
            if (statement.executeUpdate() == 1) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys())
                {
                    if (generatedKeys.next())
                        return generatedKeys.getInt(1);
                }
            } else {
                System.err.println("Error in createGroupConversation: No rows affected");
                return 0;
            }
        } catch (SQLException e) {
            System.err.println("Error in createGroupConversation: " + e.getMessage());
            return 0;
        }
        return 0;
    }

        @Override
    public Conversation getConversation(int conversation_id)
    {
        if(conversation_id<=0)
        {
            System.err.println("Error in getConversation: conversation_id is invalid");
            return null;
        }
        String query = "SELECT * FROM conversations WHERE id = ?";
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query))
        {
            statement.setInt(1, conversation_id);
            var result = statement.executeQuery();
            if(result.next())
            {
                return new Conversation(result.getInt("id"),
                                        ConversationType.valueOf(result.getString("type").toUpperCase()),
                                        result.getInt("group_id"),
                                        result.getTimestamp("created_at").toLocalDateTime());
            }
        } catch (SQLException e) {
            System.err.println("Error in getConversation: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Conversation getConversationByGroup(int groupId)
    {
        if(groupId<=0)
        {
            System.err.println("Error in getConversationByGroup: groupId is invalid");
            return null;
        }
        String query = "SELECT * FROM conversations WHERE group_id = ?";
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query))
        {
            statement.setInt(1, groupId);
            var result = statement.executeQuery();
            if(result.next())
            {
                return new Conversation(result.getInt("id"),
                                        ConversationType.valueOf(result.getString("type").toUpperCase()),
                                        result.getInt("group_id"),
                                        result.getTimestamp("created_at").toLocalDateTime());
            }
        } catch (SQLException e) {
            System.err.println("Error in getConversationByGroup: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Conversation> getConversations(Integer[] ids)
    {
        if(ids==null || ids.length==0)
        {
            System.err.println("Error in getConversations: ids is invalid");
            return List.of();
        }
        StringBuilder query = new StringBuilder("SELECT * FROM conversations WHERE id IN (");
        for(int i=0; i<ids.length; i++)
        {
            query.append("?");
            if(i!=ids.length-1)
                query.append(",");
        }

        query.append(")");
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query.toString()))
        {
            for(int i=0; i<ids.length; i++)
                statement.setInt(i+1, ids[i]);

            var result = statement.executeQuery();
            List<Conversation> conversations = new ArrayList<>();
            while(result.next())
            {
                conversations.add(new Conversation(result.getInt("id"),
                                                   ConversationType.valueOf(result.getString("type").toUpperCase()),
                                                   result.getInt("group_id"),
                                                   result.getTimestamp("created_at").toLocalDateTime()));
            }
            return conversations;
        } catch (SQLException e) {
            System.err.println("Error in getConversations: " + e.getMessage());
        }
        return List.of();
    }

    @Override
    public void deleteConversation(int conversation_id)
    {
        if(conversation_id<=0)
        {
            System.err.println("Error in deleteConversation: conversation_id is invalid");
            return;
        }
        String query = "DELETE FROM conversations WHERE id = ?";
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query))
        {
            statement.setInt(1, conversation_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in deleteConversation: " + e.getMessage());
        }
    }
}
