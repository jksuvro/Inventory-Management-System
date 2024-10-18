package com.zksuvro.www.inventorymanagementsystem.controller;

import com.zksuvro.www.inventorymanagementsystem.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Optional;

public class AdminDashboardController {

    @FXML
    private Label UserName;

    @FXML
    private Button addProduct_Btn;

    @FXML
    private Button addProducts_AddBtn;

    @FXML
    private TableColumn<?, ?> addProducts_Col_Brand;

    @FXML
    private TableColumn<?, ?> addProducts_Col_Name;

    @FXML
    private TableColumn<?, ?> addProducts_Col_Price;

    @FXML
    private TableColumn<?, ?> addProducts_Col_ProductId;

    @FXML
    private TableColumn<?, ?> addProducts_Col_Status;

    @FXML
    private TableColumn<?, ?> addProducts_Col_Type;

    @FXML
    private Button addProducts_DeleteBtn;

    @FXML
    private Button addProducts_EditBtn;

    @FXML
    private TextField addProducts_ProductBrand;

    @FXML
    private TextField addProducts_ProductId;

    @FXML
    private TextField addProducts_ProductName;

    @FXML
    private TextField addProducts_ProductPrice;

    @FXML
    private ComboBox<?> addProducts_ProductStatus;

    @FXML
    private ComboBox<?> addProducts_ProductType;

    @FXML
    private TextField addProducts_Search;

    @FXML
    private TableView<?> addProducts_TableView;

    @FXML
    private Button addProducts_UpdateBtn;

    @FXML
    private AnchorPane addProducts_from;

    @FXML
    private ImageView addProducts_imgView;

    @FXML
    private Button addProducts_upload;

    @FXML
    private Button close;

    @FXML
    private Label home_AvailableProducts;

    @FXML
    private Button home_Btn;

    @FXML
    private AreaChart<?, ?> home_IncomeChart;

    @FXML
    private Label home_NumberOrder;

    @FXML
    private BarChart<?, ?> home_OrderChart;

    @FXML
    private Label home_TotalIncome;

    @FXML
    private AnchorPane main_Form;

    @FXML
    private Button minimize;

    @FXML
    private TableView<?> order;

    @FXML
    private Button order_Btn;

    @FXML
    private TableColumn<?, ?> order_Col_Brand;

    @FXML
    private TableColumn<?, ?> order_Col_Price;

    @FXML
    private TableColumn<?, ?> order_Col_ProductName;

    @FXML
    private TableColumn<?, ?> order_Col_Quantity;

    @FXML
    private TableColumn<?, ?> order_Col_Type;

    @FXML
    private TextField orders_Amount;

    @FXML
    private Label orders_Balance;

    @FXML
    private ComboBox<?> orders_BrandName;

    @FXML
    private Button orders_PayBtn;

    @FXML
    private ComboBox<?> orders_ProductName;

    @FXML
    private ComboBox<?> orders_ProductType;

    @FXML
    private Spinner<?> orders_Quantity;

    @FXML
    private Button orders_Recipe;

    @FXML
    private Button orders_ResetBtn;

    @FXML
    private Label orders_Total;

    @FXML
    private AnchorPane orders_form;

    @FXML
    private Button signOut_Btn;

    @FXML
    private Button orders_AddBtn;


    @FXML
    void addProducts_AddBtn(ActionEvent event) {

    }

    @FXML
    void addProducts_DeleteBtn(ActionEvent event) {

    }

    @FXML
    void addProducts_EditBtn(ActionEvent event) {

    }

    @FXML
    void addProducts_UpdateBtn(ActionEvent event) {

    }

    @FXML
    void addProducts_upload(ActionEvent event) {

    }


    @FXML
    void order_Btn(ActionEvent event) {

    }
    @FXML
    void orders_AddBtn(ActionEvent event) {

    }

    @FXML
    void orders_PayBtn(ActionEvent event) {

    }

    @FXML
    void orders_Recipe(ActionEvent event) {

    }

    @FXML
    void orders_ResetBtn(ActionEvent event) {

    }
    @FXML
    void switchForm(ActionEvent event) {
        if (event.getSource() == home_Btn) {
            main_Form.setVisible(true);
            addProducts_from.setVisible(false);
            orders_form.setVisible(false);
//            css Style
            home_Btn.setStyle(" -fx-background-color: #fff");
            addProduct_Btn.setStyle(" -fx-background-color: rgba(255, 255, 255, 0.7)");
            order_Btn.setStyle(" -fx-background-color: rgba(255, 255, 255, 0.7)");
        } else if (event.getSource()== addProduct_Btn) {
            main_Form.setVisible(false);
            addProducts_from.setVisible(true);
            orders_form.setVisible(false);
//            css style
            home_Btn.setStyle(" -fx-background-color: rgba(255, 255, 255, 0.7)");
            addProduct_Btn.setStyle(" -fx-background-color: #fff");
            order_Btn.setStyle(" -fx-background-color: rgba(255, 255, 255, 0.7)");
        } else if (event.getSource() == order_Btn) {
            main_Form.setVisible(false);
            addProducts_from.setVisible(false);
            orders_form.setVisible(true);
//            css Style
            home_Btn.setStyle(" -fx-background-color: rgba(255, 255, 255, 0.7)");
            addProduct_Btn.setStyle(" -fx-background-color: rgba(255, 255, 255, 0.7)");
            order_Btn.setStyle(" -fx-background-color: #fff");
        }
    }

    @FXML
    void signOut_Btn(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sign Out");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            HelloApplication.changeScene("login");
        }else if (result.get() == ButtonType.CANCEL){
            alert.close();
        }

    }

    @FXML
    void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void minimize(ActionEvent event) {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);

    }

}
