package me.jy.sort;

/**
 * @author jy
 */
public class MaxPQ {

    private final int[] elements;

    private int n;

    public MaxPQ(int n) {
        this.n = n;
        this.elements = new int[n];
    }

    public MaxPQ(int[] arr) {
        this.n = arr.length;
        this.elements = arr;
    }

    public int size() {
        return n;
    }

    public void insert(int v) {
        elements[++n] = v;
        swim(n);
    }

    public void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exchange(k / 2, k);
            k /= 2;
        }
    }

    public void sink(int k) {
        while (k * 2 < n) {
            int j = k * 2;
            if (j < n && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exchange(k, j);
            k = j;
        }
    }

    public void exchange(int i, int j) {
        int temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    private boolean less(int i, int j) {
        return elements[i] < elements[j];
    }
}
