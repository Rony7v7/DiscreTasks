package com.discretask.model;

import java.util.Comparator;

/**
 * ComparatorPriority is a comparator that compares two tasks based on their priorities.
 */
public class ComparatorPriority implements Comparator<Task> {

    /**
     * Constructor for ComparatorPriority.
     */
    public ComparatorPriority() {
    }

    /**
     * Compares two tasks based on their priorities.
     * @param o1 The first task to be compared.
     * @param o2 The second task to be compared.
     * @return A negative integer, zero, or a positive integer as the first task's priority is less than, equal to, or
     * greater than the second task's priority.
     */
    @Override
    public int compare(Task o1, Task o2) {
        return o1.getPriority().compareTo(o2.getPriority());
    }
    
}
