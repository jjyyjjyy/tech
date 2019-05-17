package me.jy.algs4.ch3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jy
 */
@DisplayName("ch3 tests")
class Ch3Tests {

    @Test
    @DisplayName("bst test")
    void testBST() {
        BST<String, Integer> bst = new BST<>();
        bst.put("a", 2);
        bst.put("az", 23);
        bst.put("abc", 12);

        assertEquals(2, bst.get("a").intValue());
        assertEquals(23, bst.get("az").intValue());
        assertEquals(12, bst.get("abc").intValue());

        assertEquals("abc", bst.select(1));
        assertEquals("a", bst.select(3));

    }
}
