package com.discretask.structures;

import com.discretask.interfaces.IHashTable;

public class HashTable<K, V> implements IHashTable<K, V> {

    private int size;
    private final int DEFAULT_SIZE = 11;
    private NodeHashTable<K, V> table[];

    @SuppressWarnings("unchecked")
    public HashTable() {
        this.size = 0;
        this.table = new NodeHashTable[DEFAULT_SIZE];
    }

    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        this.size = 0;
        this.table = new NodeHashTable[size];
    }

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

    @SuppressWarnings("unchecked")
    public void resize() {
        NodeHashTable<K, V>[] oldTable = table;
        table = new NodeHashTable[oldTable.length * 2];
        size = 0;

        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                put(oldTable[i].getKey(), oldTable[i].getValue());
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

        if (table[index] == null) {
            return null;
        }

        for (int i = 0; i < table.length; i++) {

            while (pointer != null) {

                if (table[index].getKey().equals(key)) {
                    return table[index].getValue();

                } else if (table[index].getNext() != null) {
                    pointer = pointer.getNext();
                }

            }

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

        if (key == null) {
            return null;
        }

        if (table[index] == null) {
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

        size--;
        return toRemove.getValue();
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
        int index = key.hashCode() % DEFAULT_SIZE;
        return index >= 0 ? index : index * -1;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        size = 0;
        table = new NodeHashTable[DEFAULT_SIZE];
    }

    /**
     * The function returns an array of keys from a hash table.
     * 
     * @return The method `keySet()` is returning an array of type `K[]`, which represents the set of keys
     * in the hash table.
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

}
