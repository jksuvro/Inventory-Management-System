package com.zksuvro.www.inventorymanagementsystem.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_HOST = "localhost";
    private static final String DB_NAME = "inventory_management_system";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "admin123";
    public static final String DB_URL = "jdbc:mysql://" + DB_HOST + "/" + DB_NAME;


    private static Connection connection;
    public static DatabaseConnection dbConnection = new DatabaseConnection();

    private DatabaseConnection() {
        try{
            connection = DriverManager.getConnection(DatabaseConnection.DB_URL, DatabaseConnection.DB_USER, DatabaseConnection.DB_PASSWORD);
            System.out.println("Database connection established");
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {
        return connection;
    }
}
