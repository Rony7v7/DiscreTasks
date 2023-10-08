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
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Esta clase es necesaria para crear la tarea que se vera en la pantalla.
 * 
 * El objetivo de esta clase es el de recibir una tarea y crear un objeto que
 * podamos mostrar en pantalla
 * 
 * 
 * la tarea esta pensada como una rectangulo vertical el cual esta dividido en 3
 * rectangulos horizontales,
 * cada uno de estos rectangulos tiene un contenido propio.
 * 
 * El rectangulo de la izquierda es para el titulo, la descripcion y la
 * categoria
 * 
 * El rectangulo del medio es para la prioridad
 * 
 * El rectangulo de la derecha es para el boton y para la fecha de entrega
 */
public class TaskItem extends HBox {

    private Button optionsButton;
    private SimpleDateFormat sdf;
    private MenuItem eliminarMenuItem;
    private MenuItem modificarMenuItem;
    private ContextMenu contextMenu;
    private MainController controller;
    private Task task;

    public TaskItem(Task task, MainController controller) {
        this.controller = controller;
        this.task = task;
        eliminarMenuItem = new MenuItem("Eliminar");
        modificarMenuItem = new MenuItem("Modificar");
        contextMenu = new ContextMenu();
        sdf = new SimpleDateFormat("dd-MM-yyyy");
        optionsButton = new Button(". . .");
        contextMenu.getItems().addAll(eliminarMenuItem, modificarMenuItem);

        configureOptionsButton();

        // Add VBox containers to HBox
        getChildren().addAll(buildLeftVbox(task), buildMiddleVbox(task), buildRightVbox(task));

        // Configure spacing between VBox containers
        setSpacing(10); // Espacio entre los VBox

        // Con esto le ponemos un borde al TaskItem
        setStyle("-fx-border-color: gray; -fx-border-width: 0 0 1px 0; -fx-padding: 10px;");
    }

    private VBox buildLeftVbox(Task task) {
        // Vuelve todos esos String de la Tarea a Labels (Cosas que se pueden poner
        // dentro de la caja vertical)
        Label titleLabel = new Label(task.getTitle());
        Label descriptionLabel = new Label(task.getContent());
        Label categoryLabel = new Label(task.getUserCategory());

        // Llamamos a la funcion de abajo para que todos los labels tengan el mismo
        // estilo
        applyStylesToLabel(titleLabel);
        applyStylesToLabel(descriptionLabel);
        applyStylesToLabel(categoryLabel);
        descriptionLabel.setStyle("-fx-wrap-text: true;");

        // (Ponemos todos estos elementos dentro de la caja para poder mostrarla)
        VBox leftVbox = new VBox(titleLabel, descriptionLabel, categoryLabel);
        // Esto es para que se expanda el VBox y rellene todo
        HBox.setHgrow(leftVbox, javafx.scene.layout.Priority.ALWAYS);

        // Añadir un margen flexible al VBox (ajustado al ancho total)
        setMargin(leftVbox, new Insets(0, this.getWidth() / 3, 0, 0));
        leftVbox.setStyle("-fx-alignment: top-left;");

        return leftVbox;
    }

    private VBox buildMiddleVbox(Task task) {
        // Contruimos el VBox del medio con el priority: El circulo del color de la
        // prioridad
        Label priorityLabel = new Label("Priority: ");
        Priority priority = task.getPriority();
        Circle priorityCircle = priorityCircle(priority);

        // Metemos estos elementos dentro de una caja auxiliar para que queden lado a
        // lado
        HBox helperBox = new HBox(priorityLabel, priorityCircle);

        // Hacemos que todos los items de la caja auxiliar esten en el centro abajo
        helperBox.setAlignment(Pos.BOTTOM_CENTER);

        // Metemos la caja auxiliar a la caja vertical del medio
        VBox middleVbox = new VBox(helperBox);
        middleVbox.setAlignment(Pos.BOTTOM_CENTER);

        // Ponemos el margin y hacemos que la caja ocupe lo mismo que los demas con el
        // Hgrow
        setMargin(middleVbox, new Insets(0, 0, 0, this.getWidth() / 3));
        HBox.setHgrow(middleVbox, javafx.scene.layout.Priority.ALWAYS);

        return middleVbox;
    }

    // Este metodo es que segun la prioridad le damos un color al boton
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

    private VBox buildRightVbox(Task task) {
        // Creamos el label con la fecha
        Label dateLabel = new Label(sdf.format(task.getDeadline().getTime()));

        // Le ponemos los mismos estilos que a los textos
        applyStylesToLabel(dateLabel);
        dateLabel.setAlignment(Pos.BOTTOM_RIGHT);
        // Metemos el boton dentro de una caja auxiliar para que quede en el centro

        // Configuramos el boton (Esta funcion sera util mas adelante para que cuando
        // alguien oprima el boton se abra un menu)
        configureOptionsButton();

        HBox helperBox = new HBox(optionsButton);
        helperBox.setAlignment(Pos.CENTER_RIGHT);

        // Metemos el boton y el label dentro de la caja vertical junto con la fecha
        VBox rightVbox = new VBox(helperBox, dateLabel);
        rightVbox.setAlignment(Pos.BOTTOM_RIGHT);
        // Intento de que el boton quede arriba a la derecha haciendo que la caja
        // auxiliar sea la mitad de la caja total
        helperBox.setPrefHeight(rightVbox.getPrefHeight() / 2);
        HBox.setHgrow(rightVbox, javafx.scene.layout.Priority.ALWAYS);

        return rightVbox;

    }

    private void configureOptionsButton() {
        optionsButton.setStyle(
                " -fx-text-fill: black;" +
                        "-fx-alignment: top-right; -fx-font-weight: bold; " +
                        "-fx-font-size: 1em;  -fx-border-width: 1;");
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
                controller.deleteTask(task.getTitle());
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

    private void openModifyMenu() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Modify Task");
        // Load the FXML file (Trae el archivo FXML)
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("addTaskMenu.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));

        // Esto es necesario para poder acceder a los metodos del controlador
        AddTaskMenuController addTaskMenuController = loader.getController();
        // Esto es para hacer una inyeccion de dependencias (Para que todos esten
        // instacaiados igual)
        addTaskMenuController.setMainController(controller);
        addTaskMenuController.setStage(stage);
        changeAddTaskMenuToModifyTaskMenu(addTaskMenuController);
        stage.show();
    }

    private void changeAddTaskMenuToModifyTaskMenu(AddTaskMenuController addTaskMenuController) {
        changeTitles(addTaskMenuController);
        addTaskMenuController.setIsEditing(true);
        addTaskMenuController.setOldTitleTask(task.getTitle());
        addTaskMenuController.setTitleInput(task.getTitle());
        addTaskMenuController.setDescriptionInput(task.getContent());
        addTaskMenuController.setPriorityInput(task.getPriority());
        addTaskMenuController.setCategoryInput(task.getUserCategory());
        addTaskMenuController.setDeadlineInput(task.getDeadline());
    }

    private void changeTitles(AddTaskMenuController addTaskMenuController) {
        addTaskMenuController.setHeader("Modify Task");
        addTaskMenuController.setTitleLabel("New Title");
        addTaskMenuController.setDescriptionLabel("New Description");
        addTaskMenuController.setPriorityLabel("New Priority");
        addTaskMenuController.setCategoryLabel("New Category");
        addTaskMenuController.setDeadLineLabel("New Deadline");
        addTaskMenuController.setSubmitBTNText("Modify Task!");
    }

    private void applyStylesToLabel(Label label) {
        label.setStyle("-fx-font-weight: bold;");
        label.setStyle("-fx-alignment: center;");
        label.setPrefHeight(USE_COMPUTED_SIZE);
    }

}