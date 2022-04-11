package com.example.gui;

import Client.Client;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class Authorization extends Application {

    private static double xOffset = 0;
    private static double yOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("authorization_scene.fxml")));
        anchorPane.setOnMousePressed(event -> {
            xOffset = stage.getX() - event.getScreenX();
            yOffset = stage.getY() - event.getScreenY();
        });
        anchorPane.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() + xOffset);
            stage.setY(event.getScreenY() + yOffset);
        });
        Scene scene = new Scene(anchorPane, 400, 300);
        stage.setScene(scene);
        if (!stage.getStyle().equals(StageStyle.UNDECORATED))
            stage.initStyle(StageStyle.UNDECORATED);
        GlovalValues._stage = stage;
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        System.out.println("exit " + GlovalValues._login);
        if(GlovalValues._login!=null) {
            Client client = new Client();
            client.sendMessage(
                    "command := logout " +
                            "login := " + GlovalValues._login
            );
        }
        Platform.exit();
        System.exit(0);
        super.stop();
    }
}
