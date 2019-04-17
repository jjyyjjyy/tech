package me.jy.algs4.ch1;

import edu.princeton.cs.algs4.*;

public class Interval2DScratches {

    public static void main(String[] args) {
        int n = 20;
        double min = 1d, max = 2d;
        StdDraw.setCanvasSize(1024, 512);
        StdDraw.setPenRadius(.005);
        StdDraw.setXscale(min, max);
        StdDraw.setYscale(min, max);

        Interval2D[] intervals = new Interval2D[n];
        for (int i = 0; i < n; i++) {
            intervals[i] = createRandomInterval2D(min, max);
            intervals[i].draw();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Interval2D intervalA = intervals[i];
                Interval2D intervalB = intervals[j];
                if (intervalA.intersects(intervalB)) {
                    StdOut.println("Intersect: " + intervalA + " - " + intervalB);
                }
            }
        }

    }

    private static Interval2D createRandomInterval2D(double min, double max) {
        return new Interval2D(createdRandomInterval1D(min, max), createdRandomInterval1D(min, max));
    }


    private static Interval1D createdRandomInterval1D(double min, double max) {
        double x = StdRandom.uniform(min, max);
        double y = StdRandom.uniform(min, max);
        return new Interval1D(Math.min(x, y), Math.max(x, y));
    }
}
