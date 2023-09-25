package com.discretask.ui;

import java.util.Calendar;

import com.discretask.model.DiscretasksSystem;
import com.discretask.model.Priority;

import javafx.fxml.FXML;
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

    public void setDiscretasksSystem(DiscretasksSystem discretasksSystem) {
        this.discretasksSystem = discretasksSystem;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        for (int i = 0; i < 3; i++)
            priorityRadio.getToggles().get(i).setUserData(Priority.values()[i]);
    }

    public void submitTask() {
        String title = titleInput.getText();
        String content = contentInput.getText();

        Priority priority = Priority.valueOf(priorityRadio.getSelectedToggle().getUserData().toString());
        String userCategory = categoryInput.getText();
        Calendar deadLine = Calendar.getInstance();
        deadLine.set(deadLineInput.getValue().getYear(), deadLineInput.getValue().getMonthValue(),
                deadLineInput.getValue().getDayOfMonth());
        System.out.println(deadLine);

        if (priorityRadio.getSelectedToggle() != null) {

            discretasksSystem.addTask(title, content, priority, userCategory, deadLine);
            System.out.println("I added the new task");
        }

        if (stage != null) {
            stage.close();
        }
    }
}