package me.jy.list;

import me.jy.list.base.MyIterator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
}