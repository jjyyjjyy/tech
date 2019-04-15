package me.jy.search;

/**
 * 二分查找
 */
public class BinarySearch<T extends Comparable<T>> {

    public int search(T[] arr, T target) {

        int start = 0;
        int end = arr.length - 1;

        if (end == 0 || arr[start].compareTo(target) > 0 || arr[end].compareTo(target) < 0) {
            return -1;
        }

        while (true) {
            int mid = (start + end) / 2;
            int compare = arr[mid].compareTo(target);
            if (compare == 0) {
                return mid;
            }
            if (compare < 0) {
                start = mid + 1;
            } else end = mid - 1;
        }

    }
}
