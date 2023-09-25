package com.discretask.model;

import java.util.Calendar;

import com.discretask.structures.HashTable;
import com.discretask.structures.Queue;
import com.discretask.structures.Stack;
import com.discretask.structures.Heap;

public class DiscretasksSystem {

    private HashTable<String, Task> tasks;
    private Queue<Task> nonPriorityTasks;
    private Heap<Task> tasksByDeadLine;
    private Heap<Task> priorityTasks;
    private Stack<DiscretasksSystem> operationStack;

    public DiscretasksSystem() {
        tasks = new HashTable<String, Task>();
        nonPriorityTasks = new Queue<Task>();
        operationStack = new Stack<DiscretasksSystem>();
        tasksByDeadLine = new Heap<Task>();
    }

    // add task
    public void addTask(String title, String content, Priority priority, String userCategory, Calendar deadline) {
        Task task = new Task(title, content, priority, userCategory, deadline);
        tasks.put(title, task);
        tasksByDeadLine.offer(task);
        if (priority == Priority.NON_PRIORITY) {
            nonPriorityTasks.enqueue(task);
        } else if (priority == Priority.PRIORITY) {
            priorityTasks.offer(task);
        }

        autoSave();
    }

    public Queue<Task> getNonPriorityTasks() {
        return nonPriorityTasks;
    }

    public Stack<DiscretasksSystem> getOperationStack() {
        return operationStack;
    }

    public Heap<Task> getPriorityTasks() {
        return priorityTasks;
    }

    public HashTable<String, Task> getTasks() {
        return tasks;
    }

    public Heap<Task> getTasksByDeadLine() {
        return tasksByDeadLine;
    }

    // edit task
    public void editTask(String title, String content, Priority priority, String userCategory, Calendar deadline) {
        Task task = tasks.get(title);
        task.setContent(content);
        task.setPriority(priority);
        task.setUserCategory(userCategory);
        task.setDeadline(deadline);

        autoSave();
    }

    // No se si es mejor llamarlo desde el main (depende del javafx) porque desde el
    // controller tocaria
    // llamarlo en cada metodo de modificación, incumpliendo con el principio de
    // responsabilidad única.
    public void autoSave() {
        operationStack.push(this);
    }

    public void undo() {
        DiscretasksSystem previousState = operationStack.pop();
        this.tasks = previousState.tasks;
        this.nonPriorityTasks = previousState.nonPriorityTasks;
    }

}
