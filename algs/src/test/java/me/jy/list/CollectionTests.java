package me.jy.list;

import me.jy.list.base.MyIterator;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jy
 * @date 2017/11/13
 */
public class CollectionTests {

    @Test
    public void testMyArrayList() {
        MyArrayList<Integer> list = MyArrayList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertEquals(10, list.size());
        list.add(11);
        assertEquals(11, list.size());
        list.remove(5);
        assertEquals(10, list.size());
        assertEquals(Integer.valueOf(11), list.get(9));

        MyIterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.remove();
        }
        assertEquals(0, list.size());
    }

    @Test
    public void testMyLinkedList() {
        MyLinkedList<Integer> list = MyLinkedList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(10, list.size());
        list.add(11);
        assertEquals(11, list.size());
        list.remove(5);
        assertEquals(10, list.size());
        assertEquals(Integer.valueOf(11), list.get(9));
    }

    @Test
    public void testDeque() {
//        Deque<Integer> q = new ArrayDeque<>(); //120ms
        Deque<Integer> q = new LinkedList<>(); // 180ms
        long begin = System.currentTimeMillis();
        test(q);
        long end = System.currentTimeMillis();
        System.out.println("took " + (end - begin) + "ms");
    }

    private static void test(Deque<Integer> q) {
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 10_000; j++) {
                q.addLast(j);
            }

            for (int j = 0; j < 10_000; j++) {
                q.removeFirst();
            }
        }
    }
}
