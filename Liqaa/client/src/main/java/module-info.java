module com.liqaa.client {
    requires com.liqaa.shared;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.liqaa.client to javafx.fxml;
    exports com.liqaa.client;
}