package com.example.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PrivateMessage extends Application {

    private static double xOffset = 0;
    private static double yOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane anchorPane = FXMLLoader.load((getClass().getResource("chat_scene.fxml")));
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
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("dialog closed");
        GlovalValues.isDialogOpened = false;
        PrivateMessageController.closeSocket(); //should not work
        super.stop();
    }

}
