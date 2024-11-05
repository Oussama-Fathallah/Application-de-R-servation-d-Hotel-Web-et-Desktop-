package com.hotel.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {
    private static final String url = "jdbc:mysql://localhost/larana_hotel";
    private static final String username = "root";
    private static final String password = "root";

    public static Connection getConnection() {
        Connection databaseLink = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;
    }
}