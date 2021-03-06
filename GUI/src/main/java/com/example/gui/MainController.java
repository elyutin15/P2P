package com.example.gui;

import Client.ClientServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    public static Stage _stage;
    @FXML
    public Button exitButton;
    @FXML
    public Button minimizeButton;
    @FXML
    public TextField pasteKeyField;
    @FXML
    public TextField copyKeyField;


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
    public void createKeyButtonController(ActionEvent actionEvent) throws IOException {
        ClientServer client = new ClientServer();
        RandomKeyGenerator randomKeyGenerator = new RandomKeyGenerator();
        randomKeyGenerator.createKey();
        client.sendMessage(
                "command := createKey " +
                        "key := " + randomKeyGenerator.getKey() + ' ' +
                        "login := " + GlovalValues._login
        );
        client.close();
        copyKeyField.setText(randomKeyGenerator.getKey());
    }

    @FXML
    public void enterKeyButtonController(ActionEvent actionEvent) throws Exception {
        ClientServer clientSocket = new ClientServer();
        clientSocket.sendMessage("command := pasteKey " +
                "key := " + pasteKeyField.getText()
        );
        String friendIp = clientSocket.getResponse();

        if (!GlovalValues.isDialogOpened && friendIp != null) {
            PrivateMessage privateMessages = new PrivateMessage();
            Stage newStage = new Stage();
            privateMessages.start(newStage);
            PrivateMessageController.setStage(newStage);
            PrivateMessageController.setFriendIp(friendIp);
            GlovalValues.isDialogOpened = true;
        }
        clientSocket.close();
    }

    @FXML
    public void exitButtonController(ActionEvent actionEvent) {
        _stage.close();
    }

    @FXML
    public void minimizeWindowController(ActionEvent actionEvent) {
        _stage.setIconified(true);
    }

    public static void setStage(Stage stage) {
        _stage = stage;
    }
}
