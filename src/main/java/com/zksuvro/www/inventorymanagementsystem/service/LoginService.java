package com.zksuvro.www.inventorymanagementsystem.service;

import com.zksuvro.www.inventorymanagementsystem.model.Login;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginService {
    public void savelogin(Login login){

        try{
           // Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory_management_system","root","admin123");
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            String quary = "INSERT INTO login value('" + login.getUsername() + "','" + login.getPassword() + "')";
            statement.executeUpdate(quary);
            System.err.println("login saved");
        }catch (SQLException e) {
            e.printStackTrace();
        }
//        try {
//            String line = login.getUsername() + "," + login.getPassword() + "\n";
//            RandomAccessFile raf = new RandomAccessFile("login.txt", "rw");
//            raf.seek(raf.length());
//            raf.writeBytes(line);
//        }catch (FileNotFoundException e){
//            e.printStackTrace();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
    }
}
