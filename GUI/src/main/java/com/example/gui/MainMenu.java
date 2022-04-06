package com.example.gui;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Objects;

public class MainMenu {
    private static double xOffset = 0;
    private static double yOffset = 0;

    public MainMenu(Stage stage) throws Exception {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainMenu_scene.fxml")));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(anchorPane, 800, 600);
        stage.setX((screenBounds.getWidth() - 800) / 2);
        stage.setY((screenBounds.getHeight() - 600) / 2);
        stage.setScene(scene);
        stage.show();

        anchorPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = stage.getX() - event.getScreenX();
                yOffset = stage.getY() - event.getScreenY();
            }
        });
        anchorPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() + xOffset);
                stage.setY(event.getScreenY() + yOffset);
            }
        });

    }
}
