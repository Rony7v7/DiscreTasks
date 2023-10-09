package com.discretask.ui;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import com.discretask.exceptions.invalidDateException;
import com.discretask.model.DiscretasksSystem;
import com.discretask.model.Priority;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddTaskMenuController implements Initializable {

    private DiscretasksSystem discretasksSystem;
    private MainController mainController;
    private Stage stage;
    private String taskID = "";
    private boolean isEditing = false;

    @FXML
    private TextField categoryInput;

    @FXML
    private Label priorityLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private DatePicker deadLineInput;

    @FXML
    private Label deadLineLabel;

    @FXML
    private TextArea descriptionInput;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label header;

    @FXML
    private ChoiceBox<String> priorityChoiceBox;

    @FXML
    private Button submitBTN;

    @FXML
    private TextField titleInput;

    @FXML
    private Label titleLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Priority[] priorities = Priority.values();
        priorityChoiceBox.getItems().addAll(priorities[0].toString(), priorities[1].toString(),
                priorities[2].toString(), priorities[3].toString(), priorities[4].toString());
    }

    // Son metodos para las dependencias usados en el MainController

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        this.discretasksSystem = mainController.getController();
    }

    // Trae las cosas del UI y si hay un error lo muestra
    public void submitTask() {
        String title = "";
        String description = "";
        Priority priority = null;
        String userCategory = "";
        Calendar deadLine = Calendar.getInstance();

        boolean hasError = false;
        try {
            LocalDate datePicked = deadLineInput.getValue();
            Date date = java.sql.Date.valueOf(datePicked);
            deadLine.setTime(date);
            title = titleInput.getText();
            description = descriptionInput.getText();
            priority = Priority.valueOf(priorityChoiceBox.getValue());
            userCategory = categoryInput.getText();

            if (title.equals("") || userCategory.equals("")) {
                throw new NullPointerException();
            }

            if (datePicked.isBefore(LocalDate.now())) {
                throw new invalidDateException("The date is before today");
            }

        } catch (NullPointerException e) {
            hasError = true;
            showError("Error", "Error", "Please fill all the fields");
        } catch (invalidDateException e) {
            hasError = true;
            showError("Error", "Error", e.getMessage());
        } catch (Exception e) {
            hasError = true;
            showError("Error", "Error", "An unexpected error has ocurred");
        }

        if (!hasError && !isEditing) {
            discretasksSystem.addTask(title, description, priority, userCategory, deadLine);

            mainController.updateTaskList();

            if (stage != null) {
                stage.close();
            }

        } else if (!hasError && isEditing) {
            discretasksSystem.editTask(taskID, title, description, priority, userCategory, deadLine, false);
            mainController.updateTaskList();
            if (stage != null) {
                stage.close();
            }
        }

    }

    // Metodo auxiliar para mostrar errores con mensajes personalizados
    private void showError(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public void setHeader(String header) {
        this.header.setText(header);
    }

    public void setTitleLabel(String titleLabel) {
        this.titleLabel.setText(titleLabel);
    }

    public void setDescriptionLabel(String descriptionLabel) {
        this.descriptionLabel.setText(descriptionLabel);
    }

    public void setPriorityLabel(String priorityLabel) {
        this.priorityLabel.setText(priorityLabel);
    }

    public void setDeadLineLabel(String deadLineLabel) {
        this.deadLineLabel.setText(deadLineLabel);
    }

    public void setCategoryLabel(String categoryLabel) {
        this.categoryLabel.setText(categoryLabel);
    }

    public void setTitleInput(String text) {
        this.titleInput.setText(text);
    }

    public void setDescriptionInput(String text) {
        this.descriptionInput.setText(text);
    }

    public void setPriorityInput(Priority priority) {
        this.priorityChoiceBox.setValue(priority.toString());
    }

    public void setCategoryInput(String text) {
        this.categoryInput.setText(text);
    }

    public void setSubmitBTNText(String text) {
        this.submitBTN.setText(text);
    }

    public void setIsEditing(boolean isEditing) {
        this.isEditing = isEditing;
    }

    public void setDeadlineInput(Calendar deadline) {
        Instant instant = deadline.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zdt.toLocalDate();
        this.deadLineInput.setValue(localDate);
    }

}