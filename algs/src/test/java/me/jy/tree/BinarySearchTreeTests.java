package me.jy.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author jy
 */
class BinarySearchTreeTests {

    BinarySearchTree<Integer> tree;

    @BeforeEach
    void init() {
        tree = new BinarySearchTree<>();
        List<Integer> integerList = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());
        Collections.shuffle(integerList);
        integerList.forEach(tree::insert);
    }

    @Test
    void testFind() {

        IntStream.rangeClosed(2, 99).map(i -> (int) (ThreadLocalRandom.current().nextDouble() * 97 + 2))
            .forEach(tree::insert);

        assertEquals(1, tree.findMin().intValue());
        assertEquals(100, tree.findMax().intValue());

    }

    @Test
    void testContains() {
        IntStream.rangeClosed(1, 100).forEach(i -> assertTrue(tree.contains(i)));
    }

    @Test
    void testRemove() {
        IntStream.rangeClosed(30, 70).forEach(i -> {
            tree.remove(i);
            assertFalse(tree.contains(i), i + "");
        });
    }

}
