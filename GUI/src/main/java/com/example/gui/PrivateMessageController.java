package com.example.gui;

import Client.ClientClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class PrivateMessageController {
    private static ClientClient clientSocket;
    private static Stage _stage;
    private static String _friendIp;
    @FXML
    public Button exitButton;
    @FXML
    public Button minimizeButton;
    @FXML
    public TextField messageField;

    //-----------------------------------------------------------------------//
    //                                                                       //
    //    ////////////////  socket to second client  ///////////////////     //
    //    /              / ------------------------> /                 /     //
    //    / first client /                           /  second client  /     //
    //    /              / <------------------------ /                 /     //
    //    ////////////////  socket to first client   ///////////////////     //
    //         ^                                                 ^           //
    //         |              /////////////////////              |           //
    //         |              /                   /              |           //
    //         ---------------/  the main server  /---------------           //
    //     ip of 2nd client   /                   /  ip of 1st client        //
    //                        /////////////////////                          //
    //_______________________________________________________________________//

    @FXML
    public void initialize() throws IOException {
        clientSocket = new ClientClient(_friendIp);

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
    public static void setStage(Stage stage) {
        _stage = stage;
    }

    public static void setFriendIp(String friendIp) {
        _friendIp = friendIp;
    }

    public static void closeSocket() throws IOException { //should not work
        clientSocket.closeSocket();
    }

    @FXML
    public void exitButtonController(ActionEvent actionEvent) throws Exception {
        _stage.close();
        System.out.println(_friendIp);
        new PrivateMessage().stop();
    }

    @FXML
    public void minimizeWindowController(ActionEvent actionEvent) {
        _stage.setIconified(true);
    }

    @FXML
    public void sendMessageButtonController() throws IOException {
        clientSocket.sendMessage(messageField.getText());
    }
}
