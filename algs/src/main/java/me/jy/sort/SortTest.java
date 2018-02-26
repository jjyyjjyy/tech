package me.jy.sort;

import java.util.Arrays;

/**
 * @author jy
 * @date 2018/02/26
 */
public class SortTest {

    private static <T extends Comparable<T>> void selectSort(T[] array) {
        for (int i = 0; i < array.length; i++) {
            T min = array[i];
            int indexToSwap = i;
            for (int j = i + 1; j < array.length; j++) { // 选出最小的数
                if (min.compareTo(array[j]) > 0) {
                    min = array[j];
                    indexToSwap = j;
                }
            }
            if (indexToSwap != i)
                swap(array, i, indexToSwap);
        }
    }

    private static <T extends Comparable<T>> void insertSort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            T temp = array[i];
            int j = i;
            for (; j > 0 && temp.compareTo(array[j - 1]) < 0; j--) {
                array[j] = array[j - 1];
            }
            array[j] = temp;

        }
    }

    private static <T extends Comparable<T>> void bubbleSort(T[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) { // 前面的比后面的大则交换位置
                    swap(array, j, j + 1);
                }
            }
        }
    }

    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    private static <T extends Comparable<T>> int search(T[] arr, T target) {
        int start = 0;
        int end = arr.length;

        while (start <= end) {
            int mid = (start + end) / 2;
            int result = target.compareTo(arr[mid]);
            if (result == 0) {
                return mid;
            } else if (result < 0) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        throw new RuntimeException("No such element");
    }

    public static void main(String[] args) {
        Integer[] arr = {2, 4, 213, 1, 12};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(search(arr, 213));
    }
}
