package com.zksuvro.www.inventorymanagementsystem.controller;

import com.zksuvro.www.inventorymanagementsystem.HelloApplication;
import com.zksuvro.www.inventorymanagementsystem.model.Registration;
import com.zksuvro.www.inventorymanagementsystem.service.RegistrationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;

public class registrationController implements Initializable {

    @FXML
    private CheckBox agreeCheckBox;

    @FXML
    private ImageView regLeftBrandImg;

    @FXML
    private TextField emailReg;

    @FXML
    private TextField firstNameReg;

    @FXML
    private TextField lastNameReg;

    @FXML
    private PasswordField passwordReg;

    @FXML
    private TextField phoneReg;

    @FXML
    private Button regLoginBtn;

    @FXML
    private Button closeBtn;

    @FXML
    private Button signupReg;

    @FXML
    private Label successfullySave;

    @FXML
    void regLoginBtn(ActionEvent event) {
        HelloApplication.changeScene("login");
    }
    @FXML
    void closeBtnAction(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }


    @FXML
    void signUpAction(ActionEvent event) {
        String email = emailReg.getText();
        String firstname = firstNameReg.getText();
        String lastname = lastNameReg.getText();
        String password = passwordReg.getText();
        int phone = Integer.parseInt(phoneReg.getText());

        Registration registration = new Registration(firstname, lastname, email, phone, password);
        RegistrationService registrationService = new RegistrationService();
        registrationService.saveregistration(registration);

        if(firstNameReg.getText().isEmpty()) {
            firstNameReg.setStyle("-fx-border-color: #7e0000 ;" + " -fx-border-width: 2px ;" + " -fx-border-radius: 15px ;");
        }else {
            successfullySave.setText("Sign up successful");
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File leftBrandFile = new File("img/logo.jpg");
        Image leftBrandImg = new Image(leftBrandFile.toURI().toString());
        regLeftBrandImg.setImage(leftBrandImg);
    }
}
