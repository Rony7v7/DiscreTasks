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
        tasksByDeadLine = new Heap<Task>(new ComparatorDeadLine());
        priorityTasks = new Heap<Task>(new ComparatorPriority());
    }

    // add task
    public void addTask(String title, String content, Priority priority, String userCategory, Calendar deadline) {
        Task task = new Task(title, content, priority, userCategory, deadline, title + Calendar.getInstance());
        tasks.put(task.getId(), task);
        tasksByDeadLine.add(task);

        assignTaskToStructure(task);

        autoSave();
    }

    public void editTask(String id, String title, String content, Priority priority, String userCategory,
            Calendar deadline) {

        Task task = tasks.remove(id); // Removing task from the hash table because the id is the key

        if (task.getTitle() != title) {
            task.setTitle(title);
            task.setId(title + Calendar.getInstance());
        }
        
        if (task.getContent() != content) task.setContent(content);
        if (task.getPriority() != priority) task.setPriority(priority);
        if (task.getUserCategory() != userCategory) task.setUserCategory(userCategory);
        if (task.getDeadline() != deadline) task.setDeadline(deadline);

        tasks.put(task.getId(), task); // Adding the task again to the hash table with the new id

        autoSave();
    }

    public void assignTaskToStructure(Task task) {
        Priority priority = task.getPriority();

        if (priority == Priority.NON_PRIORITY)
            nonPriorityTasks.enqueue(task);
        else if (priority == Priority.LOW_PRIORITY || priority == Priority.MEDIUM_PRIORITY
                || priority == Priority.HIGH_PRIORITY)
            priorityTasks.add(task);
    }

    public void removeTaskFromStructure(Task task) {
        Priority priority = task.getPriority();

        if (priority == Priority.NON_PRIORITY)
            nonPriorityTasks.remove(task);
        else if (priority == Priority.LOW_PRIORITY || priority == Priority.MEDIUM_PRIORITY
                || priority == Priority.HIGH_PRIORITY)
            priorityTasks.remove(task);
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
        priorityTasks.remove(tasks.get(key));
        nonPriorityTasks.remove(tasks.get(key));
        tasksByDeadLine.remove(tasks.get(key));

        tasks.remove(key);
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
