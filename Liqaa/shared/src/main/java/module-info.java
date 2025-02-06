module com.liqaa.shared {
    exports com.liqaa.shared.models.entities;
    exports com.liqaa.shared.models.enums;
    exports com.liqaa.shared.exceptions;
    exports com.liqaa.shared.models;
    exports com.liqaa.shared.util;

    requires java.sql;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;
}