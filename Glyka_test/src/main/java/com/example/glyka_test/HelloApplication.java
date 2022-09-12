package com.example.glyka_test;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    int windowWidth = 1024;
    int windowHeight = 600;
//    @Override
//    public void start(Stage stage) throws IOException {
//        try {
//            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene1.fxml")));
//            Scene scene1 = new Scene(root);
//            stage.setScene(scene1);
//            stage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        // Create the Text
        Text text = new Text("Hello JavaFX");
        // Create the VBox
        VBox root = new VBox();
        // create GridPane
        GridPane gridPane = new GridPane();
        //Create a Button
        Button button = new Button("Poop");
        //Create a Button
        Button button1 = new Button("Poop");
        //Create a Button
        Button button2 = new Button("Poop");
        //Create a Button
        Button button3 = new Button("Poop");
        //Create a Button
        Button button4 = new Button("Poop");

        button.setOnAction(actionEvent -> {
            Parent p = null;
            try {
                p = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene1.fxml")));
                scene = new Scene(p);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
                });

        GridPane.setConstraints(button, 0,0);
        GridPane.setConstraints(button1, 0,1);
        GridPane.setConstraints(button2, 1,0);
        GridPane.setConstraints(button3, 1,1);
        GridPane.setConstraints(button4, 0,4);
        gridPane.getChildren().addAll(button);
        gridPane.getChildren().addAll(button1);
        gridPane.getChildren().addAll(button2);
        gridPane.getChildren().addAll(button3);
        gridPane.getChildren().addAll(button4);
        int x = windowWidth / 3;
        gridPane.getColumnConstraints().add(new ColumnConstraints(x));
        gridPane.getColumnConstraints().add(new ColumnConstraints(x));
//        gridPane.getColumnConstraints().add(new ColumnConstraints(x));
//        gridPane.getColumnConstraints().add(new ColumnConstraints(x));
//        gridPane.getColumnConstraints().add(new ColumnConstraints(x));
        gridPane.getRowConstraints().add(new RowConstraints(100));
        gridPane.getRowConstraints().add(new RowConstraints(100));
        gridPane.getRowConstraints().add(new RowConstraints(100));
        gridPane.getRowConstraints().add(new RowConstraints(100));
        gridPane.getRowConstraints().add(new RowConstraints(100));

        // Add the Text to the VBox
        root.getChildren().add(text);
        root.getChildren().add(gridPane);
        // Set the Size of the VBox
        root.setMinSize(windowWidth, windowHeight);

        // Create the Scene
        Scene scene = new Scene(root);

//        Parent p = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene1.fxml")));
//        scene = new Scene(p);
//        stage.setScene(scene);
//        stage.show();

        // Add the scene to the Stage
        stage.setScene(scene);
        // Set the title of the Stage
        stage.setTitle("Your first JavaFX Example");
        // Display the Stage
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}