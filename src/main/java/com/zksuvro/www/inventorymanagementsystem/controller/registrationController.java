package com.zksuvro.www.inventorymanagementsystem.controller;

import com.zksuvro.www.inventorymanagementsystem.HelloApplication;
import com.zksuvro.www.inventorymanagementsystem.model.Registration;
import com.zksuvro.www.inventorymanagementsystem.service.RegistrationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private Button closeBtn;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private TextField firstNameReg;

    @FXML
    private TextField lastNameReg;

    @FXML
    private PasswordField passwordReg;

    @FXML
    private TextField phoneReg;

    @FXML
    private ImageView regLeftBrandImg;

    @FXML
    private Button regLoginBtn;

    @FXML
    private Button signupReg;

    @FXML
    private AnchorPane registrationPane;

    @FXML
    private Label successfullySave;

    @FXML
    private TextField userNameReg;

    private double x = 0;
    private double y = 0;

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
        String firstname = firstNameReg.getText();
        String lastname = lastNameReg.getText();
        int phone = Integer.parseInt(phoneReg.getText());
        String username = userNameReg.getText();
        String password = passwordReg.getText();
        String passwordConfirm = confirmPassword.getText();


        Registration registration = new Registration(firstname, lastname, phone, username, password);
        RegistrationService registrationService = new RegistrationService();
        registrationService.saveregistration(registration);

        if(firstNameReg.getText().isEmpty() || lastNameReg.getText().isEmpty() || phoneReg.getText().isEmpty() || passwordReg.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        }else {
            successfullySave.setText("Sign up successful");
        }


    }

    @FXML
    void registrationPane_Dragged(MouseEvent event) {
        Stage stage = (Stage) registrationPane.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    void registrationPane_Pressed(MouseEvent event) {
        x=event.getScreenX();
        y=event.getScreenY();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File leftBrandFile = new File("img/logo.jpg");
        Image leftBrandImg = new Image(leftBrandFile.toURI().toString());
        regLeftBrandImg.setImage(leftBrandImg);
    }
}
