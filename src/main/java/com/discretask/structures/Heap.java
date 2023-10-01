package com.discretask.structures;

import java.util.Comparator;
import com.discretask.interfaces.IPriorityQueue;

public class Heap<T> implements IPriorityQueue<T> {

    private T[] heap;

    private int size;

    private int capacity;

    private final int DEFAULT_SIZE = 11;

    private Comparator<T> comparator;

    @SuppressWarnings("unchecked")
    public Heap(Comparator<T> comparator) {
        this.size = 0;
        this.heap = (T[]) new Object[DEFAULT_SIZE];
        this.comparator = comparator;
        this.capacity = DEFAULT_SIZE;
    }

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


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        heap = (T[]) new Object[capacity];
        size = 0;
    }

    private void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && comparator.compare(parent(index), heap[index]) > 0) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && comparator.compare(rightChild(index), leftChild(index)) < 0) {
                smallerChildIndex = getRightChildIndex(index);
            }
            if (comparator.compare(heap[index], heap[smallerChildIndex]) < 0) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        capacity *= 2;
        T[] newHeap = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

    private void swap(int index1, int index2) {
        T temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private T parent(int index) {
        return heap[getParentIndex(index)];
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private int getLeftChildIndex(int index) {
        return index * 2 + 1;
    }

    private T leftChild(int index) {
        return heap[getLeftChildIndex(index)];
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private int getRightChildIndex(int index) {
        return index * 2 + 2;
    }

    private T rightChild(int index) {
        return heap[getRightChildIndex(index)];
    }

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

    public void getHeap(T[] result) {
        if (result.length < size) {
            throw new IllegalArgumentException("Result array is too small");
        }
        System.arraycopy(heap, 0, result, 0, size);
    }

}
