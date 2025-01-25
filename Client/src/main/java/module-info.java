module com.liqaa {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.liqaa to javafx.fxml;
    exports com.liqaa;
}
