package me.jy.tree;

/**
 * 堆实现
 *
 * @author jy
 */
public class Heap<T extends Comparable<T>> {

    private static final int DEFAULT_CAPACITY = 15;

    private final Object[] data;

    private int size;

    private final int capacity;

    public Heap() {
        this(DEFAULT_CAPACITY);
    }

    public Heap(int capacity) {
        this.capacity = capacity;
        this.data = new Object[capacity + 1];
    }

    public void insert(T e) {

        if (size >= capacity) {
            throw new RuntimeException("Index out of bounds!");
        }

        this.data[++size] = e;

        swim(size);

    }

    private void swim(int p) {
        while (p > 1
            && ((T) data[p / 2]).compareTo((T) data[p]) < 0) {
            exchange(p / 2, p);
            p /= 2;
        }
    }

    private void sink(int p) {
        int j;
        while ((j = p * 2) <= size) {
            if (j < size && ((T) data[j]).compareTo((T) data[j + 1]) < 0) {
                j++;
            }
            if (((T) data[p]).compareTo((T) data[j]) > 0) {
                break;
            }
            exchange(p, j);
            p = j;
        }
    }


    public T pop() {

        T max = (T) data[1];

        data[1] = data[size];
        data[size--] = null;

        sink(1);

        return max;
    }

    private void exchange(int a, int b) {
        Object tmp = data[b];
        data[b] = data[a];
        data[a] = tmp;
    }

}
