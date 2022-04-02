package com.example.gui;

import Client.Client;
import javafx.application.Application;
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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    private TextField login;
    private TextField password;
    @FXML
    private Button button;
    private VBox vBox;
    @Override
    public void start(Stage stage)  {
        stage.setTitle("Регистрация");
        Group root = new Group();
        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);

        stage.setMaxWidth(scene.getWidth());
        stage.setMinWidth(scene.getWidth());
        stage.setMaxHeight(scene.getHeight());
        stage.setMinHeight(scene.getHeight());
        login = new TextField();
        login.setPrefWidth(150);
        login.setPrefHeight(20);
        login.setPromptText("Введите логин");
        login.setLayoutX(scene.getWidth() / 2 - login.getPrefWidth() / 2);
        login.setLayoutY(scene.getHeight() / 2 - 4 * login.getPrefHeight());

        password = new TextField();
        password.setPrefWidth(150);
        password.setPrefHeight(20);
        password.setPromptText("Введите пароль");
        password.setLayoutX(scene.getWidth() / 2 - password.getPrefWidth() / 2);
        password.setLayoutY(scene.getHeight() / 2 - 2 * password.getPrefHeight());

        button = new Button();
        button.setPrefWidth(150);
        button.setPrefHeight(20);
        button.setText("Зарегистрироваться");
        button.setLayoutX(scene.getWidth() / 2 - password.getPrefWidth() / 2);
        button.setLayoutY(scene.getHeight() / 2);

        button.setOnAction(actionEvent -> {
            try {
                Client c = new Client();
                c.sendMessage("command := register " + login.getText() + ' ' + password.getText());
                c.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        root.getChildren().add(login);
        root.getChildren().add(password);
        root.getChildren().add(button);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}