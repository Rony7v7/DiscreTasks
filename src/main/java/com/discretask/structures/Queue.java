package com.discretask.structures;

import java.lang.reflect.Array;

import com.discretask.interfaces.IQueue;

/**
 * The Queue class is a generic implementation of a queue data structure.
 */
public class Queue<T> implements IQueue<T> {

    /**
     * The front variable represents the front of the queue.
     */
    private Node<T> front;

    /**
     * The back variable represents the back of the queue.
     */
    private Node<T> back;

    /**
     * The size variable represents the size of the queue.
     */
    private int size;

    /**
     * This is a default constructor for a queue that initializes the front and back
     */
    public Queue() {
        front = null;
        back = null;
        size = 0;
    }

    /**
     * The dequeue() function removes and returns the data from the front of the queue.
     * 
     * @return The method is returning the data of the node that is being dequeued.
     */
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

    /**
     * The enqueue function adds a new node to the back of the queue.
     * 
     * @param node The node to be added to the queue.
     */
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

    /**
     * The function returns the data of the front element in a queue.
     * 
     * @return The method is returning the data of the front element of a data structure. The type of the
     *         data being returned is T.
     */
    @Override
    public T front() {
        return front.getData();
    }

    /**
     * The function returns the data of the last element in a data structure.
     * 
     * @return The method is returning the data stored in the "back" node.
     */
    @Override
    public T back() {
        return back.getData();
    }

    /**
     * The function checks if the front of a queue is null, indicating that the queue is empty.
     * 
     * @return The method is returning a boolean value.
     */
    @Override
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * The "clear" function sets the front and back pointers to null and the size to 0, effectively
     * emptying the data structure.
     */
    @Override
    public void clear() {
        front = null;
        back = null;
        size = 0;
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
     * The remove function removes a specific node from a queue and returns the removed node.
     * 
     * @param node The parameter "node" is of type T, which means it can be any type of object. It
     *             represents the node that needs to be removed from the queue.
     * @return The method is returning the removed node.
     */
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

    /**
     * The function converts a linked list into an array of a specified class type.
     * 
     * @param clazz The `clazz` parameter is a `Class` object that represents the type of the elements in
     *              the linked list. It is used to create a new array of the same type as the elements in the 
     *              linked list.
     * @return The method is returning an array of type T.
     */
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
