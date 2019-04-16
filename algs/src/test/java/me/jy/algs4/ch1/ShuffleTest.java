package me.jy.algs4.ch1;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author jy
 */
public class ShuffleTest {

    // tag::ex1.1.36[]
    public static int[][] shuffleCheck(int m, int n) {
        int[] arr = new int[m];

        int[][] statistics = new int[m][m];

        for (int i = 0; i < n; i++) {
            reset(arr);
            shuffle(arr);
            for (int j = 0; j < m; j++) {
                statistics[j][arr[j]]++;
            }
        }
        return statistics;
    }

    private static void reset(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
    }

    public static void main(String[] args) {
        int[][] statistics = shuffleCheck(10, 100);
        for (int[] statistic : statistics) {
            StdOut.println(Arrays.toString(statistic));
        }
    }
    // end::ex1.1.36[]

    // tag::ex1.1.37[]
    public static void shuffle(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = ThreadLocalRandom.current().nextInt(n);
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
    // end::ex1.1.37[]

}
