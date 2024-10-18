package com.zksuvro.www.inventorymanagementsystem.service;

import com.zksuvro.www.inventorymanagementsystem.HelloApplication;
import com.zksuvro.www.inventorymanagementsystem.model.Login;
import javafx.scene.control.Alert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.*;




public class LoginService {

    public void savelogin(Login login){

        try{
            // Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory_management_system","root","admin123");
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            String quary = "SELECT * FROM login WHERE username = '" + login.getUsername() + "'" + " AND password = '" + login.getPassword() + "'"; ;
            PreparedStatement preparedStatement = connection.prepareStatement(quary);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (login.getUsername().isEmpty() && login.getPassword().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter your username and password");
                alert.showAndWait();
            }else {
                if (resultSet.next()){
                    // If correct username and password then proceed to dashboard
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Login Success");
                    alert.setHeaderText(null);
                    HelloApplication.changeScene("adminDashboard");
                    System.out.println("Login successful");
                }
                else {
                    // If username and password are wrong the show error message
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Login Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong username or password");
                    alert.showAndWait();
                    System.out.println("Login Failed");
                }

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        //update database information
//        try{
//           // Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory_management_system","root","admin123");
//            Connection connection = DatabaseConnection.getConnection();
//            Statement statement = connection.createStatement();
//            String quary = "INSERT INTO login value('" + login.getUsername() + "','" + login.getPassword() + "')";
//            statement.executeUpdate(quary);
//            System.err.println("login saved");
//        }catch (SQLException e) {
//            e.printStackTrace();
//        }

        //Local store data
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
