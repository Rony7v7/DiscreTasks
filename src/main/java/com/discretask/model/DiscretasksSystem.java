package com.discretask.model;

import java.util.Calendar;
import java.util.Comparator;

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
        tasksByDeadLine = new Heap<Task>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getDeadline().compareTo(o2.getDeadline());
            }
        });
        priorityTasks = new Heap<Task>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getPriority().compareTo(o2.getPriority());
            }
        });
    }

    // add task
    public void addTask(String title, String content, Priority priority, String userCategory, Calendar deadline) {
        Task task = new Task(title, content, priority, userCategory, deadline);
        tasks.put(title, task);
        tasksByDeadLine.add(task);

        assignTaskToStructure(task);

        autoSave();
    }

    public void editTask(String oldTitle, String title, String content, Priority priority, String userCategory,
            Calendar deadline) {
        Task task = tasks.get(oldTitle);

        // delete task from old structure
        if (task.getPriority() == Priority.PRIORITY)
            priorityTasks.remove(task);
        else if (task.getPriority() == Priority.NON_PRIORITY)
            nonPriorityTasks.remove(task);

        task.setTitle(title);
        task.setContent(content);
        task.setPriority(priority);
        task.setUserCategory(userCategory);
        task.setDeadline(deadline);

        assignTaskToStructure(task);

        autoSave();
    }

    public void assignTaskToStructure(Task task) {
        Priority priority = task.getPriority();

        if (priority == Priority.NON_PRIORITY)
            nonPriorityTasks.enqueue(task);
        else if (priority == Priority.PRIORITY)
            priorityTasks.add(task);
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

    public void deleteTask(String key) {
        tasks.remove(key);
        priorityTasks.remove(tasks.get(key));
        nonPriorityTasks.remove(tasks.get(key));
        tasksByDeadLine.remove(tasks.get(key));

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
}
