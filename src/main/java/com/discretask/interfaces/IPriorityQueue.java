package com.discretask.interfaces;

public interface IPriorityQueue<T> {

    public void add(T item);
    
    public T poll();
    
    public T peek();
    
    public boolean isEmpty();
    
    public int size();
    
    public void clear();
    
}
