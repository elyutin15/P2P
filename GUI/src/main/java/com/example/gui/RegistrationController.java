package com.example.gui;

import Client.Client;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class RegistrationController {
    public TextField loginField;
    public TextField passwordField;
    public TextField secondPasswordField;
    @FXML
    private Button exitButton;
    @FXML
    private Button minimizeButton;

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
    public void RgButtonController() throws Exception {
        String login = loginField.getText();
        String password = passwordField.getText();
        String secondPassword = secondPasswordField.getText();
        if (password.equals(secondPassword)) {
            Client client = new Client();
            client.sendMessage(
                    "command := register " +
                            "login := " + login + ' ' +
                            "password := " + password
            );
            if(client.getResponse().equals("true")) {
                Authorization authorization = new Authorization();
                authorization.start(GlovalValues._stage);
            }
        }
    }

    @FXML
    protected void cancelButtonController() throws Exception {
        Authorization authorization = new Authorization();
        authorization.start(GlovalValues._stage);
    }

    @FXML
    public void exitButtonController(ActionEvent actionEvent) {
        GlovalValues._stage.close();
    }

    @FXML
    public void minimizeWindowController(ActionEvent actionEvent){GlovalValues._stage.setIconified(true);
    }

}
