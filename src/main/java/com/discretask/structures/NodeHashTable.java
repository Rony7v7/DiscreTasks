package com.discretask.structures;

/**
 * The NodeHashTable class is a generic class that represents a node in a hash table.
 */
public class NodeHashTable<K, V> {

    /**
     * The key variable represents the key of the key-value pair.
     */
    final private K key;

    /**
     * The hash variable represents the hash of the key.
     */
    final private int hash;

    /**
     * The value variable represents the value of the key-value pair.
     */
    private V value;

    /**
     * The next variable represents the next node in the hash table.
     */
    NodeHashTable<K, V> next;

    /**
     * This is a constructor for a node in a hash table that initializes the key and
     * value variables.
     * @param key The key parameter is of type K, which means it can be any type of object.
     * @param value The value parameter is of type V, which means it can be any type of object.
     */
    public NodeHashTable(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.hash = key.hashCode();
    }

    /**
     * The function returns the value of the hash variable.
     * 
     * @return The method is returning the value of the variable "hash".
     */
    public int getHash() {
        return hash;
    }

    /**
     * The function returns the key of a generic type.
     * 
     * @return The method is returning the value of the variable "key".
     */
    public K getKey() {
        return key;
    }

    /**
     * The function returns the value associated with a key in a data structure.
     * 
     * @return The method is returning the value of type V.
     */
    public V getValue() {
        return value;
    }

    /**
     * The function sets the value of a variable.
     * 
     * @param value The parameter "value" is of type V, which means it can be any type of object.
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * The function returns the value stored in a variable of type V.
     * 
     * @return The method is returning the value of the variable "value".
     */
    public V getData() {
        return value;
    }

    /**
     * The function sets the value of a variable.
     * 
     * @param value The value parameter is of type V, which means it can be any type of object.
     */
    public void setData(V value) {
        this.value = value;
    }

    /**
     * The function returns the next NodeHashTable in a linked list.
     * 
     * @return The method is returning an object of type NodeHashTable<K, V>.
     */
    public NodeHashTable<K, V> getNext() {
        return next;
    }
    
    /**
     * The function sets the next node in a hash table.
     * 
     * @param next The "next" parameter is of type NodeHashTable<K, V>, which represents the next node in
     * the hash table.
     */
    public void setNext(NodeHashTable<K, V> next) {
        this.next = next;
    }

}