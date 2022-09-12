module com.example.glyka_test {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.glyka_test to javafx.fxml;
    exports com.example.glyka_test;
}