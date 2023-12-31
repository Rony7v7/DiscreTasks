package com.discretask.structures;

import com.discretask.interfaces.IHashTable;

/**
 * The HashTable class is an implementation of the IHashTable interface in Java.
 */
public class HashTable<K, V> implements IHashTable<K, V> {

    /**
     * The size variable represents the number of key-value pairs in the hash table.
     */
    private int size;

    /**
     * The final variable DEFAULT_SIZE represents the default size of the hash table.
     */
    private final int DEFAULT_SIZE = 11;

    /**
     * The table variable is an array of type NodeHashTable, which is a generic type
     * representing a node in a hash table.
     */
    private NodeHashTable<K, V> table[];

    /**
     * This is a default constructor for a hash table that initializes the size of
     * the hash table to zero and
     * initializes the table variable to an array of type NodeHashTable.
     */
    @SuppressWarnings("unchecked")
    public HashTable() {
        this.size = 0;
        this.table = new NodeHashTable[DEFAULT_SIZE];
    }

    /**
     * This is a constructor for a hash table that takes a size parameter and
     * initializes the size of the hash.
     * @param size The size parameter is an integer value representing the size of the hash table.
     */
    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        this.size = 0;
        this.table = new NodeHashTable[size];
    }
    /**
     * The function returns the array of NodeHashTable objects.
     * 
     * @return The method is returning an array of NodeHashTable objects.
     */
    public NodeHashTable<K, V>[] getTable() {
        return table;
    }

    /**
     * This is a put method for a hash table that adds a new key-value pair to the
     * table or updates the
     * value of an existing key, while ensuring no duplicates are added.
     * 
     * @param key The key of the key-value pair being added to the hash table.
     * @param val "val" is the value associated with the key in the hash table. It
     *            is the value that is
     *            being added or updated in the hash table.
     * @return The method is returning the value (of type V) that was just added or
     *         updated in the hash
     *         table.
     */
    @Override
    public V put(K key, V val) {
        if (size >= table.length) {
            resize();
        }

        int index = hash(key);

        if (table[index] == null) {
            table[index] = new NodeHashTable<K, V>(key, val);
        } else {
            NodeHashTable<K, V> temp = table[index];
            table[index] = new NodeHashTable<K, V>(key, val);
            table[index].setNext(temp);
        }
        size++;

        return val;

    }

    /**
     * The `resize()` function resizes the hash table by creating a new table with double the capacity,
     * rehashing all the elements from the old table into the new table, and updating the size.
     */
    @SuppressWarnings("unchecked")
    public void resize() {
        NodeHashTable<K, V>[] oldTable = table;
        table = new NodeHashTable[oldTable.length * 2];
        size = 0;

        for (int i = 0; i < oldTable.length; i++) {
            NodeHashTable<K, V> node = oldTable[i];
            while (node != null) {
                put(node.getKey(), node.getValue());
                node = node.getNext();
            }
        }
    }

    /**
     * The function checks if a given key exists in the map and returns a boolean
     * value.
     * 
     * @param key The parameter "key" is of type K, which is a generic type
     *            representing the key used to
     *            access a value in a map. It could be any type of object, depending
     *            on how the map is defined.
     * @return The method `containsKey` returns a boolean value indicating whether
     *         the key is present in
     *         the data structure or not. It returns `true` if the key is present,
     *         and `false` otherwise.
     */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * This is a Java function that retrieves the value associated with a given key
     * in a hash table.
     * 
     * @param key The key parameter is the key of the key-value pair that we want to
     *            retrieve the value
     *            for.
     * @return The method is returning the value associated with the specified key
     *         in the hash table. If
     *         the key is not found in the hash table, the method returns null.
     */
    @Override
    public V get(K key) {
        int index = hash(key);
        NodeHashTable<K, V> pointer = table[index];

        while (pointer != null) {
            if (pointer.getKey().equals(key)) {
                return pointer.getValue();
            }
            pointer = pointer.getNext();
        }

        return null;
    }

    /**
     * This function removes a key-value pair from a hash table and returns the
     * value associated with the
     * key.
     * 
     * @param key The key of the key-value pair that needs to be removed from the
     *            hash table.
     * @return The method is returning the value associated with the key that was
     *         removed from the hash
     *         table. If the key is not found in the hash table, the method returns
     *         null.
     */
    @Override
    public V remove(K key) {
        int index = hash(key);
        NodeHashTable<K, V> toRemove = null;

        if (key == null || table[index] == null) {
            return null;
        }

        if (table[index].getKey().equals(key)) {
            toRemove = table[index];
            table[index] = table[index].getNext();
        } else {
            NodeHashTable<K, V> pointer = table[index];
            while (pointer.getNext() != null && !pointer.getNext().getKey().equals(key)) {
                pointer = pointer.getNext();
            }
            if (pointer.getNext() != null) {
                toRemove = pointer.getNext();
                pointer.setNext(pointer.getNext().getNext());
            }
        }

        if (toRemove != null) {
            size--;
            return toRemove.getValue();
        } else {
            return null;
        }
    }

    /**
     * This function returns the size of a data structure.
     * 
     * @return The method is returning the value of the variable "size".
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * This function checks if a data structure is empty by comparing its size to
     * zero.
     * 
     * @return The method is returning a boolean value which indicates whether the
     *         size of the object is
     *         equal to zero or not. If the size is zero, then the method will
     *         return true, indicating that the
     *         object is empty. Otherwise, it will return false, indicating that the
     *         object is not empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * This is a hash function in Java that takes a key and returns an index based
     * on the key's hash code.
     * 
     * @param key The key is a parameter of generic type K, which represents the key
     *            used to access a value
     *            in a hash table. It could be any object type that implements the
     *            hashCode() method.
     * @return The method is returning an integer value, which is the index of the
     *         hash table where the
     *         key-value pair will be stored.
     */
    @Override
    public int hash(K key) {
        int index = key.hashCode() % table.length;
        return index >= 0 ? index : index * -1;
    }

    /**
     * The function clears the hash table by setting the size to 0 and creating a new array of
     * NodeHashTable objects.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        size = 0;
        table = new NodeHashTable[DEFAULT_SIZE];
    }

    /**
     * The function returns an array of keys from a hash table.
     * 
     * @return The method `keySet()` is returning an array of type `K[]`, which
     *         represents the set of keys
     *         in the hash table.
     */
    @SuppressWarnings("unchecked")
    public K[] keySet() {
        K[] keys = (K[]) new Object[size];
        int index = 0;
        for (int i = 0; i < table.length; i++) {
            NodeHashTable<K, V> node = table[i];
            while (node != null) {
                keys[index] = node.getKey();
                index++;
                node = node.getNext();
            }
        }
        return keys;
    }

    /**
     * The function returns an array of values from a hash table.
     * 
     * @return The method is returning an array of type V, which represents the values stored in the hash
     *         table.
     */
    @SuppressWarnings("unchecked")
    public V[] values() {
        V[] values = (V[]) new Object[size];
        int index = 0;
        for (int i = 0; i < table.length; i++) {
            NodeHashTable<K, V> node = table[i];
            while (node != null) {
                values[index] = node.getValue();
                index++;
                node = node.getNext();
            }
        }
        return values;
    }
}
