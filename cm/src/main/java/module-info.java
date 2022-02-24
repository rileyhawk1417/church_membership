module com.c_m.cm {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                        requires org.kordamp.bootstrapfx.core;
        
    opens com.c_m.cm to javafx.fxml;
    exports com.c_m.cm;
}