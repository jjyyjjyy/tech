package me.jy.algs4.ch2;

import java.util.Arrays;

/**
 * @author jy
 */
public interface SortTemplate {

    static void print(int[] arr) {
        ifSorted(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void ifSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                throw new IllegalStateException("not sorted!");
            }
        }
    }

    void sort(int[] arr);

    default boolean less(int a, int b) {
        return a < b;
    }

    default void exchange(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
