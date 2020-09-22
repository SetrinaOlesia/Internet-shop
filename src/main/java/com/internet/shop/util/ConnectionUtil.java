package com.internet.shop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't find MySQL Driver", e);
        }
    }

    public static Connection getConnection() {
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "1");
        String url = "jdbc:mysql://localhost:3306";

        try {
            Connection connection = DriverManager.getConnection(url, properties);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Cant't establish the connection to DB", e);
        }

    }
}
