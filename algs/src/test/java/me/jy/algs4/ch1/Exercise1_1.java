package me.jy.algs4.ch1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Exercise1_1 {

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

    // tag::ex1.1.3[]
    @ParameterizedTest
    @CsvSource({"1,1,2,false", "11,11,11,true"})
    @DisplayName("1.1.3")
    void ex113(int a, int b, int c, boolean result) {
        assertEquals(result, checkEquals(a, b, c));
    }

    private static boolean checkEquals(int a, int b, int c) {
        return a == b && b == c;
    }
    // end::ex1.1.3[]

    // tag::ex1.1.5[]
    @ParameterizedTest
    @CsvSource({"0.2d,0.5d,true", "0.2d,2d,false", "2d,1d,false"})
    @DisplayName("1.1.5")
    void ex115(double inputX, double inputY, boolean result) {
        assertEquals(result, isInRange(inputX) && isInRange(inputY));
    }

    private static boolean isInRange(double input) {
        return input >= 0 && input <= 1d;
    }
    // end::ex1.1.5[]

    // tag::ex1.1.9[]
    @ParameterizedTest
    @CsvSource({"2,10", "3,11", "127,1111111", "32,100000"})
    @DisplayName("1.1.9")
    void ex119(int input, String result) {
        assertEquals(result, toBinaryString(input));
    }

    private static String toBinaryString(int input) {
        if (input / 2 == 0) {
            return input + "";
        }
        return toBinaryString(input / 2) + (input % 2 + "");
    }
    // end::ex1.1.9[]

    // tag::ex1.1.11[]
    @Test
    @DisplayName("1.1.11")
    void ex1111() {
        boolean[][] arr = {{true}};
        assertEquals("* \n", printBooleanArray(arr));
        boolean[][] arr2 = {{true, false, true}, {false, true, false}};
        assertEquals("*   * \n  *   \n", printBooleanArray(arr2));
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
    // end::ex1.1.11[]

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

    private static int[][] transposeArray(int[][] arr) {
        int[][] result = new int[arr[0].length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                result[j][i] = arr[i][j];
            }
        }
        return result;
    }
    // end::ex1.1.13[]

    // tag::ex1.1.14[]
    @ParameterizedTest
    @CsvSource({"0,1", "1,2", "3,15", "7,129", "10,1025"})
    @DisplayName("1.1.14")
    void ex1114(int expect, int input) {
        assertEquals(expect, floorLog(input));
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
    // end::ex1.1.14[]

    // tag::ex1.1.15[]
    @ParameterizedTest
    @MethodSource("histogramArguments")
    @DisplayName("1.1.15")
    void ex1115(int[] a, int m, int[] expect) {
        assertArrayEquals(expect, histogram(a, m));
    }

    private static int[] histogram(int[] a, int m) {
        int[] result = new int[m];
        for (int value : a) {
            if (value < m) {
                result[value]++;
            }
        }
        return result;
    }

    private static Stream<Arguments> histogramArguments() {
        return Stream.of(
            Arguments.of(new int[]{1, 2, 0}, 3, new int[]{1, 1, 1})
        );
    }
    // end::ex1.1.15[]

    // tag::ex1.1.18[]
    @ParameterizedTest
    @CsvSource({"2,25,50", "3,11,33"})
    @DisplayName("1.1.18")
    void ex1118(int a, int b, int expect) {
        assertEquals(expect, mystery(a, b));
    }

    private static int mystery(int a, int b) {
        if (b == 0) {
            return 0;
        }
        if (b % 2 == 0) {
            return mystery(2 * a, b / 2);
        }
        return mystery(2 * a, b / 2) + a;
    }
    // end::ex1.1.18[]

    // tag::ex1.1.19[]
    @ParameterizedTest
    @CsvSource({"7,13"})
    @DisplayName("1.1.19")
    void ex1119(int no, int expect) {
        assertEquals(expect, fibonacci2(no));
    }

    private static long fibonacci2(int n) {
        if (n <= 1) {
            return n;
        }
        int[] arr = new int[n];
        arr[0] = arr[1] = 1;
        for (int i = 2; i < n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n - 1];
    }
    // end::ex1.1.19[]

    // tag::ex1.1.20[]
    @ParameterizedTest
    @CsvSource({"1,0", "5,4.787491742782046"})
    @DisplayName("1.1.20")
    void ex1120(int n, double expect) {
        assertEquals(expect, logNJ(n));
    }

    private static double logNJ(int n) {
        if (n == 1) {
            return 0;
        }
        return Math.log(n) + logNJ(n - 1);
    }
    // end::ex1.1.20[]

    // tag::ex1.1.21[]
    private static void printTable() {
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            String[] str = line.split(" ");
            int a = Integer.valueOf(str[1]);
            double b = Double.valueOf(str[2]);
            StdOut.printf("%s %d %d %.3f", str[0], a, b, a / b);
        }
    }
    // end::ex1.1.21[]

    @ParameterizedTest
    @MethodSource("ex1122Arguments")
    @DisplayName("1.1.22")
    void ex1122(int key, int[] arr, int expect) {
        assertEquals(expect, rank(key, arr));
    }

    private static Stream<Arguments> ex1122Arguments() {
        return Stream.of(
            Arguments.of(1, new int[]{0, 1, 2}, 1),
            Arguments.of(12, new int[]{1, 4, 5, 7, 8, 12, 23, 43}, 5)
        );
    }

    // tag::ex1.1.22[]
    private static int rank(int key, int[] a) {
        return rank0(key, a, 0, a.length - 1, 0);
    }

    private static int rank0(int key, int[] a, int lo, int hi, int depth) {
        for (int i = 0; i < depth; i++) {
            StdOut.print("  ");
        }
        StdOut.printf("%d %d", lo, hi);
        StdOut.println();
        if (lo > hi) {
            return -1;
        }
        int mid = (lo + hi) / 2;
        if (key == a[mid]) {
            return mid;
        }
        if (key > a[mid]) {
            return rank0(key, a, mid + 1, hi, ++depth);
        }
        return rank0(key, a, lo, mid - 1, ++depth);
    }
    // end::ex1.1.22[]

    // tag::ex1.1.23[]
    private static void binarySearchEx23(int[] arr, int[] target, char option) {
        for (int i : target) {
            int rank = rank(i, arr);
            if (rank < 0 && option == '-') {
                StdOut.println(i);
            } else if (rank >= 0 && option == '+')
                StdOut.println(i);
        }
    }
    // end::ex1.1.23[]

    // tag::ex1.1.24[]
    private static int gcd(int a, int b) {
        StdOut.println("a=" + a + ", b=" + b);
        if (b == 0) return a;
        return gcd(b, a % b);
    }
    // end::ex1.1.24[]

    // tag::ex1.1.27[]
    @Test
    @DisplayName("1.1.27")
    void ex1127() {
        System.out.println(binomial(10, 5, 0.25));
    }

    private static double binomial(int n, int k, double p) {
        if (n < 0 || k < 0) {
            return 0;
        }
        if (n == 0 && k == 0) {
            return 1.0;
        }
        return (1 - p) * binomial(n - 1, k, p) + p * binomial(n - 1, k - 1, p);
    }

    // end::ex1.1.27[]

}
