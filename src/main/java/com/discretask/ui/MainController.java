package com.discretask.ui;

import java.io.IOException;

import com.discretask.Main;
import com.discretask.model.DiscretasksSystem;
import com.discretask.model.Task;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
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

    // This method is to add a taks
    public void addTask() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Add Task");
        // Load the FXML file (Trae el archivo FXML)
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("addTaskMenu.fxml"));
        Parent root = loader.load();
        // Esto es necesario para poder acceder a los metodos del controlador
        addTaskMenuController addTaskController = loader.getController();
        stage.setScene(new Scene(root));
        // Esto es para hacer una inyeccion de dependencias (Para que todos esten
        // instacaiados igual)
        addTaskController.setDiscretasksSystem(controller); // Inject the system
        addTaskController.setStage(stage);
        addTaskController.setMainController(this);
        stage.show();
    }

    public void updateTaskList() {
        taskViewer.setContent(null);

        VBox taskList = new VBox();// Esto sera lo que tiene nuestro ScrollPane al final, una caja Grande donde
                                   // pondremos nuestros TaskItem

        // Esto es para que se actualice la lista de tareas
        // Recorremos la cola de tareas para ir convirtiendo cada tarea a un TaskItem
        for (int i = 0; i < controller.getTasksByDeadLine().size(); i++) {
            // TODO: Comprobrar si esto funciona(No se si al sacar una tarea y volverla a
            // meter realmente lo estamos recorriendo)
            Task task = controller.getTasksByDeadLine().poll();

            // Cojemos cada tarea y la volvemos un TaskItem(Para asi poderlo mostrar)
            TaskItem taskItem = new TaskItem(task);
            taskList.getChildren().add(taskItem);
            VBox.setVgrow(taskItem, Priority.ALWAYS);
            controller.getTasksByDeadLine().offer(task);
        }
        // Con esto le decimos al ScrollPane que muestre la lista de tareas
        taskViewer.setContent(taskList);

        // Esto no me acuerdo que hace xd
        taskViewer.setFitToWidth(true);
    }

    public DiscretasksSystem getController() {
        return controller;
    }
}
