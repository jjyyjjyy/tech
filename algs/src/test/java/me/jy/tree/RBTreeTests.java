package me.jy.tree;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author jy
 */
class RBTreeTests {

    @SneakyThrows
    private static Object extractColor(Map.Entry entry) {
        Field field = entry.getClass().getDeclaredField("color");
        field.setAccessible(true);
        return field.get(entry);
    }

    @Test
    void testRBMap() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        IntStream.rangeClosed(1, 100).forEach(i -> map.put(i, i));
        List<RedBlackTree.EntryColor> jdkRBTreeColors = map.entrySet().stream().map(RBTreeTests::extractColor).map(c -> (boolean) c ? RedBlackTree.EntryColor.BLACK : RedBlackTree.EntryColor.RED).collect(Collectors.toList());

        RedBlackTree<Integer, Integer> redBlackTree = new RedBlackTree<>();
        IntStream.rangeClosed(1, 100).forEach(i -> redBlackTree.put(i, i));
        List<RedBlackTree.EntryColor> redBlackTreeColors = redBlackTree.entrySet().stream().map(RBTreeTests::extractColor).map(c -> (RedBlackTree.EntryColor) c).collect(Collectors.toList());
        Assertions.assertIterableEquals(jdkRBTreeColors, redBlackTreeColors, "Bad R-B tree implementation!");

    }
}
