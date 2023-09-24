package com.discretask.interfaces;

public interface INode<T> {

    public T getData();

    public void setData(T data);

    public INode<T> getNext();

    public void setNext(INode<T> next);
    
}
