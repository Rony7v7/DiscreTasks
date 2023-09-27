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

    @Test
    void testBack() {
        setupScenary2();
        assert queue.back().equals("node3");
    }

    @Test
    void testClear() {
        setupScenary2();
        queue.clear();
        assert queue.isEmpty();
    }

    @Test

    void testDequeue() {
        setupScenary2();
        queue.dequeue();
        assert queue.front().equals("node2");
    }

    @Test

    void testEnqueue() {
        setupScenary1();
        queue.enqueue("n");
        assert queue.front().equals("n");
    }

    @Test

    void testFront() {
        setupScenary2();
        assert queue.front().equals("n");
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

    @Test
    void testRemove() {
        setupScenary2();
        queue.remove("node2");
        assert queue.front().equals("n");
    }

}
