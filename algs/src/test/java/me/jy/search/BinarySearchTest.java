package me.jy.search;

import lombok.extern.slf4j.Slf4j;
import me.jy.algs4.ch1.BinarySearch;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class BinarySearchTest {

    @Test
    void search() {
        assertEquals(-1, BinarySearch.search(new int[]{1, 2}, 0));
        assertEquals(-1, BinarySearch.search(new int[]{1, 2}, 3));
        assertEquals(1, BinarySearch.search(new int[]{1, 2}, 2));
        assertEquals(3, BinarySearch.search(new int[]{1, 2, 5, 6, 9}, 6));
        int[] arr = IntStream.iterate(0, a -> a + 2).limit(100_000_000).toArray();
        log.info("START ...");
        assertEquals(3, BinarySearch.search(arr, 6));
        log.info("OVER ...");


    }
}
