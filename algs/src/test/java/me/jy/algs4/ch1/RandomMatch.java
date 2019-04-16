package me.jy.algs4.ch1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

/**
 * @author jy
 */
public class RandomMatch {

    private static final int[] lengths = {1_000, 10_000, 100_000, 1_000_000};

    public static void main(String[] args) {
        int t = args.length == 0 ? 1 : Integer.valueOf(args[0]);

        for (int i = 0; i < t; i++) {
            for (int n : lengths) {
                int[] a = createArray(n);
                int[] b = createArray(n);
                Stopwatch stopwatch = new Stopwatch();
                StdOut.println("Elements " + n + " start...");
                for (int e : a) {
                    if (BinarySearch.search(b, e) > 0) {
                        StdOut.println(e + " ");
                    }
                }
                StdOut.println("\n Elements " + n + " cost " + stopwatch.elapsedTime() + "s");
            }
        }

    }

    private static int[] createArray(int length) {
        return Stream.generate(() -> ThreadLocalRandom.current().nextInt(1_000_000))
            .limit(length)
            .mapToInt(a -> a)
            .toArray();
    }
}
