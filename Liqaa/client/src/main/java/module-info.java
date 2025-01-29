module com.liqaa.client {
    requires com.liqaa.server;
    requires com.liqaa.shared;
    requires java.sql;
    requires javafx.fxml;
    requires javafx.controls;

    opens com.liqaa.client to javafx.fxml;
    exports com.liqaa.client;
}