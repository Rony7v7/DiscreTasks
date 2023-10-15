package com.discretask;

import java.io.IOException;

import com.discretask.ui.MainController;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


/**
 * The Main class serves as the entry point for the DiscreTasks JavaFX application. It initializes the
 * main user interface, sets up event handling for undo actions, and loads the initial scene.
 */
public class Main extends Application {

    /**
     * The scene variable represents the scene of the JavaFX application.
     */
    private static Scene scene;

    /**
     * The mainController variable represents the controller of the JavaFX application.
     */
    private static MainController mainController;

    /**
     * The main function is the entry point of the JavaFX application.
     * 
     * @param args The args parameter is an array of strings that are passed as command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * The start function is responsible for initializing the JavaFX application.
     * 
     * @param stage The stage parameter is a JavaFX Stage object that is used to display the JavaFX application.
     * @throws IOException The IOException is thrown when an input or output operation fails.
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("mainView"));
        stage.setTitle("DiscreTasks");
        stage.setScene(scene);
        stage.getIcons().add(new Image("/com/discretask/img/T.png"));

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.isControlDown() && event.getCode() == KeyCode.Z) {
                    mainController.undo();
                }
            }
        });

        scene.getStylesheets().add("/com/discretask/style.css");

        stage.show();
    }

    /**
     * The updateTaskList function updates the task list in the JavaFX application.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();
        mainController = fxmlLoader.getController();

        return root;
    }

}