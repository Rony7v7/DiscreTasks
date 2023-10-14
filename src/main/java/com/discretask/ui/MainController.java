package com.discretask.ui;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.discretask.Main;
import com.discretask.model.DiscretasksSystem;
import com.discretask.model.Task;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The MainController class is responsible for initializing the JavaFX application.
 * This class is also responsible for updating the task list and opening a new window to add a task.
 */
public class MainController implements Initializable {

    /**
     * The controller variable is an instance of the DiscretasksSystem class.
     */
    private DiscretasksSystem controller;

    /**
     * The default constructor for the MainController class initializes the controller object.
     */
    public MainController() {
        controller = new DiscretasksSystem();
        controller.initSytem();
    }

    /**
     * The addButton variable is a JavaFX Button object that is used to add a task.
     */
    @FXML
    private Button addButton;

    /**
     * The allRadioBTN variable is a JavaFX RadioButton object that is used to select all tasks.
     */
    @FXML
    private RadioButton allRadioBTN;

    /**
     * The noPriorityRadioBTN variable is a JavaFX RadioButton object that is used to select tasks with no
     * priority.
     */
    @FXML
    private RadioButton noPriorityRadioBTN;

    /**
     * The priorityRadioBTN variable is a JavaFX RadioButton object that is used to select tasks with a
     * priority.
     */
    @FXML
    private RadioButton priorityRadioBTN;

    /**
     * The taskViewer variable is a JavaFX ScrollPane object that is used to display tasks.
     */
    @FXML
    private ScrollPane taskViewer;

    /**
     * The initialize function sets the allRadioBTN as selected and updates the task list.
     * 
     * @param location The location of the FXML file that contains the controller class.
     * @param resources The `resources` parameter is a `ResourceBundle` object that contains the localized
     *                  resources for the specified location. It can be used to retrieve localized strings or other
     *                  resources based on the current locale.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allRadioBTN.setSelected(true);
        updateTaskList();
    }

    /**
     * This function opens a new window to add a task and sets up the necessary dependencies.
     */
    public void addTask() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Add Task");
        stage.getIcons().add(new Image("/com/discretask/img/T.png"));
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("addTaskMenu.fxml"));
        Parent root = loader.load();
        AddTaskMenuController addTaskController = loader.getController();
        stage.setScene(new Scene(root));
        addTaskController.setStage(stage);
        addTaskController.setMainController(this);
        stage.show();
    }

    /**
     * The function updates the task list based on the selected radio button option.
     */
    public void updateTaskList() {

        if (allRadioBTN.isSelected()) {
            updateTaskList(controller.getSortedHeap("DEADLINE"));
        } else if (priorityRadioBTN.isSelected()) {

            updateTaskList(controller.getSortedHeap("PRIORITY"));
        } else if (noPriorityRadioBTN.isSelected()) {
            updateTaskList(controller.getNonPriorityTasks().toArray(Task.class));
        }

    }

    /**
     * The function updates the task list by converting each task in the task array into a TaskItem and
     * displaying it in a VBox within a ScrollPane.
     * 
     * @param taskArray The taskArray parameter is an array of Task objects.
     */
    public void updateTaskList(Task[] taskArray) {
        taskViewer.setContent(null);

        VBox taskList = new VBox();

        for (int i = 0; i < taskArray.length; i++) {
            Task task = taskArray[i];
            TaskItem taskItem = new TaskItem(task, this);
            taskList.getChildren().add(taskItem);
            VBox.setVgrow(taskItem, Priority.ALWAYS);
        }

        taskViewer.setContent(taskList);

        taskViewer.setFitToWidth(true);
    }

    /**
     * The function modifies a task by calling a controller method to edit the task's properties and then
     * updates the task list.
     * 
     * @param oldTitle The old title of the task that needs to be modified.
     * @param title The new title for the task.
     * @param content The content parameter is a String that represents the new content of the task.
     * @param priority The priority parameter is of type com.discretask.model.Priority, which is likely an
     *                 enum representing different levels of priority for a task.
     * @param category The category parameter is a String that represents the category of the task.
     * @param deadLine The `deadLine` parameter is a `Calendar` object that represents the deadline for the
     *                 task.
     */
    public void modifyTask(String oldTitle, String title, String content,
            com.discretask.model.Priority priority, String category,
            Calendar deadLine) {
        controller.editTask(oldTitle, title, content, priority, category, deadLine, false);
        updateTaskList();
    }

    /**
     * The deleteTask function deletes a task by calling the deleteTask method in the controller and then
     * updates the task list.
     * 
     * @param taskName The taskName parameter is a String that represents the name of the task that needs
     *                 to be deleted.
     */
    public void deleteTask(String taskName) {
        controller.deleteTask(taskName, false);
        updateTaskList();
    }

    /**
     * The function returns the controller object of the DiscretasksSystem class.
     * 
     * @return The method is returning an instance of the DiscretasksSystem class, which is the controller
     *         object.
     */
    public DiscretasksSystem getController() {
        return controller;
    }

    /**
     * The `undo` function calls the `undo` method of the `controller` object and then updates the task
     * list.
     */
    public void undo() {
        controller.undo();
        updateTaskList();
    }

}
