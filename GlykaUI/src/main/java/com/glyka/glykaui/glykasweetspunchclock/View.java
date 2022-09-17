package com.glyka.glykaui.glykasweetspunchclock;

public enum View {
    MAIN("main.fxml"),
    IO("io.fxml"),
    ADD("add.fxml"),
    DELETE("delete.fxml"),
    DELCONF("delConfirmation.fxml"),
    CONFIRM("confirm.fxml");

    private final String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
