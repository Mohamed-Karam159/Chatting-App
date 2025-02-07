module com.liqaa.server {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.liqaa.shared;
    requires mysql.connector.java;
    requires java.sql;
    requires com.zaxxer.hikari;
    requires org.slf4j;
    requires java.rmi;


    exports com.liqaa.server;
    exports com.liqaa.server.controllers.FXMLcontrollers to javafx.fxml;

    opens com.liqaa.server to javafx.fxml;
    opens com.liqaa.server.controllers.FXMLcontrollers to javafx.fxml;
    exports com.liqaa.server.controllers.reposotories.implementations;
}