package com.discretask.ui;

import java.text.SimpleDateFormat;

import com.discretask.model.Priority;
import com.discretask.model.Task;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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

    private Button optionsButton = new Button(". . .");
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public TaskItem(Task task) {

        // Add VBox containers to HBox
        getChildren().addAll(buildLeftVbox(task), buildMiddleVbox(task), buildRightVbox(task));

        // Configure spacing between VBox containers
        setSpacing(10); // Espacio entre los VBox

        // Con esto le ponemos un borde al TaskItem
        setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-padding: 10px;");
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
        if (priority == Priority.PRIORITY) {
            priorityCircle.setFill(Color.RED);
        } else if (priority == Priority.NEUTRAL) {
            priorityCircle.setFill(Color.ORANGE);
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
                "-fx-background-color: transparent; -fx-text-fill: black; -fx-alignment: top-right; -fx-font-weight: bold; -fx-font-size: 1em;  -fx-border-width: 1;");
    }

    private void applyStylesToLabel(Label label) {
        label.setStyle("-fx-font-weight: bold;");
        label.setStyle("-fx-alignment: center;");
        label.setPrefHeight(USE_COMPUTED_SIZE);
    }
}