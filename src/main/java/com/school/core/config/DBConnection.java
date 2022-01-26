package com.school.core.config;

import java.sql.*;

public class DBConnection {
    public static DBConnection dbConnection;
    private Connection connection;

    public DBConnection() {
//        String name = "LIVE";
//        String url = "jdbc:mysql://103.173.154.237:3306/schooldb?autoReconnect=true";
//        String username = "admin";
//        String password = "1Q2w3e4r5t@@";

        String name = "LOCALHOST";
        String url = "jdbc:mysql://192.168.1.38:3306/schooldb?autoReconnect=true";
        String username = "root";
        String password = "abc123@@";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
            System.out.println("-- [" + name + "] CONNECTED TO DATABASE SUCCESSFULLY! --");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance() {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void closeConnection() {
        try {
            this.connection.close();
            System.out.println("-- CLOSED DATABASE CONNECTION --");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}