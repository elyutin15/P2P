package com.example.gui;

import Client.Client;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private TextField login;
    private TextField password;
    private Button button;
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Регистрация");
        Group root = new Group();
        Scene scene = new Scene(root, 400, 300);

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

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Client c = new Client();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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