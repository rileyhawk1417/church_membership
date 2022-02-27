module com.cm.cm {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens com.cm.cm.app to javafx.fxml;
    opens com.cm.cm.controllers.misc to javafx.fxml;
    opens com.cm.cm.controllers.user to javafx.fxml;
    opens com.cm.cm.controllers.admin to javafx.fxml;
    opens com.cm.cm.controllers.cashier to javafx.fxml;
    opens com.cm.cm.modals to javafx.base;
    opens com.cm.cm.database to javafx.base;

    exports com.cm.cm.app;
    exports com.cm.cm.controllers.misc;
    exports com.cm.cm.controllers.admin;
    exports com.cm.cm.controllers.cashier;
    exports com.cm.cm.controllers.user;
    exports com.cm.cm.database;
    exports com.cm.cm.modals;

}