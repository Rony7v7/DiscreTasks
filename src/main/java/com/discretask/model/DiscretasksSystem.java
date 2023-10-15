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

/**
 * DiscretasksSystem is the class that represents the system, the controller 
 * of the application. It contains all the tasks and the methods to add, edit
 * and delete them.
 */
public class DiscretasksSystem {

    /**
     * The hash table that contains all the tasks.
     */
    private HashTable<String, Task> tasks;

    /**
     * The queue that contains all the non-priority tasks.
     */
    private Queue<Task> nonPriorityTasks;

    /**
     * The heap that contains all the tasks sorted by deadline.
     */
    private Heap<Task> tasksByDeadLine;

    /**
     * The heap that contains all the priority tasks sorted by priority.
     */
    private Heap<Task> priorityTasks;

    /**
     * The stack that contains all the operations that have been done.
     */
    private Stack<Command> operationStack;

    /**
     * Constructor for DiscretasksSystem.
     */
    public DiscretasksSystem() {
        tasks = new HashTable<String, Task>();
        nonPriorityTasks = new Queue<Task>();
        operationStack = new Stack<Command>();
        tasksByDeadLine = new Heap<Task>(new ComparatorDeadLine());
        priorityTasks = new Heap<Task>(new ComparatorPriority());
    }

    /**
     * The addTask function creates a new Task object with the given parameters, adds it to a
     * collection of tasks, and returns the created task.
     * 
     * @param title        The title of the task.
     * @param content      The content parameter is a string that represents the content or description of
     *                     the task.
     * @param priority     The priority of the task, which can be High, Medium, Low, Optional or Non-Priority.
     * @param userCategory The userCategory parameter is a String that represents the category or group
     *                     to which the task belongs. 
     * @param deadline     The deadline parameter is a Calendar object that represents the deadline for the
     *                     task.
     * @return             The method is returning a Task object.
     */
    public Task addTask(String title, String content, Priority priority, String userCategory, Calendar deadline) {
        Task task = new Task(title, content, priority, userCategory, deadline, title + Calendar.getInstance());
        tasks.put(task.getId(), task);

        tasksByDeadLine.add(task);
        assignTaskToStructure(task);
        operationStack.push(new AddTaskCommand(task));

        return task;

    }

    /**
     * The function adds a task to a collection and assigns it to a structure.
     * This function is used to undo the operation of adding a task.
     * 
     * @param task The parameter "task" is an object of type Task.
     * @return The method is returning the added task.
     */
    public Task addTaskUndo(Task task) {
        tasks.put(task.getId(), task);

        tasksByDeadLine.add(task);
        assignTaskToStructure(task);
        return task;

    }

    /**
     * The editTask function edits a task with the given parameters.
     * 
     * @param id            The id of the task.
     * @param title         The title of the task.
     * @param content       The content parameter is a string that represents the content or description of
     *                      the task.
     * @param priority      The priority of the task, which can be High, Medium, Low, Optional or Non-Priority.
     * @param userCategory  The userCategory parameter is a String that represents the category or group
     *                      to which the task belongs. 
     * @param deadline      The deadline parameter is a Calendar object that represents the deadline for the
     *                      task.
     * @param undo          The undo parameter is a boolean that represents if the operation is an undo or not.
     */
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

    /**
     * The function assigns a task to a data structure based on its priority level.
     * 
     * @param task The "task" parameter is an object of the Task class.
     */
    public void assignTaskToStructure(Task task) {
        Priority priority = task.getPriority();

        if (priority == Priority.NON_PRIORITY)
            nonPriorityTasks.enqueue(task);
        else if (priority == Priority.LOW_PRIORITY || priority == Priority.MEDIUM_PRIORITY
                || priority == Priority.HIGH_PRIORITY)
            priorityTasks.add(task);
    }

    /**
     * The function removes a task from a structure based on its priority.
     * 
     * @param task The "task" parameter is an object of the Task class.
     */
    public void removeTaskFromStructure(Task task) {
        Priority priority = task.getPriority();

        if (priority == Priority.NON_PRIORITY)
            nonPriorityTasks.remove(task);
        else if (priority == Priority.LOW_PRIORITY || priority == Priority.MEDIUM_PRIORITY
                || priority == Priority.HIGH_PRIORITY)
            priorityTasks.remove(task);
    }

    /**
     * The function pops a command from the operation stack and calls its `undo` method.
     * This function is used to undo the last operation.
     */
    public void undo() {
        if (!operationStack.isEmpty()) {
            Command command = operationStack.pop();
            command.undo(this);
        }
    }

    /**
     * The deleteTask function removes a task from the collections and the hashtable, and 
     * adds a command to the operationStack for undo functionality if the method is not being
     * called from the undo function.
     * 
     * @param key The key is a unique identifier for the task that needs to be deleted. It is used to
     *            retrieve the task from the hashtable.
     * @param undo The `undo` parameter is a boolean value that indicates whether the deletion of the task
     *             should be undone or not. If `undo` is `true`, it means that the deletion is being undone and the
     *             task should be restored. If `undo` is `false`, it means that the deletion is
     *             not being undone and the task should be deleted.
     */
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

    /**
     * The function returns the queue of non-priority tasks.
     * 
     * @return The method is returning a Queue of Task objects.
     */
    public Queue<Task> getNonPriorityTasks() {
        return nonPriorityTasks;
    }

    /**
     * The function returns the heap of priority tasks.
     * 
     * @return The method is returning a Heap of Task objects.
     */
    public Heap<Task> getPriorityTasks() {
        return priorityTasks;
    }

    /**
     * The function returns the hash table of tasks.
     * 
     * @return The method is returning a HashTable of Task objects.
     */
    public HashTable<String, Task> getTasks() {
        return tasks;
    }

    /**
     * The function returns the heap of tasks sorted by their deadline.
     * 
     * @return A Heap of Task objects is being returned.
     */
    public Heap<Task> getTasksByDeadLine() {
        return tasksByDeadLine;
    }

    /**
     * The function "getSortedHeap" returns a sorted array of tasks based on the specified type (either
     * "DEADLINE" or "PRIORITY").
     * 
     * @param type A string indicating the type of sorting to be performed. It can be either "DEADLINE" or
     *             "PRIORITY".
     * @return The method is returning an array of Task objects.
     */
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

    /**
     * The mergeSort function sorts an array of Task objects using the merge sort algorithm and a given
     * comparator.
     * 
     * @param array      The array parameter is an array of Task objects that needs to be sorted.
     * @param comparator The comparator is a function that defines the ordering of the elements in the
     *                   array. It is used to compare two tasks and determine their relative order. The comparator should
     *                   implement the `Comparator` interface and override the `compare` method.
     */
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

    /**
     * The merge function merges two sorted arrays into one sorted array using a comparator to determine
     * the order.
     * 
     * @param array      The array parameter is the array that will be merged. It is the final merged array that
     *                   will contain all the elements from the left and right arrays.
     * @param left       An array of Task objects representing the left half of the array to be merged.
     * @param right      The "right" parameter is an array of Task objects representing the right half of the
     *                   array to be merged.
     * @param comparator The comparator is a function that compares two tasks and returns an integer value
     *                   based on their order. It is used to determine the order in which the tasks should be merged in the
     *                   final sorted array.
     */
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

    /**
     * The function initializes the system by creating tasks with different priorities and due dates.
     */
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
