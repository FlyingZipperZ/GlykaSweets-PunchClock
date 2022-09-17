package com.glyka.glykaui.glykasweetspunchclock;

import com.glyka.glykaui.database.ConnectionClass;
import com.glyka.glykaui.database.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

// for the controller
// fx:controller="com.example.javafxproject.Controller"

public class MainController implements Initializable {
    String[][] names = User.getNames();

    @FXML
    private Button button1;
    @FXML
    private void buttonClick1(ActionEvent event) throws IOException {
        User.setName(button1.getText());
        ViewSwitcher.switchTo(View.IO);
        System.out.println("Switching to IO View\n");
    }

    @FXML
    private Button button2;
    @FXML
    private void buttonClick2() throws IOException {
        User.setName(button2.getText());
        ViewSwitcher.switchTo(View.IO);
        System.out.println("Switching to IO View\n");
    }

    @FXML
    private Button button3;
    @FXML
    private void buttonClick3() throws IOException {
        User.setName(button3.getText());
        ViewSwitcher.switchTo(View.IO);
        System.out.println("Switching to IO View\n");
    }

    @FXML
    private Button button4;
    @FXML
    private void buttonClick4(ActionEvent event) throws IOException {
        User.setName(button4.getText());
        ViewSwitcher.switchTo(View.IO);
        System.out.println("Switching to IO View\n");
    }

    @FXML
    private Button button5;
    @FXML
    private void buttonClick5() throws IOException {
        User.setName(button5.getText());
        ViewSwitcher.switchTo(View.IO);
        System.out.println("Switching to IO View\n");
    }

    @FXML
    private Button button6;
    @FXML
    private void buttonClick6() throws IOException {
        User.setName(button6.getText());
        ViewSwitcher.switchTo(View.IO);
        System.out.println("Switching to IO View\n");
    }

    @FXML
    private Button button7;
    @FXML
    private void buttonClick7(ActionEvent event) throws IOException {
        User.setName(button7.getText());
        ViewSwitcher.switchTo(View.IO);
        System.out.println("Switching to IO View\n");
    }

    @FXML
    private Button button8;
    @FXML
    private void buttonClick8() throws IOException {
        User.setName(button8.getText());
        ViewSwitcher.switchTo(View.IO);
        System.out.println("Switching to IO View\n");
    }

    @FXML
    private Button button9;
    @FXML
    private void buttonClick9() throws IOException {
        User.setName(button9.getText());
        ViewSwitcher.switchTo(View.IO);
        System.out.println("Switching to IO View\n");
    }

    @FXML
    private Button addButton;
    @FXML
    private void addPerson() throws IOException {
        ViewSwitcher.switchTo(View.ADD);
        System.out.println("Adding a new employee");
    }

    @FXML
    private Button deleteButton;
    @FXML
    private void deletePerson() throws IOException {
        ViewSwitcher.switchTo(View.DELETE);
        System.out.println("Deleting a old employee");
    }

    @FXML
    public Button restartButton;
    @FXML
    public void restartApp(ActionEvent event) throws IOException {

    }

    @FXML
    private Button fileButton;
    @FXML
    private void fileFinder() throws SQLException, ClassNotFoundException {
        Stage stage = null;
        final DirectoryChooser directoryChooser =
                new DirectoryChooser();
        final File selectedDirectory =
                directoryChooser.showDialog(stage);
        if (selectedDirectory != null) {
            selectedDirectory.getAbsolutePath();
        }
        System.out.println(selectedDirectory);
        Connection con = new ConnectionClass().getConnection();
        Statement stmt = con.createStatement();
        stmt.executeUpdate("TRUNCATE TABLE FileDestination;");
        stmt.executeUpdate("INSERT INTO FileDestination (\"file\") VALUES ('" + selectedDirectory + "');");
        stmt.close();
        con.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Loading user data...");

//        restartButton.setText("stop");
        restartButton.setText("Restart");
        restartButton.setVisible(false);

        button1.setText(names[0][0]);
        button2.setText(names[1][0]);
        button3.setText(names[2][0]);
        button4.setText(names[3][0]);
        button5.setText(names[4][0]);
        button6.setText(names[5][0]);
        button7.setText(names[6][0]);
        button8.setText(names[7][0]);
        button9.setText(names[8][0]);

        if (button1.getText() == null) {
            button1.setVisible(false);
        }
        if (button2.getText() == null) {
            button2.setVisible(false);
        }
        if (button3.getText() == null) {
            button3.setVisible(false);
        }
        if (button4.getText() == null) {
            button4.setVisible(false);
        }
        if (button5.getText() == null) {
            button5.setVisible(false);
        }
        if (button6.getText() == null) {
            button6.setVisible(false);
        }
        if (button7.getText() == null) {
            button7.setVisible(false);
        }
        if (button8.getText() == null) {
            button8.setVisible(false);
        }
        if (button9.getText() == null) {
            button9.setVisible(false);
        }
    }
}