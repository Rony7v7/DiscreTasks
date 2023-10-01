import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.discretask.structures.Stack;

public class StackTest {

    Stack<String> stack;

    void setupStage1() {
        stack = new Stack<String>();
    }

    void setupStage2() {
        stack = new Stack<String>();
        stack.push("node1");
        stack.push("node2");
        stack.push("node3");
    }

    @Test
    void testClear() {
        setupStage1();
        assert stack.isEmpty();
    }

    @Test
    void testClear2() {
        setupStage1();
        stack.clear();
        assert stack.isEmpty();
    }

    @Test
    void testIsEmpty() {
        setupStage1();
        assert stack.isEmpty();
    }

    @Test
    void testPeek() {
        setupStage2();
        assert stack.peek().equals("node3");
    }

    @Test
    void testPop() {
        setupStage2();
        assert stack.pop().equals("node3");
    }

    @Test
    void testPush() {
        setupStage1();
        stack.push("node1");
        assert stack.peek().equals("node1");
    }

    @Test
    void testSize() {
        setupStage2();
        assertEquals(3, stack.size());
    }

    @Test
    void testToString() {
        setupStage2();
        assertEquals("node3\nnode2\nnode1\n", stack.toString());
    }
}
