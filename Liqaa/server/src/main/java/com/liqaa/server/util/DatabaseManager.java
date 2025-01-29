package com.liqaa.server.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager
{
    private static final DatabaseManager instance = new DatabaseManager();
    private Connection connection;

    private DatabaseManager()
    {
        try (InputStream input = DatabaseManager.class.getClassLoader().getResourceAsStream("datasource.properties")) {
            Properties prop = new Properties();
            if (input == null)
                throw new RuntimeException("Sorry, unable to find datasource.properties");

            prop.load(input);
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection(url, user, password);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException("An error occurred: " + e.getMessage(), e);
        }
    }

    public static DatabaseManager getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}