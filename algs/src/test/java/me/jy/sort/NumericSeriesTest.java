package me.jy.sort;

import org.junit.jupiter.api.Test;

import static me.jy.sort.NumericSeries.getNextSeries;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @author jy
 */
public class NumericSeriesTest {

    @Test
    public void testGetNextSeries() {
        assertArrayEquals(new int[]{5, 2, 3, 4, 1}, getNextSeries(new int[]{5, 2, 3, 1, 4}));
        assertArrayEquals(new int[]{5, 2, 3, 1, 4}, getNextSeries(new int[]{5, 2, 1, 4, 3}));
        assertArrayEquals(new int[]{1, 3, 2, 3}, getNextSeries(new int[]{1, 2, 3, 3}));
        assertArrayEquals(new int[]{1, 3, 3, 2}, getNextSeries(new int[]{1, 3, 2, 3}));
        assertArrayEquals(new int[]{9, 8, 7, 6}, getNextSeries(new int[]{9, 8, 7, 6}));
    }
}
