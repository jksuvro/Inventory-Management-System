package com.zksuvro.www.inventorymanagementsystem.controller;

import com.zksuvro.www.inventorymanagementsystem.HelloApplication;
import com.zksuvro.www.inventorymanagementsystem.model.CustomerData;
import com.zksuvro.www.inventorymanagementsystem.model.ImageData;
import com.zksuvro.www.inventorymanagementsystem.model.ProductData;
import com.zksuvro.www.inventorymanagementsystem.service.DatabaseConnection;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class AdminDashboardController implements Initializable {


    @FXML
    private AnchorPane adminPane;

    @FXML
    private Label UserName;

    @FXML
    private Button addProduct_Btn;

    @FXML
    private Button addProducts_AddBtn;

    @FXML
    private TableView<ProductData> addProducts_TableView;

    @FXML
    private TableColumn<ProductData, String> addProducts_Col_Brand;

    @FXML
    private TableColumn<ProductData, String> addProducts_Col_Name;

    @FXML
    private TableColumn<ProductData, Number> addProducts_Col_Price;

    @FXML
    private TableColumn<ProductData, Number> addProducts_Col_ProductId;

    @FXML
    private TableColumn<ProductData, String> addProducts_Col_Status;

    @FXML
    private TableColumn<ProductData, String> addProducts_Col_Type;

    @FXML
    private ImageView brandIcon;

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
    private ComboBox<String> addProducts_ProductStatus;

    @FXML
    private ComboBox<String> addProducts_ProductType;

    @FXML
    private TextField addProducts_Search;

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
    private Button order_Btn;

    @FXML
    private TableView<CustomerData> orderTableView;

    @FXML
    private TableColumn<CustomerData, String> order_Col_Brand;

    @FXML
    private TableColumn<CustomerData, Number> order_Col_Price;

    @FXML
    private TableColumn<CustomerData, String> order_Col_ProductName;

    @FXML
    private TableColumn<CustomerData, Number> order_Col_Quantity;

    @FXML
    private TableColumn<CustomerData, String> order_Col_Type;

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
    private ComboBox<String> orders_ProductType;

    @FXML
    private Spinner<Integer> orders_Quantity;

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

    private Image image;

    private double x = 0;
    private double y = 0;

//   Home
    public void homeDisplayTotalOrders(){
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "SELECT COUNT(id) FROM customer WHERE date ='"+sqlDate+"' ";
        Connection connection = DatabaseConnection.getConnection();
        int countOrders = 0;
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                countOrders = resultSet.getInt("COUNT(id)");
            }
            home_NumberOrder.setText(String.valueOf(countOrders));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void homeTotalIncome(){
        String sql = "SELECT SUM(total) FROM customer_receipt";
        Connection connection = DatabaseConnection.getConnection();
        double totalIncome = 0;
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                totalIncome = resultSet.getDouble("SUM(total)");
            }
            home_TotalIncome.setText("$"+ String.valueOf(totalIncome));

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void homeAvailableProducts(){
        String sql = "SELECT COUNT(id) FROM product WHERE status = 'Available'";
        Connection connection = DatabaseConnection.getConnection();
        int countAP = 0;
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                countAP = resultSet.getInt("COUNT(id)");
            }
            home_AvailableProducts.setText(String.valueOf(countAP));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void homeIncomeChart(){
        home_IncomeChart.getData().clear();

        String sql = "SELECT date, SUM(total) FROM customer_receipt GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 6";
        Connection connection = DatabaseConnection.getConnection();
        try {
            XYChart.Series chart = new XYChart.Series();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                chart.getData().add(new XYChart.Data(resultSet.getString(1), resultSet.getDouble(2)));
            }
            home_IncomeChart.getData().addAll(chart);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void homeOrdersChart(){
        home_OrderChart.getData().clear();
        String sql = "SELECT date, COUNT(id) FROM customer GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";

        Connection connection = DatabaseConnection.getConnection();

        try{
            XYChart.Series chart = new XYChart.Series();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                chart.getData().add(new XYChart.Data(resultSet.getString(1), resultSet.getInt(2)));
            }
            home_OrderChart.getData().addAll(chart);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

//    ADD PRODUCT ACTION
    @FXML
    void addProducts_AddBtn() {

        try {
            Alert alert;
            if (addProducts_ProductId.getText().isEmpty()
                    || addProducts_ProductType.getSelectionModel().getSelectedItem() == null
                    || addProducts_ProductBrand.getText().isEmpty()
                    || addProducts_ProductName.getText().isEmpty()
                    || addProducts_ProductStatus.getSelectionModel().getSelectedItem() == null
                    || addProducts_ProductPrice.getText().isEmpty()
                    || ImageData.path.isEmpty() ){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the required fields");
                alert.showAndWait();
            }else {
//                CHECK THE PRODUCT ID IS ALREADY EXIST
                String checkData = "SELECT product_id FROM product WHERE product_id= '" + addProducts_ProductId.getText() + "'";
                Statement statement = DatabaseConnection.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(checkData);
                if (resultSet.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Product already" + addProducts_ProductId.getText() + "was already exist!");
                    alert.showAndWait();
                } else {
                    Connection connection = DatabaseConnection.getConnection();
                    connection.createStatement();
                    String sql = "INSERT INTO product (product_id, type, brand, productName, price, status, date, image) VALUES (?,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, addProducts_ProductId.getText());
                    preparedStatement.setString(2, (String) addProducts_ProductType.getSelectionModel().getSelectedItem());
                    preparedStatement.setString(3, addProducts_ProductBrand.getText());
                    preparedStatement.setString(4, addProducts_ProductName.getText());
                    preparedStatement.setString(5, addProducts_ProductPrice.getText());
                    preparedStatement.setString(6, (String) addProducts_ProductStatus.getSelectionModel().getSelectedItem());

//              For date update
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    preparedStatement.setDate(7, sqlDate);

//              Image path update
                    String uri = ImageData.path;
                    uri = uri.replace("\\", "\\\\");
                    preparedStatement.setString(8, uri);

                    preparedStatement.execute();

//                TO BECOME UPDATE YOUR TABLE VIEW
                    addProductShowListData();
//              CLEAR THE FIELD
                    addProducts_EditBtn();

                    System.err.println("Product Added Successfully");
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    TABLE VIEW PRODUCT SHOW LIST
    private ResultSet result;
    public ObservableList<ProductData> addProductListData() {
        Connection connection = DatabaseConnection.getConnection();
        ObservableList<ProductData> productlist = FXCollections.observableArrayList();
        String sql = "SELECT * FROM product";
        try {
            connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            result = preparedStatement.executeQuery();
            ProductData prodD;
            while (result.next()) {
                prodD = new ProductData(result.getInt("product_id")
                        , result.getString("type")
                        , result.getString("brand")
                        , result.getString("productName")
                        , result.getDouble("price")
                        , result.getString("status")
                        , result.getString("image")
                        , result.getDate("date"));
                productlist.add(prodD);
                System.err.println("Product Data Get");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return productlist;
    }

    private ObservableList<ProductData> addProductsList;
    public void addProductShowListData(){
        addProductsList = addProductListData();
        addProducts_Col_ProductId.setCellValueFactory(c-> new SimpleIntegerProperty(c.getValue().getProductId()));
        addProducts_Col_Type.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getType()));
        addProducts_Col_Brand.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getBrand()));
        addProducts_Col_Name.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getProductName()));
        addProducts_Col_Status.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getStatus()));
        addProducts_Col_Price.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getPrice()));

        addProducts_TableView.setItems(addProductsList);
    }

//    PRODUCT SELECT ACTION
    public void addProductsSelect() {
        ProductData prodD = addProducts_TableView.getSelectionModel().getSelectedItem();
        int num = addProducts_TableView.getSelectionModel().getSelectedIndex();

        if((num -1) < -1 ){
            return;
        }
        addProducts_ProductId.setText(String.valueOf(prodD.getProductId()));
//        addProducts_ProductType.getSelectionModel().clearSelection();
        addProducts_ProductBrand.setText(prodD.getBrand());
        addProducts_ProductName.setText(prodD.getProductName());
//        addProducts_ProductStatus.getSelectionModel().clearSelection();
        addProducts_ProductPrice.setText(String.valueOf(prodD.getPrice()));

        String uri = "file:" + prodD.getImage();
        image = new Image(uri, 112, 120, false, true);
        addProducts_imgView.setImage(image);

        ImageData.path = prodD.getImage();
    }

//    PRODUCT EDIT ACTON
    @FXML
    void addProducts_UpdateBtn() {
        String uri = ImageData.path;
        uri = uri.replace("\\", "\\\\");

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "UPDATE product SET type = '" + addProducts_ProductType.getSelectionModel().getSelectedItem()
                + "', brand = '" + addProducts_ProductBrand.getText()
                + "', productName = '" + addProducts_ProductName.getText()
                + "', price = '" + addProducts_ProductPrice.getText()
                + "', status = '" + addProducts_ProductStatus.getSelectionModel().getSelectedItem()
                + "', image = '" + uri
                + "', date = '" + sqlDate
                + "' WHERE product_id = '" + addProducts_ProductId.getText() + "'";
        try{
            Alert alert;
            if (addProducts_ProductId.getText().isEmpty()
                    || addProducts_ProductType.getSelectionModel().getSelectedItem() == null
                    || addProducts_ProductBrand.getText().isEmpty()
                    || addProducts_ProductName.getText().isEmpty()
                    || addProducts_ProductStatus.getSelectionModel().getSelectedItem() == null
                    || addProducts_ProductPrice.getText().isEmpty()
                    || ImageData.path == ""){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the required fields");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to update this product" + addProducts_ProductId.getText() + "?");
                Optional<ButtonType> result = alert.showAndWait();
                if ( result.get() == ButtonType.OK){
                    Connection connection = DatabaseConnection.getConnection();
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully updated");
                    alert.showAndWait();

                    addProductShowListData();
                    addProducts_EditBtn();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    void addProducts_EditBtn() {
        addProducts_ProductId.setText("");
        addProducts_ProductType.getSelectionModel().clearSelection();
        addProducts_ProductBrand.setText("");
        addProducts_ProductName.setText("");
        addProducts_ProductPrice.setText("");
        addProducts_ProductStatus.getSelectionModel().clearSelection();
        addProducts_imgView.setImage(null);
        ImageData.path = "";

    }

//   PRODUCT DELETE ACTION
    @FXML
    void addProducts_DeleteBtn() {
        String sql = "DELETE FROM product WHERE product_id = '" + addProducts_ProductId.getText() + "'";
        try {
            Alert alert;
            if (addProducts_ProductId.getText().isEmpty()
                    || addProducts_ProductType.getSelectionModel().getSelectedItem() == null
                    || addProducts_ProductBrand.getText().isEmpty()
                    || addProducts_ProductName.getText().isEmpty()
                    || addProducts_ProductStatus.getSelectionModel().getSelectedItem() == null
                    || addProducts_ProductPrice.getText().isEmpty()
                    || ImageData.path.isEmpty()){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the required fields");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE this product" + addProducts_ProductId.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if ( option.get() == ButtonType.OK){
                    Connection connection = DatabaseConnection.getConnection();
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted");
                    alert.showAndWait();

                    addProductShowListData();
                    addProducts_EditBtn();
                }


            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

//    PRODUCT IMAGE ACTION
    @FXML
    void addProducts_upload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("Image File", "*.jpg", "*.png") );

        File file = fileChooser.showOpenDialog(main_Form.getScene().getWindow());
        if (file != null) {
            ImageData.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 112, 120, false, true );
            addProducts_imgView.setImage(image);
        }
    }
//    PRODUCT SEARCH OPTION
    public void addProductSearch(){
        FilteredList<ProductData> filter = new FilteredList<>(addProductsList, e -> true);
        addProducts_Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(productData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                if (productData.getProductId().toString().contains(searchKey)) {
                    return true;
                }else if (productData.getType().toLowerCase().contains(searchKey)) {
                    return true;
                }else if(productData.getBrand().toLowerCase().contains(searchKey)) {
                    return true;
                }else if( productData.getProductName().toLowerCase().contains(searchKey)) {
                    return true;
                }else if (productData.getPrice()<0) {
                    return true;
                }else if (productData.getStatus().toLowerCase().contains(searchKey)) {
                    return true;
                }else return false;
            });
        });
        SortedList<ProductData> sortedList = new SortedList<>(filter);
        sortedList.comparatorProperty().bind(addProducts_TableView.comparatorProperty());
        addProducts_TableView.setItems(sortedList);
    }

//    Product Type List
    private String[] listType = {"Snacks", "Drinks", "Dessert", "Gadgets", "Personal Product", "Others"};
    public void addProductListType(){
        List<String> listT = new ArrayList<>();
        for(String data: listType){
            listT.add(data);
        }
        ObservableList<String> listData = FXCollections.observableList(listT);
        addProducts_ProductType.setItems(listData);
    }

//    Product Status
    private String[] listStatus = {"Available", "Not Available"};
    public void addProductListStatus(){
        List<String> listS = new ArrayList<>();
        for(String data: listStatus){
            listS.add(data);
        }
        ObservableList<String> listData = FXCollections.observableList(listS);
        addProducts_ProductStatus.setItems(listData);
    }



    @FXML
    void order_Btn(ActionEvent event) {

    }

    @FXML
    public void orders_AddBtn(){
        customerId();
        String sql = "INSERT INTO customer (customer_id, type, brand, productName, quantity, price, date)"
                + "VALUES(?,?,?,?,?,?,?)";
        Connection connection = DatabaseConnection.getConnection();
        try{

            String checkData = "SELECT * FROM product WHERE productName = '"
                    + orders_ProductName.getSelectionModel().getSelectedItem() + "'";
            double totalPrice = 0;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(checkData);
            if (resultSet.next()) {
                totalPrice = resultSet.getDouble("price");
            }
            double totalPData = (totalPrice*quantity);
            Alert alert;
            if (orders_ProductType.getSelectionModel().getSelectedItem() == null
                    ||(String) orders_BrandName.getSelectionModel().getSelectedItem() == null
                    ||(String) orders_ProductName.getSelectionModel().getSelectedItem() == null
                    || totalPData == 0) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all the required fields");
                    alert.showAndWait();
            } else {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, String.valueOf(customerid));
                    preparedStatement.setString(2, orders_ProductType.getSelectionModel().getSelectedItem());
                    preparedStatement.setString(3, (String) orders_BrandName.getSelectionModel().getSelectedItem());
                    preparedStatement.setString(4, (String) orders_ProductName.getSelectionModel().getSelectedItem());
                    preparedStatement.setString(5, String.valueOf(quantity));

                    preparedStatement.setString(6, String.valueOf(totalPData));

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    preparedStatement.setString(7, String.valueOf(sqlDate));
                    preparedStatement.executeUpdate();

                ordersShowListData();
                ordersDisplayTotal();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private double totalP;
    public void ordersDisplayTotal(){
        customerId();
        String sql = "SELECT SUM(price) FROM customer WHERE customer_id = '"+customerid+"'";
        Connection connection = DatabaseConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                totalP = resultSet.getDouble("SUM(price)");
            }
            orders_Total.setText("$"+ String.valueOf(totalP));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    @FXML
    void orders_ResetBtn(ActionEvent event) {
        customerId();
        String sql = "DELETE FROM customer WHERE customer_id = '"+customerid+"'";
        Connection connection = DatabaseConnection.getConnection();

        try{
            if(!orderTableView.getItems().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to reset?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    Statement statement = connection.createStatement();
                    statement.execute(sql);

                    ordersShowListData();

                    totalP = 0;
                    balanceP = 0;
                    amountP = 0;

                    orders_Balance.setText("$0.0");
                    orders_Total.setText("$0.0");
                    orders_Amount.setText("");
                }

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    @FXML
    void orders_Recipe() {

    }

    @FXML
    void ordersPayBtn(ActionEvent event) {
        customerId();
        String sql = "INSERT INTO customer_receipt (customer_id, total, amount, balance, date)"
                + "VALUES(?, ?, ?, ?, ?)";

        Connection connection = DatabaseConnection.getConnection();
        try{
            Alert alert;
            if(totalP > 0 || orders_Amount.getText().isEmpty() || amountP == 0) {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");
                Optional<ButtonType> option =  alert.showAndWait();
                if (option.get() == ButtonType.OK){
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, String.valueOf(customerid));
                    preparedStatement.setString(2, String.valueOf(totalP));
                    preparedStatement.setString(3, String.valueOf(amountP));
                    preparedStatement.setString(4, String.valueOf(balanceP));
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    preparedStatement.setString(5, String.valueOf(sqlDate));

                    preparedStatement.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully ordered");
                    alert.showAndWait();

                    ordersShowListData();

                    totalP = 0;
                    balanceP = 0;
                    amountP = 0;

                    orders_Balance.setText("$0.0");
                    orders_Amount.setText("");
                } else return;
            }else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Total");
                alert.showAndWait();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private double amountP;
    private double balanceP;
    public void orderAmount(){
        Alert alert;

        if(!orders_Amount.getText().isEmpty()) {
            amountP = Double.parseDouble(orders_Amount.getText());

            if (totalP > 0) {
                if (amountP > totalP) {
                    balanceP = (amountP - totalP);
                    orders_Balance.setText("$" + String.valueOf(balanceP));
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Total");
                    alert.showAndWait();
                    orders_Amount.setText("");
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Total");
                alert.showAndWait();
            }
        }
        else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Total");
            alert.showAndWait();
        }
    }

    private SpinnerValueFactory<Integer> spinner;
    public void orderSpinner(){
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        orders_Quantity.setValueFactory(spinner);
    }
    private int quantity;
    public void orderShowSpinnerValue(){
         quantity = orders_Quantity.getValue();
    }


    public ObservableList<CustomerData> orderListData(){
        customerId();
        ObservableList<CustomerData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customer WHERE customer_id = '"+customerid+"'";
        Connection connection = DatabaseConnection.getConnection();
        try{
            CustomerData customerD;
            Statement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                customerD = new CustomerData(result.getInt("customer_id")
                        , result.getString("type")
                        , result.getString("brand")
                        , result.getString("productName")
                        , result.getInt("quantity")
                        , result.getDouble("price")
                        , result.getDate("date"));
                listData.add(customerD);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }
    private ObservableList<CustomerData> orderList;
    public void ordersShowListData(){
        orderList = orderListData();

        order_Col_Type.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getType()));
        order_Col_Brand.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getBrand()));
        order_Col_ProductName.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getProductName()));
        order_Col_Quantity.setCellValueFactory(c-> new SimpleIntegerProperty(c.getValue().getQuantity()));
        order_Col_Price.setCellValueFactory(c-> new SimpleDoubleProperty(c.getValue().getPrice()));

        orderTableView.setItems(orderList);
        ordersDisplayTotal();
    }

private int customerid;
    public void customerId(){
        String customerId = "SELECT * FROM customer";
        Connection connection = DatabaseConnection.getConnection();

        try {
            connection.prepareStatement(customerId);
            result = connection.createStatement().executeQuery(customerId);

            int checkId = 0;

            while (result.next()) {
//                GET LAST CUSTOMER ID
                customerid = result.getInt("customer_id");
            }
            String checkData = "SELECT * FROM customer_receipt";
            Statement statement = connection.createStatement();
            result = statement.executeQuery(checkData);

            while (result.next()) {
                checkId = result.getInt("customer_id");
            }
            if (customerid == 0){
                customerid+=1;
            }else if(checkId == customerid){
                customerid+=1;
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private String[] oderListType = {"Snacks", "Drinks", "Dessert", "Gadgets", "Personal Product", "Others"};
    public void oderListType(){
        List<String> oderList = new ArrayList<>();

        for(String oderType : oderListType){
            oderList.add(oderType);
        }
        ObservableList<String> listData = FXCollections.observableArrayList(oderList);
        orders_ProductType.setItems(listData);
        oderListBrand();
    }

    public void oderListBrand(){
        String sql = "SELECT * FROM product WHERE type='"
                +orders_ProductType.getSelectionModel().getSelectedItem()
                +"' and status =  'Available'";
        Connection connection = DatabaseConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            result = preparedStatement.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();
            while (result.next()) {
                listData.add(result.getString("brand"));
            }
            orders_BrandName.setItems(listData);
            orderListProductName();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void orderListProductName(){
        String sql = "SELECT productName FROM product WHERE brand = '"
                + orders_BrandName.getSelectionModel().getSelectedItem() +"'";
        Connection connection = DatabaseConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            result = preparedStatement.executeQuery();
            ObservableList listData = FXCollections.observableArrayList();
            while (result.next()) {
                listData.add(result.getString("productName"));
            }
            orders_ProductName.setItems(listData);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

//    COMMON FUNCTION:
//    SCENE CHANGE IN DASHBOARD
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

            homeDisplayTotalOrders();
            homeTotalIncome();
            homeAvailableProducts();
            homeIncomeChart();
            homeOrdersChart();

        } else if (event.getSource()== addProduct_Btn) {
            main_Form.setVisible(false);
            addProducts_from.setVisible(true);
            orders_form.setVisible(false);
//            css style
            home_Btn.setStyle(" -fx-background-color: rgba(255, 255, 255, 0.7)");
            addProduct_Btn.setStyle(" -fx-background-color: #fff");
            order_Btn.setStyle(" -fx-background-color: rgba(255, 255, 255, 0.7)");

            addProductListType();
            addProductListData();
            addProductSearch();
            addProductShowListData();
            addProductListStatus();

        } else if (event.getSource() == order_Btn) {
            main_Form.setVisible(false);
            addProducts_from.setVisible(false);
            orders_form.setVisible(true);
//            css Style
            home_Btn.setStyle(" -fx-background-color: rgba(255, 255, 255, 0.7)");
            addProduct_Btn.setStyle(" -fx-background-color: rgba(255, 255, 255, 0.7)");
            order_Btn.setStyle(" -fx-background-color: #fff");

            oderListType();
            oderListBrand();
            ordersShowListData();
            orderListProductName();
            orderSpinner();
        }
    }

//    Sign Out Function
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

//    System Close Function
    @FXML
    void close(ActionEvent event) {
        System.exit(0);
    }

//    System Close Function
    @FXML
    void minimize(ActionEvent event) {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);

    }
    @FXML
    void adminPane_Dragged(MouseEvent event) {
        Stage stage = (Stage) adminPane.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    void adminPane_Pressed(MouseEvent event) {
        x=event.getScreenX();
        y=event.getScreenY();
    }


//    Initialize Array list and others
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        homeDisplayTotalOrders();
        homeTotalIncome();
        homeAvailableProducts();
        homeIncomeChart();
        homeOrdersChart();


        addProductShowListData();
        addProductListType();
        addProductListStatus();

        ordersShowListData();
        oderListType();
        oderListBrand();
        orderListProductName();
        orderSpinner();


        File brandingFile = new File("img/main-logo.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandIcon.setImage(brandingImage);
    }
}
