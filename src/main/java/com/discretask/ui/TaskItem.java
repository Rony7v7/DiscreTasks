package com.discretask.ui;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import com.discretask.Main;
import com.discretask.model.Priority;
import com.discretask.model.Task;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


/**
 * This class is important for creating tasks for screen display. 
 * Its core function is to receive task data and generate an object suitable 
 * for on-screen presentation. The task itself is organized as a vertical 
 * rectangle, divided into three distinct horizontal segments, each serving 
 * a specific purpose. The left segment is dedicated to the task's title, 
 * description, and category. In the middle segment, the priority is conveyed. 
 * On the right, you'll find the action button and relevant delivery date information.
 */
public class TaskItem extends HBox {

    /**
     * The optionsButton variable is a JavaFX Button object that is used to open a
     * context menu.
     */
    private Button optionsButton;

    /**
     * The sdf variable is a SimpleDateFormat object that is used to format the
     * date.
     */
    private SimpleDateFormat sdf;

    /**
     * The eliminarMenuItem variable is a JavaFX MenuItem object that is used to
     * delete a task.
     */
    private MenuItem eliminarMenuItem;

    /**
     * The modificarMenuItem variable is a JavaFX MenuItem object that is used to
     * modify a task.
     */
    private MenuItem modificarMenuItem;

    /**
     * The contextMenu variable is a JavaFX ContextMenu object that is used to
     * display a context menu.
     */
    private ContextMenu contextMenu;

    /**
     * The controller variable is an instance of the MainController class.
     */
    private MainController controller;

    /**
     * The task variable is an instance of the Task class.
     */
    private Task task;

    /**
     * The default constructor for the TaskItem class initializes the controller and
     * task objects. It also initializes the eliminarMenuItem, modificarMenuItem,
     * contextMenu, sdf, and optionsButton objects.
     * 
     * @param task       The task parameter is of type Task, which means it can be
     *                   any type of task.
     * @param controller The controller parameter is of type MainController, which
     *                   means it can be any type of MainController.
     */
    public TaskItem(Task task, MainController controller) {
        this.controller = controller;
        this.task = task;
        eliminarMenuItem = new MenuItem("Eliminar");
        modificarMenuItem = new MenuItem("Modificar");
        contextMenu = new ContextMenu();
        sdf = new SimpleDateFormat("dd-MM-yyyy");
        optionsButton = new Button();
        contextMenu.getItems().addAll(eliminarMenuItem, modificarMenuItem);

        configureOptionsButton();

        // Add VBox containers to HBox
        getChildren().addAll(buildLeftVbox(task), buildMiddleVbox(task), buildRightVbox(task));

        // Configure spacing between VBox containers
        setSpacing(10); // Espacio entre los VBox

        // Con esto le ponemos un borde al TaskItem
        setStyle(
                "-fx-background-color:  #EEEEEE ;-fx-border-color: gray; -fx-border-width: 1px ; -fx-padding: 10px; -fx-border-radius: 5px; ");
    }

    /**
     * The buildLeftVbox function creates a VBox object that contains the task's
     * title, description, and category.
     * 
     * @param task The task parameter is of type Task, which means it can be any
     *             type of task.
     * @return The method is returning a VBox object.
     */
    private VBox buildLeftVbox(Task task) {
        // Convert all of the task's strings to labels (things that can be placed
        // inside the vertical box)
        Label titleLabel = new Label(task.getTitle());
        Label descriptionLabel = new Label(task.getContent());
        Label categoryLabel = new Label(task.getUserCategory());

        // Call the function below to apply the same style to all labels
        applyStylesToLabel(titleLabel);
        applyStylesToLabel(descriptionLabel);
        applyStylesToLabel(categoryLabel);
        descriptionLabel.setStyle("-fx-wrap-text: true;");

        // Put all of these elements inside a VBox so that we can display them
        VBox leftVbox = new VBox(titleLabel, descriptionLabel, categoryLabel);
        
        // This is to expand the VBox and fill everything
        HBox.setHgrow(leftVbox, javafx.scene.layout.Priority.ALWAYS);

        // Add a flexible margin to the VBox (adjusted to the total width)
        setMargin(leftVbox, new Insets(0, this.getWidth() / 3, 0, 0));
        leftVbox.setStyle("-fx-alignment: top-left; -fx-border-radius: 5px;");

        return leftVbox;
    }

    /**
     * The buildMiddleVbox function creates a VBox object that contains the task's
     * priority.
     * 
     * @param task The task parameter is of type Task, which means it can be any
     *             type of task.
     * @return The method is returning a VBox object.
     */
    private VBox buildMiddleVbox(Task task) {
        // Construct the middle VBox with the priority: The circle representing 
        // the priority color.
        Label priorityLabel = new Label("Priority: ");
        Priority priority = task.getPriority();
        Circle priorityCircle = priorityCircle(priority);

        // Place these elements within a helper box to align them side by side.
        HBox helperBox = new HBox(priorityLabel, priorityCircle);

        // Ensure all items in the helper box are centered at the bottom.
        helperBox.setAlignment(Pos.BOTTOM_CENTER);

        // Include the helper box within the middle vertical box.
        VBox middleVbox = new VBox(helperBox);
        middleVbox.setAlignment(Pos.BOTTOM_CENTER);

        // Set the margin and make the box occupy the same space as others with Hgrow.
        setMargin(middleVbox, new Insets(0, 0, 0, this.getWidth() / 3));
        HBox.setHgrow(middleVbox, javafx.scene.layout.Priority.ALWAYS);
        middleVbox.setStyle("-fx-border-radius: 5px;");
        return middleVbox;
    }

    /**
     * The priorityCircle function creates a Circle object that represents the
     * priority of a task.This method is used to put the color of the priority.
     * 
     * @param priority The priority parameter is of type Priority, which means it can
     *                 be any type of priority.
     * @return The method is returning a Circle object.
     */
    private Circle priorityCircle(Priority priority) {
        Circle priorityCircle = new Circle(6);
        if (priority == Priority.HIGH_PRIORITY) {
            priorityCircle.setFill(Color.RED);
        } else if (priority == Priority.MEDIUM_PRIORITY) {
            priorityCircle.setFill(Color.ORANGE);
        } else if (priority == Priority.LOW_PRIORITY) {
            priorityCircle.setFill(Color.YELLOW);
        } else if (priority == Priority.OPTIONAL) {
            priorityCircle.setFill(Color.BLUE);
        } else {
            priorityCircle.setFill(Color.LIGHTGREEN);
        }
        return priorityCircle;
    }

    /**
     * The buildRightVbox function creates a VBox object that contains the task's
     * deadline.
     * 
     * @param task The task parameter is of type Task, which means it can be any
     *             type of task.
     * @return The method is returning a VBox object.
     */
    private VBox buildRightVbox(Task task) {
        // Create a label with the date
        Label dateLabel = new Label(sdf.format(task.getDeadline().getTime()));

        // Apply the same style to the label
        applyStylesToLabel(dateLabel);
        dateLabel.setAlignment(Pos.BOTTOM_RIGHT);
        // Metemos el boton dentro de una caja auxiliar para que quede en el centro

        // Place the button within a helper box for centering
        configureOptionsButton();
        HBox helperBox = new HBox(optionsButton);
        helperBox.setAlignment(Pos.CENTER_RIGHT);

        // Combine the button, label, and date within the vertical box
        VBox rightVbox = new VBox(helperBox, dateLabel);
        rightVbox.setAlignment(Pos.BOTTOM_RIGHT);
        
        // Attempt to position the button in the top right corner by setting the helper box's height
        helperBox.setPrefHeight(rightVbox.getPrefHeight() / 2);
        HBox.setHgrow(rightVbox, javafx.scene.layout.Priority.ALWAYS);
        rightVbox.setStyle("-fx-border-radius: 5px;");
        return rightVbox;

    }

    /**
     * The configureOptionsButton function configures the optionsButton object.
     */
    private void configureOptionsButton() {

        ImageView imageView = new ImageView(new Image("/com/discretask/img/three-dots.png"));
        imageView.setFitWidth(25); // Set the width to 32 pixels
        imageView.setFitHeight(25); // Set the height to 32 pixels
        optionsButton.setGraphic(imageView);
        optionsButton.setStyle(
                " -fx-text-fill: black;" +
                        "-fx-alignment: top-right; -fx-font-weight: bold; " +
                        "-fx-font-size: 1em; -fx-border-width: 1;" +
                        "-fx-background-color: transparent;");
        // Configure the event to toggle the ContextMenu's visibility
        optionsButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) { // Check if left button is clicked
                if (contextMenu.isShowing()) {
                    contextMenu.hide();
                } else {
                    contextMenu.show(optionsButton, Side.TOP, 0, 0);
                }
            }
        });

        // Configure actions for the menu items
        eliminarMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this task?");

            // Show the confirmation dialog and wait for user's response
            Optional<ButtonType> result = alert.showAndWait();

            // If the user clicks OK, delete the task
            if (result.isPresent() && result.get() == ButtonType.OK) {
                controller.deleteTask(task.getId());
            }
        });

        modificarMenuItem.setOnAction(event -> {
            try {
                openModifyMenu();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * The openModifyMenu function opens a new window to modify a task and sets up
     * the necessary dependencies.
     * 
     * @throws IOException The IOException is thrown if an input or output
     *                     exception occurs.
     */
    private void openModifyMenu() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Modify Task");
        // Load the FXML file (Trae el archivo FXML)
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("addTaskMenu.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("/com/discretask/img/T.png"));

        // It is necessary to access the methods of the controller
        AddTaskMenuController addTaskMenuController = loader.getController();
       
        // This is for dependency injection (to ensure uniform 
        //instantiation of all components).
        addTaskMenuController.setMainController(controller);
        addTaskMenuController.setStage(stage);
        changeAddTaskMenuToModifyTaskMenu(addTaskMenuController);
        stage.show();
    }

    /**
     * The changeAddTaskMenuToModifyTaskMenu function changes the AddTaskMenu to a
     * ModifyTaskMenu.
     * 
     * @param addTaskMenuController The addTaskMenuController parameter is of type
     *                              AddTaskMenuController, which means it can be any
     *                              type of AddTaskMenuController.
     */
    private void changeAddTaskMenuToModifyTaskMenu(AddTaskMenuController addTaskMenuController) {
        changeTitles(addTaskMenuController);
        addTaskMenuController.setIsEditing(true);
        addTaskMenuController.setTaskID(task.getId());
        addTaskMenuController.setTitleInput(task.getTitle());
        addTaskMenuController.setDescriptionInput(task.getContent());
        addTaskMenuController.setPriorityInput(task.getPriority());
        addTaskMenuController.setCategoryInput(task.getUserCategory());
        addTaskMenuController.setDeadlineInput(task.getDeadline());
    }

    /**
     * The changeTitles function changes the titles of the AddTaskMenu to the
     * ModifyTaskMenu.
     * 
     * @param addTaskMenuController The addTaskMenuController parameter is of type
     *                              AddTaskMenuController, which means it can be any
     *                              type of AddTaskMenuController.
     */
    private void changeTitles(AddTaskMenuController addTaskMenuController) {
        addTaskMenuController.setHeader("Modify Task");
        addTaskMenuController.setTitleLabel("New Title");
        addTaskMenuController.setDescriptionLabel("New Description");
        addTaskMenuController.setPriorityLabel("New Priority");
        addTaskMenuController.setCategoryLabel("New Category");
        addTaskMenuController.setDeadLineLabel("New Deadline");
        addTaskMenuController.setSubmitBTNText("Modify Task!");
    }

    /**
     * The applyStylesToLabel function applies the same style to all labels.
     * 
     * @param label The label parameter is of type Label, which means it can be any
     *              type of label.
     */
    private void applyStylesToLabel(Label label) {
        label.setStyle("-fx-font-weight: bold;");
        label.setStyle("-fx-alignment: center;");
        label.setPrefHeight(USE_COMPUTED_SIZE);
    }

}