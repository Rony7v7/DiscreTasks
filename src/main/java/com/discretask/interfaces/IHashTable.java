package com.discretask.interfaces;

/**
 * The IHashTable interface contains the methods that a hash table data structure should implement.
 */
public interface IHashTable<K, V> {
    
    public V put(K key, V value);

    public V get(K key);

    public V remove(K key);

    public boolean containsKey(K key);
    
    public boolean isEmpty();

    public int size();
    
    public void clear();

    public int hash(K key);

}
