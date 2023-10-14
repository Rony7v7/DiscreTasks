package com.discretask.structures;

import java.util.Comparator;
import com.discretask.interfaces.IPriorityQueue;

/**
 * The Heap class is an implementation of the IPriorityQueue interface.
 */
public class Heap<T> implements IPriorityQueue<T> {

    /**
     * The heap variable is an array of type T, which is a generic type representing
     * a heap.
     */
    private T[] heap;

    /**
     * The size variable represents the number of elements in the heap.
     */
    private int size;

    /**
     * The capacity variable represents the maximum number of elements the heap can
     * hold.
     */
    private int capacity;

    /**
     * The final variable DEFAULT_SIZE represents the default size of the heap.
     */
    private final int DEFAULT_SIZE = 11;

    /**
     * The comparator variable is an object of type Comparator, which is an interface
     * that represents a comparison function.
     */
    private Comparator<T> comparator;


    /**
     * This is a default constructor for a heap that initializes the size of the heap
     * to zero and initializes the heap variable to an array of type T.
     * @param comparator The comparator parameter is an object of type Comparator, which is an interface
     *                   that represents a comparison function.
     */
    @SuppressWarnings("unchecked")
    public Heap(Comparator<T> comparator) {
        this.size = 0;
        this.heap = (T[]) new Object[DEFAULT_SIZE];
        this.comparator = comparator;
        this.capacity = DEFAULT_SIZE;
    }

    /**
     * The add function adds an item to the heap, resizing if necessary, and then performs heapifyUp to
     * maintain the heap property.
     * 
     * @param item The item to be added to the heap.
     */
    @Override
    public void add(T item) {
        if (size == capacity) {
            resize();
        }
        if (item == null) {
            throw new NullPointerException();
        }
        heap[size] = item;
        size++;
        heapifyUp();
    }

    /**
     * The function removes and returns the top element from a heap data structure.
     * 
     * @return The method is returning an item of type T.
     */
    @Override
    public T poll() {
        if (size == 0) {
            return null;
        }
        T item = heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;
        heapifyDown();
        return item;
    }

    /**
     * The function checks if the data structure is empty by comparing its size to zero.
     * 
     * @return The method is returning a boolean value, specifically whether the size variable is equal to
     *         0.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
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
     * The clear() function clears the heap by creating a new array of objects and setting the size to 0.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        heap = (T[]) new Object[capacity];
        size = 0;
    }

    /**
     * The function `heapifyUp()` is used to maintain the heap property by moving an element up the heap
     * until it is in the correct position. It is like the buildHeap() function in the Heap class.
     */
    private void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && comparator.compare(parent(index), heap[index]) > 0) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    /**
     * The function heapifyDown() is used to maintain the heap property by moving the element at the given
     * index down the heap until it is in the correct position.
     */
    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && comparator.compare(rightChild(index), leftChild(index)) > 0) {
                smallerChildIndex = getRightChildIndex(index);
            }
            if (comparator.compare(heap[index], heap[smallerChildIndex]) > 0) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }

    /**
     * The function resizes the heap by doubling its capacity and creating a new array with the updated
     * capacity.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        capacity *= 2;
        T[] newHeap = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

    /**
     * The swap function swaps the elements at two given indices in an array.
     * 
     * @param index1 The index of the first element to be swapped.
     * @param index2 index2 is the index of the second element that needs to be swapped.
     */
    private void swap(int index1, int index2) {
        T temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    /**
     * The function checks if a given index has a parent index.
     * 
     * @param index The index parameter represents the index of a node in a data structure.
     * @return The method is returning a boolean value.
     */
    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    /**
     * The function returns the index of the parent node in a binary tree given the index of a child node.
     * 
     * @param index The index parameter represents the index of a node in a binary heap data structure.
     * @return The method is returning the index of the parent node in a binary tree given the index of a
     *         child node.
     */
    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    /**
     * The function returns the parent element of a given index in a heap.
     * 
     * @param index The index parameter represents the index of a node in a binary heap data structure.
     * @return The method is returning the element at the parent index of the given index in the heap.
     */
    private T parent(int index) {
        return heap[getParentIndex(index)];
    }

    /**
     * The function checks if a given index has a left child in a binary tree.
     * 
     * @param index The index parameter represents the index of a node in a binary tree.
     * @return The method is returning a boolean value.
     */
    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    /**
     * The function returns the index of the left child of a given index in a binary tree.
     * 
     * @param index The index parameter represents the index of a node in a binary tree.
     * @return The method is returning the index of the left child of a given index in a binary tree.
     */
    private int getLeftChildIndex(int index) {
        return index * 2 + 1;
    }

    /**
     * The function returns the left child element of a given index in a heap.
     * 
     * @param index The index parameter represents the index of a node in a binary heap.
     * @return The left child of the element at the given index in the heap.
     */
    private T leftChild(int index) {
        return heap[getLeftChildIndex(index)];
    }

    /**
     * The function checks if a given index has a right child in a binary tree.
     * 
     * @param index The index parameter represents the index of a node in a binary tree.
     * @return The method is returning a boolean value.
     */
    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    /**
     * The function returns the index of the right child node in a binary tree given the index of its
     * parent node.
     * 
     * @param index The index parameter represents the index of a node in a binary tree.
     * @return The method is returning the index of the right child of a given index in a binary tree.
     */
    private int getRightChildIndex(int index) {
        return index * 2 + 2;
    }
    /**
     * The function returns the right child element of a given index in a heap.
     * 
     * @param index The parameter "index" represents the index of the parent node in the heap.
     * @return The method is returning the element at the right child index of the given index in the heap.
     */
    private T rightChild(int index) {
        return heap[getRightChildIndex(index)];
    }

    /**
     * The remove function removes an element from the heap and returns the removed element.
     * 
     * @param element The element parameter represents the element that you want to remove from the heap.
     * @return The method is returning the element that was removed from the heap.
     */
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (heap[i].equals(element)) {
                heap[i] = null;
                break;
            }
        }

        T[] temp = heap;
        clear();
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != null) {
                add(temp[i]);
            }
        }

        return element;
    }

    /**
     * The function `getHeap` copies the elements of the heap array into the result array.
     * 
     * @param result The parameter "result" is an array of type T, which is the type of elements stored in
     *               the heap.
     */
    public void getHeap(T[] result) {
        if (result.length < size) {
            throw new IllegalArgumentException("Result array is too small");
        }
        System.arraycopy(heap, 0, result, 0, size);
    }

}
