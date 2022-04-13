package com.example.gui;

import Client.ClientServer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AuthorizationController {
    private static Stage _stage;
    @FXML
    private Button exitButton;
    @FXML
    private Button minimizeButton;
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;

    @FXML
    public void initialize() {
        ImageView exitView = new ImageView(getClass().getResource("/assets/exit.png").toExternalForm());
        exitView.setFitWidth(12);
        exitView.setFitHeight(12);
        exitButton.setGraphic(exitView);
        exitView.setPreserveRatio(true);

        ImageView minimizeView = new ImageView(getClass().getResource("/assets/minimize.png").toExternalForm());
        minimizeView.setFitWidth(12);
        minimizeView.setFitHeight(12);
        minimizeButton.setGraphic(minimizeView);
        minimizeView.setPreserveRatio(true);
    }

    @FXML
    protected void enterButtonController() throws Exception {
        String login = loginField.getText();
        String password = passwordField.getText();
        ClientServer clientSocket = new ClientServer();
        clientSocket.sendMessage(
                "command := login " +
                        "login := " + login + ' ' +
                        "password := " + password
        );
        if (clientSocket.getResponse().equals("true")) {
            GlovalValues._login = loginField.getText();
            Main mainMenu = new Main(_stage);
            MainController.setStage(_stage);
        }
        clientSocket.close();
    }

    @FXML
    protected void cancelButtonController() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    protected void minimizeWindowController() {
        _stage.setIconified(true);
    }

    @FXML
    protected void RgButtonController() throws Exception {
        Registration registration = new Registration(_stage);
    }

    @FXML
    protected void exitButtonController() {
        _stage.close();
    }

    static public void setStage(Stage stage) {
        _stage = stage;
    }
}