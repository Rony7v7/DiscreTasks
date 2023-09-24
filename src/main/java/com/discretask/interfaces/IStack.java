package com.discretask.interfaces;

public interface IStack<T> {
    
    public void push(T s);
    
    public T pop();
    
    public boolean isEmpty();
    
    public void clear();
    
    public T peek();
    
    public int size();
    
}
