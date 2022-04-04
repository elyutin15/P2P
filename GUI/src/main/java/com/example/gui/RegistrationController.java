package com.example.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class RegistrationController {
    @FXML
    private Button exitButton;
    @FXML
    private Button minimizeButton;

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
    protected void cancelButtonController() throws Exception {
        Authorization authorization = new Authorization();
        authorization.start(Authorization._stage);
    }

    @FXML
    public void exitButtonController(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void minimizeWindowController(ActionEvent actionEvent) {
        Authorization._stage.setIconified(true);
    }

    @FXML
    public void enterButtonController(ActionEvent actionEvent) {
    }
}
