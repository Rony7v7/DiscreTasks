package com.discretask.interfaces;

/**
 * The IPriorityQueue interface contains the methods that a priority queue data structure should implement.
 */
public interface IPriorityQueue<T> {

    public void add(T item);
    
    public boolean isEmpty();
    
    public int size();
    
    public void clear();

    public T poll();
    
}
