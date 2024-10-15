package com.zksuvro.www.inventorymanagementsystem.service;

import com.zksuvro.www.inventorymanagementsystem.model.Registration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

public class RegistrationService {
    public void saveregistration(Registration registration) {
        try{
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            //insert into registration value('Rahim', 'karim', 'karim@gmail.com', 01254563355, '1234')
            String quary = "INSERT INTO registration value('" + registration.getFirstname() + "','" + registration.getLastname() + "','" + registration.getEmail() + "'," + registration.getPhone() + ",'" + registration.getPassword() + "')";
            statement.executeUpdate(quary);
            System.err.println("Registration saved");
        }catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
