package com.discretask.ui;

import java.util.Calendar;

import com.discretask.exceptions.invalidDateException;
import com.discretask.model.DiscretasksSystem;
import com.discretask.model.Priority;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class addTaskMenuController {

    private DiscretasksSystem discretasksSystem;
    private Stage stage;

    @FXML
    private TextField categoryInput;

    @FXML
    private TextArea contentInput;

    @FXML
    private DatePicker deadLineInput;

    @FXML
    private ToggleGroup priorityRadio;

    @FXML
    private TextField titleInput;

    @FXML
    private Button submitButton;

    // Son metodos para las dependencias usados en el MainController
    public void setDiscretasksSystem(DiscretasksSystem discretasksSystem) {
        this.discretasksSystem = discretasksSystem;
    }
    // Son metodos para las dependencias usados en el MainController

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        // Esto es para darle las enumeracion a cada boton
        for (int i = 0; i < 3; i++)
            priorityRadio.getToggles().get(i).setUserData(Priority.values()[i]);
    }

    // Trae las cosas del UI y si hay un error lo muestra
    public void submitTask() {
        String title = "";
        String content = "";
        Priority priority = null;
        String userCategory = "";
        Calendar deadLine = Calendar.getInstance();
        boolean hasError = false;
        try {
            title = titleInput.getText();
            content = contentInput.getText();
            priority = Priority.valueOf(priorityRadio.getSelectedToggle().getUserData().toString());
            userCategory = categoryInput.getText();

            deadLine.set(deadLineInput.getValue().getYear(), deadLineInput.getValue().getMonthValue(),
                    deadLineInput.getValue().getDayOfMonth());

            if (deadLine.before(Calendar.getInstance())
                    || deadLine.get(Calendar.DAY_OF_MONTH) < Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
                throw new invalidDateException("The date is before today");
            }

        } catch (NullPointerException e) {
            hasError = true;
            showError("Error", "Error", "Please fill all the fields");
        } catch (invalidDateException e) {
            hasError = true;
            showError("Error", "Error", e.getMessage());
        }

        if (!hasError) {
            discretasksSystem.addTask(title, content, priority, userCategory, deadLine);

            if (stage != null) {
                stage.close();
            }
        }

    }

    private void showError(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
