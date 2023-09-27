import org.junit.jupiter.api.Test;

import com.discretask.structures.Queue;

public class QueueTest {

    Queue<String> queue;

    void setupScenary1() {
        queue = new Queue<String>();
    }

    void setupScenary2() {
        queue = new Queue<String>();
        queue.enqueue("Hola");
        queue.enqueue("Mundo");
        queue.enqueue("!");
    }

    @Test
    void testBack() {
        setupScenary2();
        assert queue.back().equals("!");
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
        assert queue.front().equals("Mundo");
    }

    @Test

    void testEnqueue() {
        setupScenary1();
        queue.enqueue("Hola");
        assert queue.front().equals("Hola");
    }

    @Test

    void testFront() {
        setupScenary2();
        assert queue.front().equals("Hola");
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
        queue.remove("Mundo");
        assert queue.front().equals("Hola");
    }

}
