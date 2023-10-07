
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.discretask.structures.Heap;
import org.junit.jupiter.api.Test;
import java.util.Comparator;

public class HeapTest {

    private Heap<Integer> heap;

    void setUpScenary1() {
        heap = new Heap<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
    }

    void setUpScenary2() {
        setUpScenary1();
        heap.add(1);
        heap.add(2);
        heap.add(3);
        heap.add(4);
        heap.add(5);
    }

    void setUpScenary3() {
        setUpScenary1();
        heap.add(5);
        heap.add(4);
        heap.add(3);
        heap.add(2);
        heap.add(1);
    }

    void setUpScenary4() {
        setUpScenary1();
        heap.add(1);
        heap.add(3);
        heap.add(5);
        heap.add(2);
        heap.add(4);
    }

    // --------- TESTS ADD ------------

    // Base case
    @Test
    void testAdd() {
        setUpScenary1();
        heap.add(1);
        assertEquals(heap.poll(), 1);
    }

    // Limit case
    @Test
    void testAdd2() {
        setUpScenary2();
        heap.add(6);
        assertEquals(heap.poll(), 1);
    }

    // Interesting case
    @Test
    void testAdd3() {
        setUpScenary3();
        heap.add(6);
        assertEquals(heap.poll(), 1);
    }

    // --------- TESTS POLL ------------

    // Base case
    @Test
    void testPoll() {
        setUpScenary2();
        assertEquals(heap.poll(), 1);
    }

    // Limit case
    @Test
    void testPoll2() {
        setUpScenary3();
        assertEquals(heap.poll(), 1);
    }

    // Interesting case
    @Test
    void testPoll3() {
        setUpScenary4();
        assertEquals(heap.poll(), 1);
    }

    // --------- TESTS IS EMPTY ------------

    // Base case
    @Test
    void testIsEmpty() {
        setUpScenary1();
        assertTrue(heap.isEmpty());
    }

    // Limit case
    @Test
    void testIsEmpty2() {
        setUpScenary2();
        heap.poll();
        heap.poll();
        heap.poll();
        heap.poll();
        heap.poll();
        assertTrue(heap.isEmpty());
    }

    @Test
    void testIsEmpty3() {
        setUpScenary3();
        heap.poll();
        heap.poll();
        heap.poll();
        heap.poll();
        heap.poll();
        assertTrue(heap.isEmpty());
    }

    // --------- TESTS SIZE ------------

    // Base case
    @Test
    void testSize() {
        setUpScenary1();
        assertEquals(heap.size(), 0);
    }

    // Limit case
    @Test
    void testSize2() {
        setUpScenary2();
        assertEquals(heap.size(), 5);
    }

    // Interesting case
    @Test
    void testSize3() {
        setUpScenary3();
        assertEquals(heap.size(), 5);
    }

    // --------- TESTS CLEAR ------------

    // Base case
    @Test
    void testClear() {

        setUpScenary2();
        heap.clear();
        assertTrue(heap.isEmpty());
    }

    // Limit case
    @Test
    void testClear2() {
        setUpScenary3();
        heap.clear();
        assertTrue(heap.isEmpty());
    }

    // Interesting case
    @Test
    void testClear3() {
        setUpScenary4();
        heap.clear();
        assertTrue(heap.isEmpty());
    }
    // --------- TESTS REMOVE ----------

    // Base case
    @Test
    void testRemove() {
        setUpScenary2();
        heap.remove(1);
        assertEquals(heap.poll(), 2);
    }

    // Limit case
    @Test
    void testRemove2() {
        setUpScenary3();
        heap.remove(5);
        assertEquals(heap.poll(), 1);
    }

    // Interesting case
    @Test
    void testRemove3() {
        setUpScenary4();
        heap.remove(4);
        assertEquals(heap.poll(), 1);
    }

    // --------- TESTS HEAPIFY UP ----------

    // Base case
    @Test
    void testHeapifyUp() {
        setUpScenary2();
        heap.add(0);
        assertEquals(heap.poll(), 0);
    }

    // Limit case
    @Test
    void testHeapifyUp2() {
        setUpScenary3();
        heap.add(0);
        assertEquals(heap.poll(), 0);
    }

    // Interesting case
    @Test
    void testHeapifyUp3() {
        setUpScenary4();
        heap.add(0);
        assertEquals(heap.poll(), 0);
    }

    // --------- TESTS HEAPIFY DOWN ----------

    // Base case
    @Test
    void testHeapifyDown() {
        setUpScenary2();
        heap.poll();
        assertEquals(heap.poll(), 2);
    }

}