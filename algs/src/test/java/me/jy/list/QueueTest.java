package me.jy.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author jy
 */
class QueueTest {

    @Test
    void testArrayQueue() {

        ArrayQueue<Integer> queue = new ArrayQueue<>();

        assertNull(queue.pop());

        queue.push(1);
        queue.push(2);

        assertEquals(2, queue.size());
        assertEquals(1, queue.pop());
        assertEquals(1, queue.size());

        for (int i = 0; i < 100; i++) {
            queue.push(i);
        }
        assertEquals(101, queue.size());

        assertEquals(2, queue.pop());

        for (int i = 0; i < 100; i++) {
            assertEquals(i, queue.pop());
        }

    }

    @Test
    void testLinkedQueue() {

        LinkedQueue<Integer> queue = new LinkedQueue<>();

        assertNull(queue.pop());

        queue.push(1);
        queue.push(2);

        assertEquals(2, queue.size());
        assertEquals(1, queue.pop());
        assertEquals(1, queue.size());

        for (int i = 0; i < 100; i++) {
            queue.push(i);
        }
        assertEquals(101, queue.size());

        assertEquals(2, queue.pop());

        for (int i = 0; i < 100; i++) {
            assertEquals(i, queue.pop());
        }

    }
}
