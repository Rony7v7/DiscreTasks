package com.discretask.model.CommandPattern;

import com.discretask.interfaces.Command;
import com.discretask.model.DiscretasksSystem;
import com.discretask.model.Task;

/**
 * DeleteTaskCommand is a concrete command that implements the Command interface.
 * It is used to delete a task from the system.
 */
public class DeleteTaskCommand implements Command {

    /**
     * The task to be deleted from the system.
     */
    private Task task;

    /**
     * Constructor for DeleteTaskCommand.
     * @param task The task to be deleted from the system.
     */
    public DeleteTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to delete the task from the system.
     * @param discretasksSystem The system from which the task will be deleted.
     */
    @Override
    public void execute(DiscretasksSystem discretasksSystem) {
        discretasksSystem.deleteTask(task.getId(), true);
    }

    /**
     * Undoes the command to add the task to the system.
     * @param discretasksSystem The system to which the task will be added.
     */
    @Override
    public void undo(DiscretasksSystem discretasksSystem) {
        discretasksSystem.addTaskUndo(task);
    }
}
