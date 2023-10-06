import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.discretask.structures.Stack;

public class StackTest {

    Stack<String> stack;

    void setupStage1() {
        stack = new Stack<String>();
    }

    void setupStage2() {
        stack = new Stack<String>();
        stack.push("1");
    }

    void setupStage3() {
        stack = new Stack<String>();
        stack.push("1");
        stack.push("2");
        stack.push("3");
    }

    // ---------- TESTS PUSH ------------

    // Casos base
    @Test
    void testPush1() {
        setupStage1();
        stack.push("1");
        assertEquals(stack.peek(), "1");
    }

    // Casos límite
    @Test
    void testPush2() {
        setupStage2();
        stack.push("2");
        assertEquals(stack.peek(), "2");
    }

    // Casos interesantes
    @Test
    void testPush3() {
        setupStage3();
        stack.push("3");
        assertEquals(stack.peek(), "3");
    }

    @Test
    void testPush4() {
        setupStage3();
        assertThrows(NullPointerException.class, () -> {
            stack.push(null);
        });
    }

    // ---------- TESTS POP ------------

    // Casos base
    @Test
    void testPop1() {
        setupStage2();
        assert stack.pop().equals("1");
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

    @Test
    void testPop4() {
        setupStage1();
        assertNull(stack.pop());
    }

    @Test
    void testPop5() {
        setupStage2();
        assert stack.pop().equals("1");
        assertTrue(stack.isEmpty());
    }

    @Test
    void testPop6() {
        setupStage2();
        assert stack.pop().equals("1");
    }

    // ---------- TESTS ClEAR ------------

    // Casos base
    @Test
    void testClear2() {
        setupStage2();
        stack.clear();
        assertNull(stack.pop());
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

    // ---------- PEEK TESTS ------------

    @Test
    void testPeek1() {
        setupStage1();
        assertThrows(NullPointerException.class, () -> {
            stack.peek();
        });
    }

    @Test
    void testPeek2() {
        setupStage2();
        assert stack.peek().equals("1");
    }

    @Test
    void testPeek3() {
        setupStage3();
        assert stack.peek().equals("3");
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
        assert stack.peek().equals("1");
    }

    @Test
    void testSize() {
        setupStage3();
        assertEquals(3, stack.size());
    }

    @Test
    void testToString() {
        setupStage3();
        assertEquals("3\n2\n1\n", stack.toString());
    }
}
