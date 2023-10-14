package com.discretask.interfaces;

/**
 * The IQueue interface contains the methods that a queue data structure should implement.
 */
public interface IQueue<T> {

    public void enqueue(T node);
    
    public T dequeue();
    
    public T front();
    
    public T back();
    
    public boolean isEmpty();
    
    public int size();
    
    public void clear();

}
