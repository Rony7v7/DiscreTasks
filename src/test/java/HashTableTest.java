import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.discretask.structures.HashTable;

public class HashTableTest {

    HashTable<String, String> hashTable;

    void setupScenary1() {
        hashTable = new HashTable<String, String>();
    }

    void setupScenary2() {
        hashTable = new HashTable<String, String>();
        hashTable.put("key1", "value1");
        hashTable.put("key2", "value2");
    }

    void setupScenary3() {
        setupScenary1();
        hashTable.put("key1", "value1");
        hashTable.put("key1", "value2");
        hashTable.put("key1", "value3");
    }

    @Test
    void testClear() {
        setupScenary2();
        hashTable.clear();

        assert hashTable.isEmpty();
    }

    // @Test
    // void testContainsKey() {
    // setupScenary2();
    // assert hashTable.containsKey("Hola");
    // }

    @Test
    void testGet() {
        setupScenary2();

        assertEquals(hashTable.get("key1"), "value1");
    }

    @Test
    void testHash() {

    }

    @Test
    void testIsEmpty() {
        setupScenary2();
        assert !hashTable.isEmpty();

        setupScenary1();
        assert hashTable.isEmpty();
    }

    @Test
    void testPut() {
        setupScenary1();
        hashTable.put("key1", "value1");
        assertEquals(hashTable.get("key1"), "value1");
    }

    @Test
    void testPut2() {
        setupScenary2();
        hashTable.put("key1", "value3");
        assertEquals(hashTable.get("key1"), "value3");
    }

    @Test
    void testRemove1() {
        setupScenary2();
        assertEquals(hashTable.remove("key1"), "value1");
    }

    @Test
    void testRemove2() {
        setupScenary3();
        assertEquals(hashTable.remove("key1"), "value3");
    }

    @Test
    void testSize1() {
        setupScenary2();
        assertEquals(hashTable.size(), 2);
    }

    @Test
    void testSize2() {
        setupScenary1();
        assertEquals(hashTable.size(), 0);
    }

    // @Test
    // void testResize() {
    // setupScenary2();
    // hashTable.resize();
    // assertEquals(hashTable.size(), 4);
    // }

}
