package com.discretask.structures;

import com.discretask.interfaces.IStack;

/**
 * The Stack class is a generic implementation of a stack data structure.
 */
public class Stack<T> implements IStack<T> {

    /**
     * The top variable represents the top of the stack.
     */
    private Node<T> top;

    /**
     * The size variable represents the size of the stack.
     */
    private int size;


    /**
     * This is a default constructor for a stack that initializes the top and size
     */
    public Stack() {
        top = null;
        size = 0;
    }


    /**
     * The clear() function removes all elements from a data structure.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            pop();
        }
    }

    /**
     * The function checks if the top element of a stack is null, indicating that the stack is empty.
     * 
     * @return The method is returning a boolean value.
     */
    @Override
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * The pop() function removes and returns the top element of a stack.
     * 
     * @return The method is returning the popped element from the top of the stack.
     */
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

    /**
     * The push method adds a new element to the top of the stack.
     * 
     * @param s The parameter "s" is of type T, which means it can be any type of object.
     */
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

    /**
     * The peek() function returns the data of the top element in a stack.
     * 
     * @return The method is returning the data of the top element in the stack.
     */
    @Override
    public T peek() {
        return top.getData();
    }

    /**
     * The function returns the size of a data structure.
     * 
     * @return The method is returning the value of the variable "size".
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * The toString() function iterates through a linked list and returns a string representation of its
     * elements.
     * 
     * @return The method is returning a string representation of the elements in a linked list.
     */
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