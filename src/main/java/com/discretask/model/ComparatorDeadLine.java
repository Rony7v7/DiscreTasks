package com.discretask.model;

import java.util.Comparator;

public class ComparatorDeadLine implements Comparator<Task> {

    public ComparatorDeadLine() {
    }

    @Override
    public int compare(Task o1, Task o2) {
        return o1.getDeadline().compareTo(o2.getDeadline());
    }
 
}