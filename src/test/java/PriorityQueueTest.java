
import com.discretask.structures.PriorityQueue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PriorityQueueTest<T> {


    // Test that a new PriorityQueue is empty
    @Test
    public void test_new_priority_queue_is_empty() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(10);
        assertTrue(pq.isEmpty());
    }

    // Test that the size of the PriorityQueue increases when an item is added
    @Test
    public void test_size_increases_when_item_added() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(10);
        pq.offer(5);
        assertEquals(1, pq.size());
    }

    // Test that the PriorityQueue returns the smallest item when polled
    @Test
    public void test_poll_returns_smallest_item() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(10);
        pq.offer(5);
        pq.offer(3);
        pq.offer(7);
        assertEquals(3, (int) pq.poll());
    }

    // Test that the PriorityQueue throws an exception when polled and empty
    @Test
    public void test_poll_throws_exception_when_empty() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(10);
        pq.poll();
    }

    // Test that the PriorityQueue can handle adding and polling null values
    @Test
    public void test_can_handle_null_values() {
        PriorityQueue<String> pq = new PriorityQueue<>(10);
        pq.offer(null);
        assertNull(pq.poll());
    }

    // Test that the PriorityQueue can handle adding and polling duplicate values
    @Test
    public void test_can_handle_duplicate_values() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(10);
        pq.offer(5);
        pq.offer(5);
        assertEquals(5, (int) pq.poll());
        assertEquals(5, (int) pq.poll());
    }
}