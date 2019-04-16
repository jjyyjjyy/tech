package me.jy.algs4.ch1;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author jy
 */
public class Matrix {

    public static double dot(double[] x, double[] y) {
        int xLength = x.length;
        int yLength = y.length;
        if (xLength != yLength) {
            throw new IllegalArgumentException();
        }

        return IntStream
            .rangeClosed(0, xLength - 1)
            .mapToDouble(i -> x[i] * y[i])
            .sum();
    }

    public static double[][] multi(double[][] a, double[][] b) {

        double[][] result = new double[a.length][b.length];
        for (int i = 0; i < a.length; i++) {

            for (int row = 0; row < a[i].length; row++) {
                double sum = 0;
                for (int j = 0; j < a[i].length; j++) {
                    sum += a[i][j] * b[j][i];
                }
                result[i][row] = sum;
            }

        }
        return result;
    }

    public static double[][] transpose(double[][] a) {
        double[][] result = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                result[i][j] = a[j][i];
            }
        }
        return result;
    }

    public static double[] multi(double[][] a, double[] b) {
        return multi(a, new double[][]{b})[0];
    }

    public static double[] multi(double[] a, double[][] b) {
        double[][] multi = multi(new double[][]{a}, b);
        return Arrays.stream(multi).mapToDouble(e -> e[0]).toArray();
    }
}
