package com.example.gui;

import Client.Client;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AuthorizationController {

    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;

    @FXML
    protected void cancelButtonController() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    protected void minimizeWindowController() {
        Authorization._stage.setIconified(true);
    }

    @FXML
    protected void enterButtonController() throws Exception {
        String login = loginField.getText();
        String password = passwordField.getText();
//        Client client = new Client();
//        client.sendMessage(
//                "command := login " +
//                        "login := " + login + ' ' +
//                        "password := " + password
//        );
//        if(client.getResponse().equals("true")) {
        Registration registration = new Registration(Authorization._stage);
//        }
//        client.close();
    }

    @FXML
    protected void exitButtonController() {
        Platform.exit();
        System.exit(0);
    }

}