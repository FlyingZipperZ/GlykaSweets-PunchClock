package com.example.glykasweetspunchclock;

import com.example.database.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class IoController {

    private Text lblTest;

    @FXML
    private void onClockIn() throws IOException, SQLException, ClassNotFoundException {
        DBConnect.DB_Clock_in(User.getName());
        ViewSwitcher.switchTo(View.CONFIRM);
        User.setName(null);
        System.out.println("Switching to Confirm View\n");
    }

    @FXML
    private void onClockOut() throws IOException, SQLException, ClassNotFoundException {
        DBConnect.DB_Clock_out(User.getName());
        ViewSwitcher.switchTo(View.CONFIRM);
        User.setName(null);
        System.out.println("Switching to Confirm View\n");
    }

    @FXML
    private void onBack() throws IOException {
        ViewSwitcher.switchTo(View.MAIN);
        System.out.println("Switching to Main View\n");
    }
}
