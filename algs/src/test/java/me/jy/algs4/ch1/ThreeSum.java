package me.jy.algs4.ch1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author jy
 */
public class ThreeSum {

    private static int count(int[] a) {
        int count = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = i + 2; k < n; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static int countFast(int[] a) {
        Arrays.sort(a);
        int count = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int target = -(a[i] + a[j]);
                if (BinarySearch.search(a, target) > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Stream.of(1, 2, 4, 8)
            .map(n -> "/home/jy/Downloads/algs4-data/" + n + "Kints.txt")
            .forEach(f -> {
                int[] ints = new In(f).readAllInts();
                Stopwatch stopwatch = new Stopwatch();
                count(ints);
                System.out.println(f + " [slow]: " + stopwatch.elapsedTime());
                Stopwatch stopwatch2 = new Stopwatch();
                countFast(ints);
                System.out.println(f + " [fast]: " + stopwatch2.elapsedTime());
            });


    }
}
