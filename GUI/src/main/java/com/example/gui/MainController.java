package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class MainController {

    @FXML
    public Button exitButton;

    @FXML
    public Button minimizeButton;

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
    public TextField copyKeyField;

    @FXML
    public void createKeyButtonController(ActionEvent actionEvent) {
        RandomKeyGenerator randomKeyGenerator = new RandomKeyGenerator();
        randomKeyGenerator.createKey();
        copyKeyField.setText(randomKeyGenerator.getKey());
    }

    @FXML
    public void enterKeyButtonController(ActionEvent actionEvent) {
    }

    @FXML
    public void exitButtonController(ActionEvent actionEvent) {
        GlovalValues._stage.close();
    }

    @FXML
    public void minimizeWindowController(ActionEvent actionEvent){GlovalValues._stage.setIconified(true);
    }
}
