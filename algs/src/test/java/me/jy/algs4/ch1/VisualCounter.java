package me.jy.algs4.ch1;

import edu.princeton.cs.algs4.StdDraw;

/**
 * @author jy
 */
public class VisualCounter {

    private int n;

    private final int max;

    private int value;

    public VisualCounter(int n, int max) {
        this.n = n;
        this.max = max;
    }

    public void increment() {
        if (n == 0 || Math.abs(value) == max) {
            return;
        }
        value++;
        n--;
    }

    public void decrement() {
        if (n == 0 || Math.abs(value) == max) {
            return;
        }
        value--;
        n--;
    }

    public int getValue() {
        return value;
    }

    public static void main(String[] args) {
        int n = 1000;
        int max = 100;
        StdDraw.setCanvasSize(1024, 512);
        StdDraw.setPenRadius(0.005);
        StdDraw.setXscale(0, n + 100);
        StdDraw.setYscale(-max - 10, max + 10);

        VisualCounter counter = new VisualCounter(n, max);

        for (int i = 0; i < n; i++) {
            if (Math.random() < 0.5) {
                counter.increment();
            } else {
                counter.decrement();
            }
            StdDraw.point(i, counter.getValue());
        }
    }

}
