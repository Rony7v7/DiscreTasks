package com.discretask.structures;

// import interfaces.INode;

public class NodeHashTable<K, V> {
    final K key;
    final int hash;
    V value;

    NodeHashTable<K, V> next;

    public NodeHashTable(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.hash = key.hashCode();
    }

    public int getHash() {
        return hash;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public V getData() {
        return value;
    }

    public void setData(V data) {
    }

    public NodeHashTable<K, V> getNext() {
        return next;
    }

    public void setNext(NodeHashTable<K, V> next) {
        this.next = next;
    }

}