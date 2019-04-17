package me.jy.algs4;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class StdLibScratches {

    private static void drawHistogramA() {
        int n = 100;
        StdDraw.setXscale(0, n);
        StdDraw.setYscale(0, n * n);
        StdDraw.setPenRadius(.01d);

        for (int i = 1; i <= n; i++) {
            StdDraw.point(i, i);
            StdDraw.point(i, i * i);
            StdDraw.point(i, i * Math.log(i));
        }
    }

    private static void drawHistogramB() {
        int n = 100;
        double[] arr = new double[n];
        for (int i = 0; i < n; i++) {
            arr[i] = ThreadLocalRandom.current().nextDouble(1d);
        }
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            double x = 1.0d * i / n;
            double y = arr[i] / 2;
            double w = 0.5d / n;
            double h = arr[i] / 2;
            StdDraw.filledRectangle(x, y, w, h);
        }
    }

    public static void main(String[] args) {
        drawHistogramB();
    }
}
