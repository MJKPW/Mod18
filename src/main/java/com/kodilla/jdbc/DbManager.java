package com.kodilla.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public enum DbManager {

    INSTANCE;

    private final Connection conn;

    DbManager() {
        Properties connectionProps = new Properties();
        connectionProps.put("user", "mjk");
        connectionProps.put("password", "Fim1_password");
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/task_crud" +
                            "?serverTimezone=Europe/Warsaw" +
                            "&useSSL=False",
                    connectionProps);
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static DbManager getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        return conn;
    }
}

