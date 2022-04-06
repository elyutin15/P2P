package com.example.gui;

import Client.Client;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AuthorizationController {

    @FXML
    private Button exitButton;
    @FXML
    private Button minimizeButton;
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;

    @FXML
    public void initialize(){
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
    protected void cancelButtonController() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    protected void minimizeWindowController() {
        GlovalValues._stage.setIconified(true);
    }

    @FXML
    protected void enterButtonController() throws Exception {
        String login = loginField.getText();
        String password = passwordField.getText();
        Client client = new Client();
        client.sendMessage(
                "command := login " +
                        "login := " + login + ' ' +
                        "password := " + password
        );
        if(client.getResponse().equals("true")) {
        GlovalValues._login = loginField.getText();
        MainMenu mainMenu = new MainMenu(GlovalValues._stage);
        }
        client.close();
    }
    @FXML
    protected void RgButton() throws Exception {
        Registration registration = new Registration(GlovalValues._stage);
    }

    @FXML
    protected void exitButtonController() {
        GlovalValues._stage.close();
    }
}