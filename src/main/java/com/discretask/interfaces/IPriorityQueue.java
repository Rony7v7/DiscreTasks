package com.discretask.interfaces;

public interface IPriorityQueue<T> {

    public void add(T item);
    
    public boolean isEmpty();
    
    public int size();
    
    public void clear();

    public T poll();
    
}
