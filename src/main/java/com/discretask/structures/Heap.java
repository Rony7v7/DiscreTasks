package com.discretask.structures;

import com.discretask.interfaces.IPriorityQueue;

public class Heap<T> implements IPriorityQueue<T> {
    
    private Object[] heap; 
    private int size;
    private int capacity;

    public Heap(int capacity) {
        this.capacity = capacity;
        this.heap = new Object[capacity];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void offer(T item) {
        if (size == capacity) {
            resize();
        }
        heap[size] = item;
        int index = size;
        size++;

        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            Comparable<T> currentItem = (Comparable<T>) heap[index]; 
            Comparable<T> parentItem = (Comparable<T>) heap[parentIndex];

            if (currentItem.compareTo((T) parentItem) >= 0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void resize() {
        capacity *= 2;
        Object[] newHeap = new Object[capacity];
        System.arraycopy(heap, 0, newHeap, 0, size);
        heap = newHeap;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T poll() {
        if (isEmpty()) {
            throw new IllegalStateException("PriorityQueue is empty");
        }
        T root = (T) heap[0];
        size--;
        heap[0] = heap[size];
        int index = 0;

        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;

            if (leftChild < size && ((Comparable<T>) heap[leftChild]).compareTo((T) heap[smallest]) < 0) {
                smallest = leftChild;
            }
            if (rightChild < size && ((Comparable<T>) heap[rightChild]).compareTo((T) heap[smallest]) < 0) {
                smallest = rightChild;
            }
            if (smallest == index) {
                break;
            }
            swap(index, smallest);
            index = smallest;
        }
        return root;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("PriorityQueue is empty");
        }
        return (T) heap[0];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        heap = new Object[capacity];
        size = 0;
    }

    private void swap(int i, int j) {
        Object temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void HeapSort() {
        int n = size;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            Object temp = heap[0];
            heap[0] = heap[i];
            heap[i] = temp;

            heapify(i, 0);
        }
    }

     /**
     * The `heapify` method is used in the `HeapSort` method to maintain the heap
     * property of the priority queue. It takes two parameters: `n` which
     * represents the size of the heap, and `i` which represents the index of the
     * current node being heapified.
     * @param n
     * @param i
     */
    @SuppressWarnings("unchecked")
    private void heapify(int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        Comparable<T> largestItem = (Comparable<T>) heap[largest];
        Comparable<T> leftItem = (Comparable<T>) heap[left];
        Comparable<T> rightItem = (Comparable<T>) heap[right];

        if (left < n && leftItem.compareTo((T) largestItem) > 0) {
            largest = left;
        }

        if (right < n && rightItem.compareTo((T) largestItem) > 0) {
            largest = right;
        }

        if (largest != i) {
            Object swap = heap[i];
            heap[i] = heap[largest];
            heap[largest] = swap;

            heapify(n, largest);
        }
    }
   
}

