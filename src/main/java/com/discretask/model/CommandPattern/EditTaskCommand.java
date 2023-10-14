package com.discretask.model.CommandPattern;

import java.util.Calendar;

import com.discretask.interfaces.Command;
import com.discretask.model.DiscretasksSystem;
import com.discretask.model.Priority;
import com.discretask.model.Task;

/**
 * EditTaskCommand is a concrete command that implements the Command interface.
 * It is used to edit a task in the system.
 */
public class EditTaskCommand implements Command {

    /**
     * The task to be edited in the system.
     */
    private Task task;

    /**
     * The previous title of the task.
     */
    private String previousTitle;

    /**
     * The previous content of the task.
     */
    private String previousContent;

    /**
     * The previous priority of the task.
     */
    private Priority previousPriority;

    /**
     * The previous user category of the task.
     */
    private String previousUserCategory;

    /**
     * The previous deadline of the task.
     */
    private Calendar previousDeadline;

    /**
     * Constructor for EditTaskCommand.
     * @param task The task to be edited in the system.
     * @param previousTitle The previous title of the task.
     * @param previousContent The previous content of the task.
     * @param previousPriority The previous priority of the task.
     * @param previousUserCategory The previous user category of the task.
     * @param previousDeadline The previous deadline of the task.
     */
    public EditTaskCommand(Task task, String previousTitle, String previousContent, Priority previousPriority,
            String previousUserCategory, Calendar previousDeadline) {
        this.task = task;
        this.previousTitle = previousTitle;
        this.previousContent = previousContent;
        this.previousPriority = previousPriority;
        this.previousUserCategory = previousUserCategory;
        this.previousDeadline = previousDeadline;
    }

    /**
     * Executes the command to edit the task in the system.
     * @param discretasksSystem The system in which the task will be edited.
     */
    @Override
    public void execute(DiscretasksSystem discretasksSystem) {
        discretasksSystem.editTask(task.getId(), previousTitle, previousContent, previousPriority, previousUserCategory,
                previousDeadline, true);
    }

    /**
     * Undoes the command to edit the task in the system.
     * @param discretasksSystem The system in which the task will be edited.
     */
    @Override
    public void undo(DiscretasksSystem discretasksSystem) {
        // To undo an edit, we need to reapply the previous values
        discretasksSystem.editTask(task.getId(), previousTitle, previousContent, previousPriority, previousUserCategory,
                previousDeadline, true);
    }
}
