package me.jy.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author jy
 */
class StackTest {

    @Test
    void testArrayStack() {
        ArrayStack<Integer> stack = new ArrayStack<>();

        assertNull(stack.pop());

        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.size());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.size());

        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }
        assertEquals(101, stack.size());


        for (int i = 99; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }

        assertEquals(1, stack.pop());

    }

    @Test
    void testLinkedStack() {
        LinkedStack<Integer> stack = new LinkedStack<>();

        assertNull(stack.pop());

        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.size());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.size());

        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }
        assertEquals(101, stack.size());


        for (int i = 99; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }

        assertEquals(1, stack.pop());

    }

}
