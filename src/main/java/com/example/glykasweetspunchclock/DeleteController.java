package com.example.glykasweetspunchclock;

import com.example.database.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.IOException;

public class DeleteController {

    @FXML
    private ComboBox<String> deleteDropDown;

    @FXML
    private Button deleteButton;
    @FXML
    private void deleteAction() throws IOException {
        User.setDelName(deleteDropDown.getValue());
        ViewSwitcher.switchTo(View.DELCONF);
        System.out.println("Are you sure you want to delete");
    }

    @FXML
    private Button cancelButton;
    @FXML
    private void cancelAction() throws IOException {
        ViewSwitcher.switchTo(View.MAIN);
        System.out.println("Canceling action");
    }

    @FXML
    private void initialize() {
        String[] names = User.getFNames();
        deleteDropDown.getItems().addAll(names);
    }
}
