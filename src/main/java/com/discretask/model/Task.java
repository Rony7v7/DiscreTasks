package com.discretask.model;

import java.util.Calendar;

public class Task {
    private String title;
    private String content;
    private Priority priority;
    private String userCategory;
    private Calendar deadline;

    public Task(String title, String content, Priority priority, String userCategory, Calendar deadline) {
        this.title = title;
        this.content = content;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(String userCategory) {
        this.userCategory = userCategory;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }

}
