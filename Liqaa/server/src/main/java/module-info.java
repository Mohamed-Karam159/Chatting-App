module com.liqaa.server {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.liqaa.shared;
    requires java.sql;
    requires mysql.connector.java;

    exports com.liqaa.server;
    exports com.liqaa.server.controllers.FXMLcontrollers to javafx.fxml;

    opens com.liqaa.server to javafx.fxml;
    opens com.liqaa.server.controllers.FXMLcontrollers to javafx.fxml;
}