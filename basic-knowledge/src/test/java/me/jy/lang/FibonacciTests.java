package me.jy.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jy
 */
public class FibonacciTests {

    private static final double SQRT5 = Math.sqrt(5.0d);

    private static final double FACTOR1 = BigDecimal.valueOf((1 + SQRT5) / 2).doubleValue();
    private static final double FACTOR2 = BigDecimal.valueOf((1 - SQRT5) / 2).doubleValue();

    private static int cal1(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return cal1(n - 1) + cal1(n - 2);
    }

    private static int cal2(int n, int sum1, int sum2) {
        if (n == 0) {
            return sum1;
        }
        return cal2(n - 1, sum2, sum1 + sum2);
    }

    private static int cal3(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return (int) ((1 / SQRT5) * (Math.pow(FACTOR1, n) - Math.pow(FACTOR2, n)));
    }

    @Test
    public void testCal1() {
        assertEquals(1, cal1(1));
        assertEquals(1, cal1(2));
        assertEquals(2, cal1(3));
        assertEquals(3, cal1(4));
        assertEquals(5, cal1(5));
        assertEquals(8, cal1(6));
        assertEquals(13, cal1(7));
        Assertions.assertThrows(StackOverflowError.class, () -> {
            assertEquals(1836311903, cal1(100_000));
        }, "stack overflow");
    }

    @Test
    public void testCal2() {
        assertEquals(1836311903, cal2(46, 0, 1));
    }

    @Test
    public void testCal3() {
        assertEquals(1, cal3(1));
        assertEquals(1, cal3(2));
        assertEquals(2, cal3(3));
        assertEquals(3, cal3(4));
        assertEquals(5, cal3(5));
        assertEquals(8, cal3(6));
        assertEquals(13, cal3(7));
        assertEquals(1836311903, cal3(46));
    }

}
