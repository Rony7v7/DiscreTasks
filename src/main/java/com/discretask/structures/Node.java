package com.discretask.structures;

import com.discretask.interfaces.INode;

/**
 * The Node class is a generic class that implements the INode interface.
 */
public class Node<T> implements INode<T>{

    /**
     * The data variable represents the data stored in the node.
     */
    private T data;

    /**
     * The next variable represents the next node in the linked list.
     */
    private Node<T> next;

    /**
     * This is a default constructor for a node that initializes the data variable.
     *
     * @param data The parameter "data" is of type T, which means it can be any type of data.
     */
    public Node(T data) {
        this.data = data;
    }

    /**
     * The function returns the data stored in a variable of type T.
     * 
     * @return The method is returning the value of the variable "data". The type of the returned value is
     *         generic and is denoted by the placeholder "T".
     */
    @Override
    public T getData() {
        return data;
    }

    /**
     * The function returns the next node in a linked list.
     * 
     * @return The method is returning a Node object of type T.
     */
    @Override
    public Node<T> getNext() {
        return next;
    }

    /**
     * The function sets the value of the "data" variable to the provided input.
     * 
     * @param data The parameter "data" is of type T, which means it can be any type of data.
     */
    @Override
    public void setData(T data) {
        this.data = data;
    }

    /**
     * The function sets the next node in a linked list.
     * 
     * @param next The "next" parameter is of type INode<T>, which is an interface representing a node in a
     *             linked list. It is used to set the next node in the linked list.
     */
    @Override
    public void setNext(INode<T> next) {
        this.next = (Node<T>) next;
    }


}