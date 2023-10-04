package com.discretask.ui;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import com.discretask.exceptions.invalidDateException;
import com.discretask.model.Priority;
import com.discretask.model.Task;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ModifyTaskMenuController {

    private Task task;
    private MainController controller;
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
    private Button submitBTN;

    @FXML
    private TextField titleInput;

    public void fillFields() {
        categoryInput.setText(this.task.getUserCategory());
        contentInput.setText(this.task.getContent());
        deadLineInput
                .setValue(this.task.getDeadline().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
        titleInput.setText(this.task.getTitle());
        priorityRadio.selectToggle(priorityRadio.getToggles().get(this.task.getPriority().ordinal()));
        submitBTN.setText("Modify task");

        for (int i = 0; i < 3; i++)
            priorityRadio.getToggles().get(i).setUserData(Priority.values()[i]);
    }

    @FXML
    void submitTask(ActionEvent event) {
        boolean hasError = false;
        Calendar deadLine = Calendar.getInstance();
        Priority priority = null;
        try {

            LocalDate datePicked = deadLineInput.getValue();
            Date date = java.sql.Date.valueOf(datePicked);
            deadLine.setTime(date);
            priority = Priority.valueOf(priorityRadio.getSelectedToggle().getUserData().toString());

            if (titleInput.getText().equals("") || categoryInput.getText().equals("")) {
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

        if (!hasError) {
            controller.modifyTask(this.task.getTitle(), titleInput.getText(), contentInput.getText(), priority,
                    categoryInput.getText(), deadLine);
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

    public void setController(MainController controller) {
        this.controller = controller;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
