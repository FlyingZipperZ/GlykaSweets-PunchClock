module com.example.glykasweetspunchclock {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires java.desktop;

    opens com.example.glykasweetspunchclock to javafx.fxml;
    exports com.example.glykasweetspunchclock;
}