package me.jy.algs4.ch1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Exercise1_1 {

    private static boolean checkEquals(int a, int b, int c) {
        return a == b && b == c;
    }

    private static boolean isInRange(double input) {
        return input >= 0 && input <= 1d;
    }

    private static String toBinaryString(int input) {
        if (input / 2 == 0) {
            return input + "";
        }
        return toBinaryString(input / 2) + (input % 2 + "");
    }

    private static String printBooleanArray(boolean[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (boolean[] booleans : arr) {
            for (boolean aBoolean : booleans) {
                sb.append(aBoolean ? "* " : "  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    // end::ex1.1.3[]

    private static int[][] transposeArray(int[][] arr) {
        int[][] result = new int[arr[0].length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                result[j][i] = arr[i][j];
            }
        }
        return result;
    }

    private static int floorLog(int n) {
        if (n <= 0) {
            return 0;
        }
        int result = 0;
        while ((n /= 2) > 0) {
            result++;
        }
        return result;
    }
    // end::ex1.1.5[]

    @Test
    @DisplayName("1.1.1")
    void ex111() {
        assertEquals(7, (0 + 15) / 2);
        assertEquals(200.0000002, 2.0e-6 * 100000000.1);
        assertTrue(true && false || true && true);
    }

    @Test
    @DisplayName("1.1.2")
    void ex112() {
        assertEquals(1.618, (1 + 2.236) / 2);
        assertEquals(10.0, 1 + 2 + 3 + 4.0);
        assertTrue(4.1 >= 4);
        assertEquals("33", 1 + 2 + "3");
    }
    // end::ex1.1.9[]

    // tag::ex1.1.3[]
    @ParameterizedTest
    @CsvSource({"1,1,2,false", "11,11,11,true"})
    @DisplayName("1.1.3")
    void ex113(int a, int b, int c, boolean result) {
        assertEquals(result, checkEquals(a, b, c));
    }

    // tag::ex1.1.5[]
    @ParameterizedTest
    @CsvSource({"0.2d,0.5d,true", "0.2d,2d,false", "2d,1d,false"})
    @DisplayName("1.1.5")
    void ex115(double inputX, double inputY, boolean result) {
        assertEquals(result, isInRange(inputX) && isInRange(inputY));
    }
    // end::ex1.1.11[]

    // tag::ex1.1.9[]
    @ParameterizedTest
    @CsvSource({"2,10", "3,11", "127,1111111", "32,100000"})
    @DisplayName("1.1.9")
    void ex119(int input, String result) {
        assertEquals(result, toBinaryString(input));
    }

    // tag::ex1.1.11[]
    @Test
    @DisplayName("1.1.11")
    void ex1111() {
        boolean[][] arr = {{true}};
        assertEquals("* \n", printBooleanArray(arr));
        boolean[][] arr2 = {{true, false, true}, {false, true, false}};
        assertEquals("*   * \n  *   \n", printBooleanArray(arr2));
    }
    // end::ex1.1.13[]

    // tag::ex1.1.13[]
    @Test
    @DisplayName("1.1.13")
    void ex1113() {
        int[][] arr = {{1, 2}, {3, 4}};
        int[][] result = transposeArray(arr);
        assertEquals(1, result[0][0]);
        assertEquals(3, result[0][1]);
        assertEquals(2, result[1][0]);
        assertEquals(4, result[1][1]);
    }

    // tag::ex1.1.14[]
    @ParameterizedTest
    @CsvSource({"0,1", "1,2", "3,15", "7,129", "10,1025"})
    @DisplayName("1.1.14")
    void ex1114(int expect, int input) {
        assertEquals(expect, floorLog(input));
    }
    // end::ex1.1.14[]


}
