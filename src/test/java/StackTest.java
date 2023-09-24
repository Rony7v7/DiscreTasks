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
        stack.push("Hola");
        stack.push("Mundo");
        stack.push("!");
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
        assert stack.peek().equals("!");
    }

    @Test
    void testPop() {
        setupStage2();
        assert stack.pop().equals("!");
    }

    @Test
    void testPush() {
        setupStage1();
        stack.push("Hola");
        assert stack.peek().equals("Hola");
    }

    @Test
    void testSize() {
        setupStage2();
        assertEquals(3, stack.size());
    }

    @Test
    void testToString() {
        setupStage2();
        assertEquals("!\nMundo\nHola\n", stack.toString());
    }
}
