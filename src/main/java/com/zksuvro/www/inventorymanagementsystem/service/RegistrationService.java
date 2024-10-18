package com.zksuvro.www.inventorymanagementsystem.service;

import com.zksuvro.www.inventorymanagementsystem.model.Registration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.*;
import java.util.Collection;

public class RegistrationService {
    public void saveregistration(Registration registration) {
        try{
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            String query =  "INSERT INTO registration (firstname, lastname, phone, username, password) VALUES('"+ registration.getFirstname() + "','" + registration.getLastname() + "','" + registration.getPhone() + "','" + registration.getUsername() + "','" + registration.getPassword() + "')";
            statement.executeUpdate(query);
            System.err.println("Registration saved");
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
