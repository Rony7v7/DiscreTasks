package com.discretask.model.CommandPattern;

import java.util.Calendar;

import com.discretask.interfaces.Command;
import com.discretask.model.DiscretasksSystem;
import com.discretask.model.Task;

public class AddTaskCommand implements Command {

    private Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(DiscretasksSystem discretasksSystem) {
        discretasksSystem.addTask(task.getTitle(), task.getContent(), task.getPriority(), task.getUserCategory(),
                task.getDeadline(), true);
    }

    @Override
    public void undo(DiscretasksSystem discretasksSystem) {
        discretasksSystem.deleteTask(task.getId(), true);
    }
}
