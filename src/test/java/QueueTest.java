import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.discretask.structures.Queue;

public class QueueTest {

    Queue<String> queue;

    void setupScenary1() {
        queue = new Queue<String>();
    }

    void setupScenary2() {
        queue = new Queue<String>();
        queue.enqueue("node1");
        queue.enqueue("node2");
        queue.enqueue("node3");
    }

    void setupScenary3() {
        queue = new Queue<String>();
        queue.enqueue("node1");
        queue.enqueue("node2");
        queue.enqueue("node3");
        queue.enqueue("node4");
        queue.enqueue("node5");
        queue.enqueue("node6");
        queue.enqueue("node7");
        queue.enqueue("node8");
        queue.enqueue("node9");
        queue.enqueue("node10");
        queue.enqueue("node11");
        queue.enqueue("node12");
        queue.enqueue("node13");
        queue.enqueue("node14");
        queue.enqueue("node15");
    }

    // ---------- TESTS ENQUEUE ------------

    // Casos base
    @Test
    void testEnqueue1() {
        setupScenary1();
        queue.enqueue("n");
        assert queue.front().equals("n");
    }

    // Casos límite
    @Test
    void testEnqueue2() {
        setupScenary3();
        queue.enqueue("n");
        assertEquals(queue.back(), "n");
    }

    // Casos interesantes
    @Test
    void testEnqueue3() {
        setupScenary3();
        queue.enqueue("n");
        assertEquals(queue.front(), "node1");
    }

    // ---------- TESTS DEQUEUE ------------

    // Casos base
    @Test
    void testDequeue1() {
        setupScenary2();
        queue.dequeue();
        assertEquals(queue.front(), "node2");
    }

    // Casos límite
    @Test
    void testDequeue2() {
        setupScenary2();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    // Casos interesantes
    @Test
    void testDequeue3() {
        setupScenary2();
        queue.dequeue();
        assertEquals(queue.front(), "node2");
    }

    // ---------- TESTS REMOVE ------------

    // Casos base
    @Test
    void testRemove1() {
        setupScenary2();
        queue.remove("node2");
        assertEquals("node1", queue.front());
    }

    // Casos límite
    @Test
    void testRemove2() {
        setupScenary2();
        queue.remove("node2");
        queue.remove("node3");
        queue.remove("node1");
        assertTrue(queue.isEmpty());
    }

    // Casos interesantes
    @Test
    void testRemove3() {
        setupScenary3();
        queue.remove("node5");
        assertEquals(queue.front(), "node1");
    }

    // ----------- OTHER TESTS ---------------

    @Test
    void testBack() {
        setupScenary2();
        assertEquals(queue.back(), "node3");
    }

    @Test
    void testClear() {
        setupScenary2();
        queue.clear();
        assertTrue(queue.isEmpty());
    }

    @Test
    void testFront() {
        setupScenary2();
        assert queue.front().equals("node1");
    }

    @Test
    void testIsEmpty() {
        setupScenary1();
        assert queue.isEmpty();
    }

    @Test
    void testSize() {
        setupScenary2();
        assert queue.size() == 3;
    }

}
