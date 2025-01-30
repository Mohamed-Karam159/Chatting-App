package com.liqaa.server.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager
{
    private static HikariDataSource dataSource;

    static {
        try (InputStream input = DatabaseManager.class.getClassLoader().getResourceAsStream("datasource.properties"))
        {
            if (input == null)
            {
                System.out.println("datasource.properties not found");
            }

            Properties prop = new Properties();
            prop.load(input);

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(prop.getProperty("url"));
            config.setUsername(prop.getProperty("user"));
            config.setPassword(prop.getProperty("password"));
            dataSource = new HikariDataSource(config);

        } catch (IOException e)
        {
            System.err.println("Error loading datasource.properties " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException
    {
        return dataSource.getConnection();
    }
}