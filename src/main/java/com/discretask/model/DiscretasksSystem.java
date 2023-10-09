package com.discretask.model;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;

import com.discretask.interfaces.Command;
import com.discretask.model.CommandPattern.AddTaskCommand;
import com.discretask.model.CommandPattern.DeleteTaskCommand;
import com.discretask.model.CommandPattern.EditTaskCommand;
import com.discretask.structures.HashTable;
import com.discretask.structures.Queue;
import com.discretask.structures.Stack;
import com.discretask.structures.Heap;

public class DiscretasksSystem {

    private HashTable<String, Task> tasks;
    private Queue<Task> nonPriorityTasks;
    private Heap<Task> tasksByDeadLine;
    private Heap<Task> priorityTasks;
    private Stack<Command> operationStack;

    public DiscretasksSystem() {
        tasks = new HashTable<String, Task>();
        nonPriorityTasks = new Queue<Task>();
        operationStack = new Stack<Command>();
        tasksByDeadLine = new Heap<Task>(new ComparatorDeadLine());
        priorityTasks = new Heap<Task>(new ComparatorPriority());
    }

    // add task
    public Task addTask(String title, String content, Priority priority, String userCategory, Calendar deadline) {
        Task task = new Task(title, content, priority, userCategory, deadline, title + Calendar.getInstance());
        tasks.put(task.getId(), task);

        tasksByDeadLine.add(task);
        assignTaskToStructure(task);
        operationStack.push(new AddTaskCommand(task));

        return task;

    }

    public Task addTaskUndo(Task task) {
        tasks.put(task.getId(), task);

        tasksByDeadLine.add(task);
        assignTaskToStructure(task);
        return task;

    }

    public void editTask(String id, String title, String content, Priority priority, String userCategory,
            Calendar deadline, boolean undo) {

        Task task = tasks.remove(id); // Removing task from the hash table because the id is the key

        String previousTitle = task.getTitle();
        String previousContent = task.getContent();
        Priority previousPriority = task.getPriority();
        String previousUserCategory = task.getUserCategory();
        Calendar previousDeadline = task.getDeadline();

        if (task.getTitle() != title) {
            task.setTitle(title);
            task.setId(title + Calendar.getInstance());
        }

        if (task.getPriority() != priority) {
            removeTaskFromStructure(task);
            task.setPriority(priority);
            assignTaskToStructure(task);
        }

        if (task.getContent() != content)
            task.setContent(content);
        if (task.getUserCategory() != userCategory)
            task.setUserCategory(userCategory);
        if (task.getDeadline() != deadline)
            task.setDeadline(deadline);

        tasks.put(task.getId(), task); // Adding the task again to the hash table with the new id
        if (!undo) {
            operationStack.push(new EditTaskCommand(task, previousTitle, previousContent, previousPriority,
                    previousUserCategory, previousDeadline));
        }

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

    public void undo() {
        if (!operationStack.isEmpty()) {
            Command command = operationStack.pop();
            command.undo(this);
        }
    }

    public void deleteTask(String key, boolean undo) {
        Task task = tasks.get(key);
        if (!undo) {
            operationStack.push(new DeleteTaskCommand(task));
        }

        priorityTasks.remove(tasks.get(key));
        nonPriorityTasks.remove(tasks.get(key));
        tasksByDeadLine.remove(tasks.get(key));

        tasks.remove(key);

    }

    public Queue<Task> getNonPriorityTasks() {
        return nonPriorityTasks;
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

    public Task[] getSortedHeap(String type) {

        Heap<Task> temp;
        Task[] taskArray = null;

        if (type.equals("DEADLINE")) {

            temp = getTasksByDeadLine();
            taskArray = new Task[temp.size()];
            temp.getHeap(taskArray);
            mergeSort(taskArray, new ComparatorDeadLine());

        } else if (type.equals("PRIORITY")) {

            temp = getPriorityTasks();
            taskArray = new Task[temp.size()];
            temp.getHeap(taskArray);
            mergeSort(taskArray, new ComparatorPriority());

        }

        return taskArray;

    }

    public void mergeSort(Task[] array, Comparator<Task> comparator) {

        if (array.length > 1) {

            int mid = array.length / 2;

            Task[] left = Arrays.copyOfRange(array, 0, mid);
            Task[] right = Arrays.copyOfRange(array, mid, array.length);

            mergeSort(left, comparator);
            mergeSort(right, comparator);

            merge(array, left, right, comparator);

        }

    }

    public void merge(Task[] array, Task[] left, Task[] right, Comparator<Task> comparator) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.length && j < right.length) {

            if (comparator.compare(left[i], right[j]) < 0) {
                array[k] = left[i];
                i++;
            } else {
                array[k] = right[j];
                j++;
            }

            k++;

        }

        while (i < left.length) {
            array[k] = left[i];
            i++;
            k++;
        }

        while (j < right.length) {
            array[k] = right[j];
            j++;
            k++;
        }

    }

    public void initSytem() {
        Calendar today = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();
        Calendar inTwoDays = Calendar.getInstance();
        Calendar nextWeek = Calendar.getInstance();
        Calendar nextMonth = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);
        inTwoDays.add(Calendar.DAY_OF_MONTH, 12);
        nextWeek.add(Calendar.DAY_OF_MONTH, 7);
        nextMonth.add(Calendar.MONTH, 1);

        addTask("Tarea1", "Descripcion 1", Priority.HIGH_PRIORITY, "Test 1", today);
        addTask("Tarea2", "Descripcion 2", Priority.LOW_PRIORITY, "Test 2", tomorrow);
        addTask("Tarea3", "Descripcion 3", Priority.MEDIUM_PRIORITY, "Test 3", inTwoDays);
        addTask("Tarea4", "Descripcion 4", Priority.NON_PRIORITY, "Test 4", nextWeek);
        addTask("Tarea5", "Descripcion 5", Priority.OPTIONAL, "Test 5", nextMonth);
    }
}
