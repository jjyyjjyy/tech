package me.jy.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jy
 */
class HeapTest {

    @Test
    void testHeap() {
        Heap<Integer> heap = new Heap<>();

        for (int i = 0; i < 10; i++) {
            heap.insert(i);
        }

        for (int i = 9; i >= 0; i--) {
            assertEquals(i, heap.pop());
        }
    }

}
