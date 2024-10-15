package com.zksuvro.www.inventorymanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 650);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void changeScene(String fileName) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fileName + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 650);
            stage.setTitle("Registration");
            stage.setScene(scene);
            stage.setScene(scene);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}