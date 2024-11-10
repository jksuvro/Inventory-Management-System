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
            Connection connection = DatabaseConnection.getConnection();
            connection.createStatement();
            String quary = "SELECT * FROM login WHERE username = '" + login.getUsername() + "'" + " AND password = '" + login.getPassword() + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(quary);
            ResultSet resultSet = preparedStatement.executeQuery();

            String employee = "SELECT * FROM employee_login WHERE username = '" + login.getUsername() + "'" + " AND password = '" + login.getPassword() + "'";
            PreparedStatement employeeDB = connection.prepareStatement(employee);
            ResultSet employeeResultSet = employeeDB.executeQuery();

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

                }else if (employeeResultSet.next()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Login Success");
                    alert.setHeaderText(null);
                    HelloApplication.changeScene("employeeDashboard");
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

    }
}
