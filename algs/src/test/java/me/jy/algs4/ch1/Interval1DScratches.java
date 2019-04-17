package me.jy.algs4.ch1;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Interval1DScratches {

    public static void main(String[] args) {
        int n = 20;
        Interval1D[] intervals = new Interval1D[n];
        for (int i = 0; i < n; i++) {
            double x = StdRandom.uniform();
            double y = StdRandom.uniform();
            intervals[i] = new Interval1D(Math.min(x, y), Math.max(x, y));
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (intervals[i].intersects(intervals[j])) {
                    StdOut.println(intervals[i] + " - " + intervals[j]);
                }
            }
        }
    }
}
