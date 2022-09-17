package com.glyka.glykaui.glykasweetspunchclock;

import com.glyka.glykaui.database.ConnectionClass;
import com.glyka.glykaui.database.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DelConfirmationController {

    @FXML
    private Label nameDisplay;

    @FXML
    private Button yesButton;
    @FXML
    private void yesButtonPress() throws SQLException, ClassNotFoundException, IOException {
        Connection con = new ConnectionClass().getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id FROM glykasweets_user WHERE \"firstName\" = '" + User.getDelName() + "';");
        rs.next();
        int idNumber = rs.getInt("id");
        //                               DELETE FROM clock_in WHERE "id" = '6';
        stmt.executeUpdate(String.format("DELETE FROM clock_in WHERE \"id\" = '%d';", idNumber));
        stmt.executeUpdate(String.format("DELETE FROM clock_out WHERE \"id\" = '%d';", idNumber));
        stmt.executeUpdate("DELETE FROM glykasweets_user WHERE \"firstName\" = '" + User.getDelName() + "';");
        rs.close();
        stmt.close();
        con.close();
        ViewSwitcher.switchTo(View.MAIN);
    }

    @FXML
    private Button noButton;
    @FXML
    private void noButtonPress() throws IOException {
        ViewSwitcher.switchTo(View.MAIN);
    }

    @FXML
    private void initialize() {
        nameDisplay.setText(User.getDelName());
    }
}
