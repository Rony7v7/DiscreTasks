package com.discretask.ui;

import java.io.IOException;

import com.discretask.Main;
import com.discretask.model.DiscretasksSystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class MainController {

    private DiscretasksSystem controller;

    public MainController() {
        controller = new DiscretasksSystem();
    }

    @FXML
    private Button addButton;

    @FXML
    private RadioButton allRadioBTN;

    @FXML
    private RadioButton noPriorityRadioBTN;

    @FXML
    private RadioButton priorityRadioBTN;

    @FXML
    private ScrollPane taskViewer;

    public void addTask() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Add Task");
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("addTaskMenu.fxml"));
        Parent root = loader.load();
        addTaskMenuController addTaskController = loader.getController();
        stage.setScene(new Scene(root));
        addTaskController.setDiscretasksSystem(controller); // Inject the system
        addTaskController.setStage(stage);
        stage.show();
    }

    public DiscretasksSystem getController() {
        return controller;
    }
}
