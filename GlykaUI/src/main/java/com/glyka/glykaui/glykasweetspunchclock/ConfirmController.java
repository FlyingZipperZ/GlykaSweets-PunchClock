package com.glyka.glykaui.glykasweetspunchclock;

import com.glyka.glykaui.database.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;

public class ConfirmController {
    @FXML
    private void onBack() throws IOException {
        ViewSwitcher.switchTo(View.MAIN);
        System.out.println("Switching to Main View\n");
    }

    @FXML
    private Label lblTest;

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        boolean status = DBConnect.check_status(User.getName());
        if(status) {
            lblTest.setText("Clocked in Already");
        }
        else
            lblTest.setText("Clocked out Already");
    }
}
