module gov.iti.jets {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens gov.iti.jets to javafx.fxml;
    exports gov.iti.jets;
}