module com.zksuvro.www.inventorymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.zksuvro.www.inventorymanagementsystem to javafx.fxml;
    exports com.zksuvro.www.inventorymanagementsystem;

    opens com.zksuvro.www.inventorymanagementsystem.controller to javafx.fxml;
    exports com.zksuvro.www.inventorymanagementsystem.controller;

}