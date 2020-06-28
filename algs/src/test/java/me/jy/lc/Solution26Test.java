package me.jy.lc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("#26")
class Solution26Test {

    private final RemoveDuplicates solution26 = new RemoveDuplicates();

    @Test
    void removeDuplicates() {

        assertEquals(0, solution26.removeDuplicates(new int[0]));

        int[] array1 = new int[]{1, 1, 2};
        assertEquals(2, solution26.removeDuplicates(array1));
        assertArrayEquals(new int[]{1, 2, 2}, array1);

        int[] array2 = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        assertEquals(5, solution26.removeDuplicates(array2));
        assertArrayEquals(new int[]{0, 1, 2, 3, 4, 2, 2, 3, 3, 4}, array2);

    }
}
