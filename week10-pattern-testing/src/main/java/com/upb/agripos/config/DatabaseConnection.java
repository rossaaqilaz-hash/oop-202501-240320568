package com.upb.agripos.config;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private String connectionString;

    private DatabaseConnection() {
        System.err.println("DatabaseConnection instance created.");
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void connect() {
        System.err.println("Connecting to database : " + connectionString);
    }
}