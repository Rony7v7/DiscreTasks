package com.discretask.model;

import java.util.Comparator;

/**
 * ComparatorDeadLine is a comparator that compares two tasks based on their deadlines.
 */
public class ComparatorDeadLine implements Comparator<Task> {

    /**
     * Constructor for ComparatorDeadLine.
     */
    public ComparatorDeadLine() {
    }

    /**
     * Compares two tasks based on their deadlines.
     * @param o1 The first task to be compared.
     * @param o2 The second task to be compared.
     * @return A negative integer, zero, or a positive integer as the first task's deadline is less than, equal to, or
     * greater than the second task's deadline.
     */
    @Override
    public int compare(Task o1, Task o2) {
        return o1.getDeadline().compareTo(o2.getDeadline());
    }
 
}
