package me.jy.algs4.ch1;

/**
 * 二分查找
 */
public class BinarySearch {

    public static int search(int[] arr, int target) {

        int start = 0;
        int end = arr.length - 1;

        if (end == 0 || arr[start] > target || arr[end] < target) {
            return -1;
        }

        while (start <= end) {
            int mid = (start + end) / 2;
            int midValue = arr[mid];
            if (midValue > target) {
                end = mid - 1;
            } else if (midValue < target) {
                start = mid + 1;
            } else
                return mid;
        }
        return -1;

    }
}
