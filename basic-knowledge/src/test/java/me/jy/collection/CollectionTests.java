package me.jy.collection;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jy
 * @date 2017/11/13
 */
public class CollectionTests {

    @Test
    public void testListRemove() {
        Iterator<Integer> arrayListItr = Stream.generate(() -> 1)
                .limit(4 * 1_000_000)
                .collect(Collectors.toCollection(ArrayList::new)).iterator();
        Iterator<Integer> linkedListItr = Stream.generate(() -> 1)
                .limit(4 * 1_000_000)
                .collect(Collectors.toCollection(LinkedList::new)).iterator();

        LocalDateTime start1 = LocalDateTime.now();
        while (arrayListItr.hasNext()) {
            if (arrayListItr.next() % 2 == 0) {
                arrayListItr.remove();
            }
        }
        System.out.println("ArrayList cost: " + Duration.between(start1, LocalDateTime.now()).toMillis());
        LocalDateTime start2 = LocalDateTime.now();
        while (linkedListItr.hasNext()) {
            if (linkedListItr.next() % 2 == 0) {
                linkedListItr.remove();
            }
        }
        System.out.println("LinkedList cost: " + Duration.between(start2, LocalDateTime.now()).toMillis());

        // 40W    41 9
        // 400W   59 41
        // 4000W  94 155
    }
}
