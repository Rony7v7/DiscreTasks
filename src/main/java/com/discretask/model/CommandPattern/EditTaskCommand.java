package com.discretask.model.CommandPattern;

import java.util.Calendar;

import com.discretask.interfaces.Command;
import com.discretask.model.DiscretasksSystem;
import com.discretask.model.Priority;
import com.discretask.model.Task;

public class EditTaskCommand implements Command {
    private Task task;
    private String previousTitle;
    private String previousContent;
    private Priority previousPriority;
    private String previousUserCategory;
    private Calendar previousDeadline;

    public EditTaskCommand(Task task, String previousTitle, String previousContent, Priority previousPriority,
            String previousUserCategory, Calendar previousDeadline) {
        this.task = task;
        this.previousTitle = previousTitle;
        this.previousContent = previousContent;
        this.previousPriority = previousPriority;
        this.previousUserCategory = previousUserCategory;
        this.previousDeadline = previousDeadline;
    }

    @Override
    public void execute(DiscretasksSystem discretasksSystem) {
        discretasksSystem.editTask(task.getId(), previousTitle, previousContent, previousPriority, previousUserCategory,
                previousDeadline, true);
    }

    @Override
    public void undo(DiscretasksSystem discretasksSystem) {
        // To undo an edit, we need to reapply the previous values
        discretasksSystem.editTask(task.getId(), previousTitle, previousContent, previousPriority, previousUserCategory,
                previousDeadline, true);
    }
}
