package com.example.gui;

import Client.Client;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.util.Objects;

import static java.lang.System.*;

public class Authorization extends Application {

    private static double xOffset = 0;
    private static double yOffset = 0;
    public static Stage _stage;
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("authorization.fxml")));
        anchorPane.setOnMousePressed(event -> {
            xOffset = stage.getX() - event.getScreenX();
            yOffset = stage.getY() - event.getScreenY();
        });
        anchorPane.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() + xOffset);
            stage.setY(event.getScreenY() + yOffset);
        });
        Scene scene = new Scene(anchorPane,400,300);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        _stage = stage;
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
