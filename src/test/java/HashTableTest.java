import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.discretask.structures.HashTable;

public class HashTableTest {

    HashTable<String, String> hashTable;

    void setupScenary1() {
        hashTable = new HashTable<String, String>();
    }

    void setupScenary2() {
        hashTable = new HashTable<String, String>();
        hashTable.put("1", "Juan");
    }

    void setupScenary3() {
        setupScenary1();
        hashTable.put("1", "Juan");
        hashTable.put("2", "Rony");
    }

    // --------- TESTS PUT ------------

    // Casos base
    @Test
    void testPut() {
        setupScenary1();
        hashTable.put("1", "Juan");
        assertEquals(hashTable.get("1"), "Juan");
    }

    // casos límite
    @Test
    void testPut2() {
        setupScenary1();
        hashTable.put("1", "Juan");
        hashTable.put("2", "Rony");
        assertEquals(hashTable.get("1"), "Juan");
    }

    // Casos interesantes
    @Test
    void testPut3() {
        setupScenary3();
        assertThrows(NullPointerException.class, () -> {
            hashTable.put(null, "Rony");
        });
    }

    // -------------- TESTS RESIZE ----------------

    // Casos base
    @Test
    void testResize() {
        HashTable<String, String> hashTable = new HashTable<String, String>(1);
        hashTable.put("key1", "value1");
        hashTable.put("key2", "value2");
        assertEquals(2, hashTable.getTable().length);
    }

    // casos límite
    @Test
    void testResize2() {
        HashTable<String, String> hashTable = new HashTable<String, String>(1);
        hashTable.put("key1", "value1");
        hashTable.put("key2", "value2");
        hashTable.put("key3", "value3");
        hashTable.put("key4", "value4");
        hashTable.put("key5", "value5");
        hashTable.put("key6", "value6");
        hashTable.put("key7", "value7");
        hashTable.put("key8", "value8");
        hashTable.put("key9", "value9");
        hashTable.put("key10", "value10");
        assertTrue(hashTable.getTable().length > 10);
    }

    // Casos interesantes
    @Test
    void testResize3() {
        HashTable<String, String> hashTable = new HashTable<String, String>(3);
        hashTable.put("key1", "value1");
        hashTable.put("key2", "value2");
        hashTable.put("key3", "value3");

        hashTable.put("key4", "value4");
        hashTable.put("key5", "value5");

        assertTrue(hashTable.containsKey("key1"));
        assertTrue(hashTable.containsKey("key2"));
        assertTrue(hashTable.containsKey("key3"));
        assertTrue(hashTable.containsKey("key4"));
        assertTrue(hashTable.containsKey("key5"));
    }

    // -------------- TESTS CONTAINSKEY ------------

    // Casos base
    @Test
    void testContainsKey() {
        setupScenary1();
        assertEquals(false, hashTable.containsKey("1"));
    }

    // Casos límite
    @Test
    void testContainsKey2() {
        setupScenary2();
        assertEquals(true, hashTable.containsKey("1"));
    }

    // Casos interesantes
    @Test
    void testContainsKey3() {
        setupScenary2();
        assertEquals(false, hashTable.containsKey("3"));
    }

    @Test
    void testClear() {
        setupScenary2();
        hashTable.clear();

        assertTrue(hashTable.isEmpty());
    }

    // ----------------- TESTS GET ------------------

    // Casos base
    @Test
    void testGet() {
        setupScenary1();
        assertEquals(hashTable.get("1"), null);
    }

    // Casos límite
    @Test
    void testGet2() {
        setupScenary2();
        assertEquals(hashTable.get("1"), "Juan");
    }

    // Casos interesantes
    @Test
    void testGet3() {
        setupScenary3();
        assertThrows(NullPointerException.class, () -> {
            hashTable.get(null);
        });
    }

    // ----------------- REMOVE TESTS ------------------

    void testRemove1() {
        setupScenary1();
        assertEquals(hashTable.remove("1"), null);
    }

    @Test
    void testRemove2() {
        setupScenary2();
        hashTable.remove("1");
        assertEquals(0, hashTable.size());
    }

    @Test
    void testRemove3() {
        setupScenary3();
        assertThrows(NullPointerException.class, () -> {
            hashTable.remove(null);
        });
    }
    // ----------------- OTHER TESTS ------------------

    @Test
    void testIsEmpty() {
        setupScenary2();
        assert !hashTable.isEmpty();

        setupScenary1();
        assert hashTable.isEmpty();
    }

    @Test
    void testSize1() {
        setupScenary3();
        assertEquals(hashTable.size(), 2);
    }

    @Test
    void testSize2() {
        setupScenary1();
        assertEquals(hashTable.size(), 0);
    }

    @Test
    void testClear1() {
        setupScenary1();
        hashTable.clear();
        assertTrue(hashTable.isEmpty());
    }

    @Test
    void testClear2() {
        setupScenary2();
        hashTable.clear();
        assertTrue(hashTable.isEmpty());
    }

}
