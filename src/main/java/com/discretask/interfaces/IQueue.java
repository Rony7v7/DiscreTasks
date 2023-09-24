package com.discretask.interfaces;

public interface IQueue<T> {

    public void enqueue(T node);
    
    public void dequeue();
    
    public T front();
    
    public T back();
    
    public boolean isEmpty();
    
    public int size();
    
    public void clear();

}
