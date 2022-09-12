package com.example.glykasweetspunchclock;

import com.example.database.ConnectionClass;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddController {

    @FXML
    public TextField fname;
    @FXML
    public TextField lname;
    @FXML
    public TextField rate;

    @FXML
    public Button acceptButton;
    @FXML
    public void acceptAction() throws IOException, SQLException, ClassNotFoundException {
        ViewSwitcher.switchTo(View.MAIN);
        System.out.println(fname.getText());
        System.out.println(lname.getText());
        System.out.println(rate.getText());
        Connection con = new ConnectionClass().getConnection();
        Statement stmt = con.createStatement();
        stmt.executeQuery(String.format("INSERT into glykasweets_user (\"firstName\", \"lastName\", \"rate\", \"io\", \"createdAt\", \"updatedAt\") " +
                "values ('%s', '%s', %d, false, now(), now());", fname.getText(), lname.getText(), Integer.parseInt(rate.getText())));
        con.close();
    }

    @FXML
    public Button cancelButton;
    @FXML
    public void cancelAction() throws IOException {
        ViewSwitcher.switchTo(View.MAIN);
        System.out.println("Action canceled no new employees added: return to main view");
    }
}
