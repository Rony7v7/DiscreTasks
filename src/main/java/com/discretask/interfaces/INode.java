package com.discretask.interfaces;

/**
 * The INode interface contains the methods that a node data structure should implement.
 */
public interface INode<T> {

    public T getData();

    public void setData(T data);

    public INode<T> getNext();

    public void setNext(INode<T> next);
    
}
