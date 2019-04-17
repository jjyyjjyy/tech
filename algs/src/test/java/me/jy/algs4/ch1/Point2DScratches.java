package me.jy.algs4.ch1;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Point2DScratches {

    public static void main(String[] args) {
        int n = 20;
        Point2D[] points = new Point2D[n];
        for (int i = 0; i < n; i++) {
            Point2D p = new Point2D(StdRandom.uniform(), StdRandom.uniform());
            points[i] = p;
        }
        double minDistance = 1d;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                minDistance = Math.min(minDistance, points[i].distanceTo(points[j]));
            }
        }
        StdOut.println("Min distance = " + minDistance);

    }
}
