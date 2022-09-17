package com.glyka.glykaui.glykasweetspunchclock;

import com.glyka.glykaui.database.User;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

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
