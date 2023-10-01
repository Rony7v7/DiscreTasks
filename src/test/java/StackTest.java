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

    // ---------- TESTS PUSH ------------

    // Casos base
    @Test
    void testPush1() {
        setupStage1();
        stack.push("node1");
        assertEquals(stack.peek(), "node1");
    }

    // Casos límite
    @Test
    void testPush2() {
        setupStage2();
        stack.push("node1");
        stack.push("node4");
        stack.push("node2");
        stack.push("node4");
        stack.push("node0");
        assertEquals(stack.peek(), "node0");
    }

    // Casos interesantes
    @Test
    void testPush3() {
        setupStage2();
        stack.push("node1");
        stack.push("node4");
        stack.push("node2");
        stack.push("node4");
        stack.push("node0");
        assertEquals(stack.pop(), "node0");
        assertEquals(stack.pop(), "node4");
        assertEquals(stack.pop(), "node2");
    }

    // ---------- TESTS POP ------------

    // Casos base
    @Test
    void testPop1() {
        setupStage2();
        assert stack.pop().equals("node3");
    }

    // Casos límite
    @Test
    void testPop2() {
        setupStage2();
        stack.pop();
        stack.pop();
        stack.pop();
        assert stack.isEmpty();
    }

    // Casos interesantes
    @Test
    void testPop3() {
        setupStage1();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        assertEquals(stack.size(), 0);
    }

    // ---------- TESTS ClEAR ------------

    // Casos base
    @Test
    void testClear2() {
        setupStage2();
        stack.clear();
        assert stack.isEmpty();
    }
    
    // Casos límite
    @Test
    void testClear1() {
        setupStage1();
        stack.clear();
        assert stack.isEmpty();
    }

    // Casos interesantes
    @Test
    void testClear3() {
        setupStage2();
        stack.clear();
        stack.clear();
        stack.clear();
        assertEquals(stack.size(), 0);
    }    
    
    
    // ---------- OTHER TESTS ------------

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
