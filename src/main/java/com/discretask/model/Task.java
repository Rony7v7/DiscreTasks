package com.discretask.model;

import java.util.Calendar;

/**
 * The `Task` class is used to represent a task in the system.
 */
public class Task {

    /**
     * The title variable is used to represent the title of the task.
     */
    private String title;

    /**
     * The content variable is used to represent the content of the task.
     */
    private String content;

    /**
     * The priority variable is used to represent the priority of the task.
     */
    private Priority priority;

    /**
     * The userCategory variable is used to represent the category of the task.
     */
    private String userCategory;

    /**
     * The deadline variable is used to represent the deadline of the task.
     */
    private Calendar deadline;

    /**
     * The id variable is used to represent the id of the task.
     */
    private String id;

    /**
     * The constructor of the `Task` class.
     * @param title The title of the task.
     * @param content The content of the task.
     * @param priority The priority of the task.
     * @param userCategory The category of the task.
     * @param deadline The deadline of the task.
     * @param id The id of the task.
     */
    public Task(String title, String content, Priority priority, String userCategory, Calendar deadline, String id) {
        this.title = title;
        this.content = content;
        this.priority = priority;
        this.userCategory = userCategory;
        this.deadline = deadline;
        this.id = id;
    }


    /**
     * Get the title of the title of the task.
     * 
     * @return The method is returning the value of the variable "title".
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the task.
     * 
     * @param title The new title for the task.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the content of the task.
     * 
     * @return The content of the task.
     */
    public String getContent() {
        return content;
    }

     /**
     * Set the content of the task.
     * 
     * @param content The new content for the task.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Get the priority of the task.
     * 
     * @return The priority of the task.
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Set the priority of the task.
     * 
     * @param priority The new priority for the task.
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Get the category of the task.
     * 
     * @return The category of the task.
     */
    public String getUserCategory() {
        return userCategory;
    }

    /**
     * Set the category of the task.
     * 
     * @param userCategory The new category for the task.
     */
    public void setUserCategory(String userCategory) {
        this.userCategory = userCategory;
    }

    /**
     * Get the deadline of the task.
     * 
     * @return The deadline of the task.
     */
    public Calendar getDeadline() {
        return deadline;
    }

    /**
     * Set the deadline of the task.
     * 
     * @param deadline The new deadline for the task.
     */
    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }

    /**
     * Get the id of the task.
     * 
     * @return The id of the task.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id of the task.
     * 
     * @param id The new id for the task.
     */
    public void setId(String id) {
        this.id = id;
    }
    
}
