package com.discretask.model.CommandPattern;

import com.discretask.interfaces.Command;
import com.discretask.model.DiscretasksSystem;
import com.discretask.model.Task;

/**
 * AddTaskCommand is a concrete command that implements the Command interface.
 * It is used to add a task to the system.
 */
public class AddTaskCommand implements Command {

    /**
     * The task to be added to the system.
     */
    private Task task;

    /**
     * Constructor for AddTaskCommand.
     * @param task The task to be added to the system.
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add the task to the system.
     * @param discretasksSystem The system to which the task will be added.
     */
    @Override
    public void execute(DiscretasksSystem discretasksSystem) {
        discretasksSystem.addTask(task.getTitle(), task.getContent(), task.getPriority(), task.getUserCategory(),
                task.getDeadline());
    }

    /**
     * Undoes the command to delete the task from the system.
     * @param discretasksSystem The system from which the task will be deleted.
     */
    @Override
    public void undo(DiscretasksSystem discretasksSystem) {
        discretasksSystem.deleteTask(task.getId(), true);
    }
}
