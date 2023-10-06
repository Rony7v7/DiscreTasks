package com.discretask.structures;

import com.discretask.interfaces.IStack;

public class Stack<T> implements IStack<T> {

    private Node<T> top;
    private int size;

    public Stack() {
        top = null;
        size = 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            pop();
        }
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public T pop() {
        T pop = null;
        if (top != null) {
            T data = top.getData();
            top = top.getNext();
            pop = data;
            size--;
        }

        return pop;
    }

    @Override
    public void push(T s) {
        if (s == null) {
            throw new NullPointerException();
        }
        Node<T> newNode = new Node<T>(s);
        if (top == null) {
            top = newNode;
        } else {
            Node<T> temp = newNode;
            temp.setNext(top);
            top = temp;
        }
        size++;

    }

    @Override
    public T peek() {
        return top.getData();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String str = "";
        Node<T> pointer = top;
        while (pointer != null) {
            str += pointer.getData() + "\n";
            pointer = pointer.getNext();
        }
        return str;
    }

}