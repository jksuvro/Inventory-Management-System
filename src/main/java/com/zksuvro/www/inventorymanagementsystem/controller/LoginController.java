package com.zksuvro.www.inventorymanagementsystem.controller;

import com.zksuvro.www.inventorymanagementsystem.HelloApplication;
import com.zksuvro.www.inventorymanagementsystem.model.Login;
import com.zksuvro.www.inventorymanagementsystem.service.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button closeBtn;

    @FXML
    private ImageView leftBrandImage;

    @FXML
    private Button loginBtn;

    @FXML
    private Label setLoginText;

    @FXML
    private Button registrationBtn;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void LoginAction(ActionEvent event) {
        String username = userNameField.getText();
        String password = passwordField.getText();

        Login login = new Login(username, password);
        LoginService loginService = new LoginService();
        loginService.savelogin(login);

        if (userNameField.getText().isEmpty() || passwordField.getText().isEmpty()){
            userNameField.setStyle("-fx-border-color: #7e0000 ;" + " -fx-border-width: 2px ;" + " -fx-border-radius: 15px ;");
            passwordField.setStyle("-fx-border-color: #7e0000 ;" + " -fx-border-width: 2px ;" + " -fx-border-radius: 15px ;");
            setLoginText.setText("please enter username and password");
        }else {
            setLoginText.setText("Successfully logged in");
        }
    }

    @FXML
    void registrationBtnAction(ActionEvent event) {
        HelloApplication.changeScene("Registration");
    }

    @FXML
    void closeAction(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("img/main-logo.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        leftBrandImage.setImage(brandingImage);
    }

}

