package com.discretask.structures;

import com.discretask.interfaces.IQueue;

public class Queue<T> implements IQueue<T>{

    private Node<T> front;
    private Node<T> back;

    private int size;

    public Queue() {
        front = null;
        back = null;
        size = 0;
    }

    @Override
    public void dequeue() {
        if(front != null) {
            front = front.getNext();
            size--;
        }
    }

    @Override
    public void enqueue(T node) {
        Node<T> newNode = new Node<T>(node);
        if(front == null) {
            front = newNode;
            back = front;
        } else {
            back.setNext(newNode);
            back = newNode;
        }
        size++;
    }

    @Override
    public T front() {
        return front.getData();
    }

    @Override
    public T back() {
        return back.getData();
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public void clear() {
        front = null;
        back = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }


}
