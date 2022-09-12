package com.example.glykasweetspunchclock;

import com.example.database.User;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class App extends Application {

    int windowWidth = 1024;
    int windowHeight = 600;

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new Pane());

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());

        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.MAIN);

        Image image = new Image("file:src/main/java/com/example/graphic/Glyka_logo.jpg");
        stage.getIcons().add(image);

        stage.setTitle("Glyka Sweets");
        stage.setScene(scene);
        stage.show();
    }

    public void stop() {

    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        User.getNamesFromDB();
        launch();
    }
}

// Resource used
// https://www.youtube.com/watch?v=LMdjhuYSrqg JavaFX GUI tutorial

//View Switcher example
//https://www.youtube.com/watch?v=DUpQGpHsRMg