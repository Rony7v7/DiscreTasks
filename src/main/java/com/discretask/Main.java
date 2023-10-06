package com.discretask;

import java.io.IOException;

import com.discretask.ui.MainController;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {
    private static Scene scene;
    private static MainController mainController;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("mainView"));
        stage.setTitle("DiscreTasks");
        stage.setScene(scene);

        // Set the key event handler for the scene
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.isControlDown() && event.getCode() == KeyCode.Z) {
                    // Ctrl+Z is pressed, call your method here
                    System.out.println("Ctrl+Z is pressed");
                    mainController.undo();
                }
            }
        });

        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();
        mainController = fxmlLoader.getController();

        return root;
    }

}