package com.discretask.interfaces;

/**
 * The IStack interface contains the methods that a stack data structure should implement.
 */
public interface IStack<T> {
    
    public void push(T s);
    
    public T pop();
    
    public boolean isEmpty();
    
    public void clear();
    
    public T peek();
    
    public int size();
    
}
