package com.discretask.model;

import java.util.Comparator;

public class ComparatorPriority implements Comparator<Task> {

    public ComparatorPriority() {
    }

    @Override
    public int compare(Task o1, Task o2) {
        int result = 0;
        if (o1.getPriority().compareTo(o2.getPriority()) > 0) {
            result = -1;
        } else if (o1.getPriority().compareTo(o2.getPriority()) < 0) {
            result = 1;
        }
        return result;
    }
    
}
