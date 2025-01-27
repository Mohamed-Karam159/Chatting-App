module com.liqaa {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.liqaa;
    exports com.liqaa.controllers to javafx.fxml;

    opens com.liqaa.controllers to javafx.fxml; // Required for reflection
}
