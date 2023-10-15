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

/**
 * The AddTaskMenuController class is responsible for initializing the JavaFX application.
 */
public class AddTaskMenuController implements Initializable {

    /**
     * The discretasksSystem variable is an instance of the DiscretasksSystem class.
     */
    private DiscretasksSystem discretasksSystem;

    /**
     * The mainController variable is an instance of the MainController class. 
     */
    private MainController mainController;

    /**
     * The stage variable is an instance of the Stage class.
     */
    private Stage stage;

    /**
     * The taskID variable is a String that represents the ID of a task.
     */
    private String taskID = "";

    /**
     * The isEditing variable is a boolean that represents if a task is being edited.
     */
    private boolean isEditing = false;

    /**
     * The categoryInput variable is a JavaFX TextField object that is used to input a category.
     */
    @FXML
    private TextField categoryInput;

    /**
     * The priorityLabel variable is a JavaFX Label object that is used to display the priority label.
     */
    @FXML
    private Label priorityLabel;

    /**
     * The categoryLabel variable is a JavaFX Label object that is used to display the category label.
     */
    @FXML
    private Label categoryLabel;

    /**
     * The deadLineInput variable is a JavaFX DatePicker object that is used to input a deadline.
     */
    @FXML
    private DatePicker deadLineInput;

    /**
     * The deadLineLabel variable is a JavaFX Label object that is used to display the deadline label.
     */
    @FXML
    private Label deadLineLabel;

    /**
     * The descriptionInput variable is a JavaFX TextArea object that is used to input a description.
     */
    @FXML
    private TextArea descriptionInput;

    /**
     * The descriptionLabel variable is a JavaFX Label object that is used to display the description label.
     */
    @FXML
    private Label descriptionLabel;

    /**
     * The header variable is a JavaFX Label object that is used to display the header.
     */
    @FXML
    private Label header;

    /**
     * The priorityChoiceBox variable is a JavaFX ChoiceBox object that is used to input a priority.
     */
    @FXML
    private ChoiceBox<String> priorityChoiceBox;

    /**
     * The submitBTN variable is a JavaFX Button object that is used to submit a task.
     */
    @FXML
    private Button submitBTN;

    /**
     * The titleInput variable is a JavaFX TextField object that is used to input a title.
     */
    @FXML
    private TextField titleInput;

    /**
     * The titleLabel variable is a JavaFX Label object that is used to display the title label.
     */
    @FXML
    private Label titleLabel;

    /**
     * The initialize method is used to initialize the JavaFX application.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Priority[] priorities = Priority.values();
        priorityChoiceBox.getItems().addAll(priorities[0].toString(), priorities[1].toString(),
                priorities[2].toString(), priorities[3].toString(), priorities[4].toString());
    }


    /**
     * The setStage method sets the stage of the JavaFX application.
     * @param stage The parameter "stage" is an instance of the Stage class.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * The setMainController method sets the main controller of the JavaFX application.
     * @param mainController The parameter "mainController" is an instance of the MainController class.
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        this.discretasksSystem = mainController.getController();
    }

    /**
     * The submitTask method is used to submit a task. 
     * It checks if the input is valid and then adds the task to the hash table.
     */
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

    /**
     * The showError method is used to display an error message.
     * @param title The parameter "title" is a String that represents the title of the error message.
     * @param headerText The parameter "headerText" is a String that represents the header text of the error message.
     * @param contentText The parameter "contentText" is a String that represents the content text of the error message.
     */
    private void showError(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    /**
     * The setTaskID method sets the task ID of a task.
     * @param taskID The parameter "taskID" is a String that represents the ID of a task.
     */
    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }


    /**
     * The setHeader method sets the header of the JavaFX application.
     * @param header The parameter "header" is a String that represents the header of the JavaFX application.
     */
    public void setHeader(String header) {
        this.header.setText(header);
    }

    /**
     * The setTitleLabel method sets the title label of the JavaFX application.
     * @param titleLabel The parameter "titleLabel" is a String that represents the title label of the JavaFX application.
     */
    public void setTitleLabel(String titleLabel) {
        this.titleLabel.setText(titleLabel);
    }

    /**
     * The setDescriptionLabel method sets the description label of the JavaFX application.
     * @param descriptionLabel The parameter "descriptionLabel" is a String that represents the description label of the JavaFX application.
     */
    public void setDescriptionLabel(String descriptionLabel) {
        this.descriptionLabel.setText(descriptionLabel);
    }

    /**
     * The setPriorityLabel method sets the priority label of the JavaFX application.
     * @param priorityLabel The parameter "priorityLabel" is a String that represents the priority label of the JavaFX application.
     */
    public void setPriorityLabel(String priorityLabel) {
        this.priorityLabel.setText(priorityLabel);
    }

    /**
     * The setDeadLineLabel method sets the deadline label of the JavaFX application.
     * @param deadLineLabel The parameter "deadLineLabel" is a String that represents the deadline label of the JavaFX application.
     */
    public void setDeadLineLabel(String deadLineLabel) {
        this.deadLineLabel.setText(deadLineLabel);
    }

    /**
     * The setCategoryLabel method sets the category label of the JavaFX application.
     * @param categoryLabel The parameter "categoryLabel" is a String that represents the category label of the JavaFX application.
     */
    public void setCategoryLabel(String categoryLabel) {
        this.categoryLabel.setText(categoryLabel);
    }

    /**
     * The setTitleInput method sets the title input of the JavaFX application.
     * @param text The parameter "text" is a String that represents the title input of the JavaFX application.
     */
    public void setTitleInput(String text) {
        this.titleInput.setText(text);
    }

    /**
     * The setDescriptionInput method sets the description input of the JavaFX application.
     * @param text The parameter "text" is a String that represents the description input of the JavaFX application.
     */
    public void setDescriptionInput(String text) {
        this.descriptionInput.setText(text);
    }

    /**
     * The setPriorityInput method sets the priority input of the JavaFX application.
     * @param priority The parameter "priority" is an instance of the Priority class.
     */
    public void setPriorityInput(Priority priority) {
        this.priorityChoiceBox.setValue(priority.toString());
    }

    /**
     * The setCategoryInput method sets the category input of the JavaFX application.
     * @param text The parameter "text" is a String that represents the category input of the JavaFX application.
     */
    public void setCategoryInput(String text) {
        this.categoryInput.setText(text);
    }

    /**
     * The setSubmitBTNText method sets the submit button text of the JavaFX application.
     * @param text The parameter "text" is a String that represents the submit button text of the JavaFX application.
     */
    public void setSubmitBTNText(String text) {
        this.submitBTN.setText(text);
    }

    /**
     * The setIsEditing method sets the isEditing variable of the JavaFX application.
     * @param isEditing The parameter "isEditing" is a boolean that represents if a task is being edited.
     */
    public void setIsEditing(boolean isEditing) {
        this.isEditing = isEditing;
    }

    /**
     * The setDeadlineInput method sets the deadline input of the JavaFX application.
     * @param deadline The parameter "deadline" is an instance of the Calendar class.
     */
    public void setDeadlineInput(Calendar deadline) {
        Instant instant = deadline.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zdt.toLocalDate();
        this.deadLineInput.setValue(localDate);
    }

}