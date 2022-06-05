module com.cm.cm {
    requires MaterialFX;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;
    requires java.sql;
    requires org.update4j;
    requires org.apache.poi.ooxml;
    requires virtualizedfx;
    requires PDFViewerFX;

    opens com.cm.cm.app to javafx.fxml;
    opens com.cm.cm.controllers.misc to javafx.fxml;
    opens com.cm.cm.controllers.user to javafx.fxml;
    opens com.cm.cm.controllers.admin to javafx.fxml;
    opens com.cm.cm.modals to javafx.base;
    opens com.cm.cm.database to javafx.base;
//    opens com.cm.cm.updateConfig.view to javafx.fxml;
//    opens com.cm.cm.updateConfig.services to javafx.base;
    opens com.cm.cm.controllers.admin.usersCtrl to javafx.fxml;

    exports com.cm.cm;
    exports com.cm.cm.app;
    exports com.cm.cm.controllers.misc;
    exports com.cm.cm.controllers.admin;
    exports com.cm.cm.controllers.user;
    exports com.cm.cm.database;
    exports com.cm.cm.modals;
    /*
    exports com.cm.cm.updateConfig.view;
    exports com.cm.cm.updateConfig.services;
    exports com.cm.cm.updateConfig.misc;
    */
    exports com.cm.cm.controllers.admin.usersCtrl;
//    exports com.cm.cm.updater.config;


}
