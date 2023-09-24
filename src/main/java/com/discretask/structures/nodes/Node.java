package com.discretask.structures.nodes;

import com.discretask.interfaces.INode;

public class Node<T> implements INode<T>{

    private T data;
    private Node<T> next;

    public Node(T data) {
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public Node<T> getNext() {
        return next;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public void setNext(INode<T> next) {
        this.next = (Node<T>) next;
    }




}