package me.jy.algs4.ch1;

/**
 * @author jy
 */
public class Accumlator {

    private double m;

    private double s;

    private int n;

    public void add(double x) {
        n++;
        s += (n - 1) * 1.0d / n * Math.pow(x - m, 2);
        m += (x - m) / n;
    }

    public double mean() {
        return m;
    }

    public double var() {
        return s / (n - 1);
    }

    public double stdDev() {
        return Math.sqrt(var());
    }
}
