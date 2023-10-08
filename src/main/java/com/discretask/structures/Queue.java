package com.discretask.structures;

import java.lang.reflect.Array;

import com.discretask.interfaces.IQueue;

public class Queue<T> implements IQueue<T> {

    private Node<T> front;
    private Node<T> back;

    private int size;

    public Queue() {
        front = null;
        back = null;
        size = 0;
    }

    @Override
    public T dequeue() {
        T data = null;
        if (front != null) {
            data = front.getData();
            front = front.getNext();
            size--;
        }
        return data;
    }

    @Override
    public void enqueue(T node) {
        if (node == null) {
            throw new NullPointerException();
        }
        Node<T> newNode = new Node<T>(node);
        if (front == null) {
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

    public T remove(T node) {
        Queue<T> aux = new Queue<T>();
        T data = null;

        while (!this.isEmpty()) {
            
            if (this.front() == node) {
                data = this.dequeue();
            } else {
                aux.enqueue(this.dequeue());
            }

        }

        this.back = aux.back;
        this.front = aux.front;
        this.size = aux.size;

        return data;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray(Class<T> clazz) {
        T[] array = (T[]) Array.newInstance(clazz, size);
        Node<T> pointer = front;
        int i = 0;
        while (pointer != null) {
            array[i] = pointer.getData();
            pointer = pointer.getNext();
            i++;
        }
        return array;
    }
}
