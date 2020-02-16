package me.jy.tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author jy
 */
class AVLTreeTest {

    @Test
    void testInsert() {
        AVLTree<Integer> tree = new AVLTree<>();

        for (int i = 0; i < 10; i++) {
            tree.insert(i);
        }

        tree.print();

        for (int i = 100; i < 200; i += 2) {
            tree.insert(i);
        }

        Assertions.assertTrue(tree.heightFactor() <= 1);
    }

}
