package me.jy.search;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class BinarySearchTest {

    private BinarySearch<Integer> binarySearch = new BinarySearch<>();

    @Test
    void search() {
        assertEquals(-1, binarySearch.search(new Integer[]{1, 2}, 0));
        assertEquals(-1, binarySearch.search(new Integer[]{1, 2}, 3));
        assertEquals(1, binarySearch.search(new Integer[]{1, 2}, 2));
        assertEquals(3, binarySearch.search(new Integer[]{1, 2, 5, 6, 9}, 6));
        Integer[] arr = IntStream.iterate(0, a -> a + 2).limit(100_000_000).boxed().toArray(Integer[]::new);
        log.info("START ...");
        assertEquals(3, binarySearch.search(arr, 6));
        log.info("OVER ...");


    }
}
