package com.school.core.config;

import java.sql.*;

public class DBConnection {
    public static DBConnection dbConnection;
    private Connection connection;

    public DBConnection() {
//        String name = "LIVE";
//        String url = "jdbc:mysql://db-mysql-sgp1-36777-do-user-3609877-0.b.db.ondigitalocean.com:25060/orderdesk?autoReconnect=true";
//        String username = "podadmin";
//        String password = "7vzIPxVPHwfLmsS6";

        String name = "LOCALHOST";
        String url = "jdbc:mysql://192.168.1.38:3306/schooldb";
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