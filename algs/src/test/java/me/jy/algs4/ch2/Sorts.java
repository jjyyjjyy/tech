package me.jy.algs4.ch2;

/**
 * @author jy
 */
public final class Sorts {

    /**
     * 选择排序
     * 选择数组剩余元素中最小的元素放到首位
     * O(n^2)
     */
    public static class SelectSort implements SortTemplate {

        @Override
        public void sort(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                int minIndex = i;
                for (int j = i + 1; j < arr.length; j++) {
                    if (less(arr[j], arr[i])) {
                        minIndex = j;
                    }
                }
                exchange(arr, i, minIndex);
            }
        }
    }

    /**
     * 插入排序
     * 保证索引左边的元素排好序
     * O(n^2)
     */
    public static class InsertSort implements SortTemplate {

        @Override
        public void sort(int[] arr) {
            for (int i = 1; i < arr.length; i++) {
                for (int j = i; j > 0; j--) {
                    if (less(arr[j], arr[j - 1])) {
                        exchange(arr, j, j - 1);
                    }
                }
            }
        }
    }

    /**
     * 希尔排序
     */
    public static class ShellSort implements SortTemplate {

        @Override
        public void sort(int[] arr) {

        }
    }

}
