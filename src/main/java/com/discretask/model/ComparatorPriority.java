package com.discretask.model;

import java.util.Comparator;

public class ComparatorPriority implements Comparator<Task> {

    public ComparatorPriority() {
    }

    @Override
    public int compare(Task o1, Task o2) {
        return o1.getPriority().compareTo(o2.getPriority());
    }
    
}
