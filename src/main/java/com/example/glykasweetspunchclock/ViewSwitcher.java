package com.example.glykasweetspunchclock;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ViewSwitcher {

    private static final Map<View, Parent> cache = new HashMap<>();

    private static Scene scene;

    public static void setScene(Scene scene) {
        ViewSwitcher.scene = scene;
    }

    public static void switchTo(View view) throws IOException {
        if(scene == null) {
            System.out.println("No scene was set");
            return;
        }

        try {
            Parent root;

            if (cache.containsKey(view) && view != View.CONFIRM) {
                System.out.println("Loading from cache");

                root = cache.get(view);
            } else {
                System.out.println("Loading from fxml");

                root = FXMLLoader.load(Objects.requireNonNull(ViewSwitcher.class.getResource(view.getFileName())));
                cache.put(view, root);
            }

            scene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}