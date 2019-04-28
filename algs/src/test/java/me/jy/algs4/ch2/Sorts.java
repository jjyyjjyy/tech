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
            int n = arr.length;

            for (int gap = n / 2; gap > 0; gap /= 2) {
                for (int i = gap; i < n; i++) {
                    int j = i;
                    while (j - gap >= 0 && arr[j] < arr[j - gap]) {
                        exchange(arr, j, j - gap);
                        j -= gap;
                    }

                }
            }
        }
    }

    /**
     * 归并排序
     */
    public static class MergeSort implements SortTemplate {

        @Override
        public void sort(int[] arr) {
//            sort(arr, 0, arr.length - 1);
            sort2(arr);
        }

        private void sort(int[] arr, int lo, int hi) {
            if (hi <= lo) {
                return;
            }
            int mid = (lo + hi) / 2;
            sort(arr, lo, mid);
            sort(arr, mid + 1, hi);
            merge(arr, lo, mid, hi);
        }

        private void sort2(int[] arr) {
            int n = arr.length;
            for (int i = 1; i < n; i += i) {
                for (int lo = 0; lo < n - i; lo += i * 2) { // 每次处理i*2个元素再合并
                    merge(arr, lo, lo + i - 1, Math.min(n - 1, lo + i * 2 - 1));
                }
            }
        }

        private void merge(int[] arr, int lo, int mid, int hi) {
            int[] copiedArr = new int[arr.length];
            if (hi + 1 - lo >= 0) System.arraycopy(arr, lo, copiedArr, lo, hi + 1 - lo);
            int leftStart = lo, rightStart = mid + 1;
            for (int i = lo; i <= hi; i++) {
                if (leftStart > mid) { // 左侧合并完成
                    arr[i] = copiedArr[rightStart++];
                    continue;
                }
                if (rightStart > hi) { // 右侧合并完成
                    arr[i] = copiedArr[leftStart++];
                    continue;
                }
                if (less(arr[leftStart], arr[rightStart])) { // 较小值放入arr数组中
                    arr[i] = arr[leftStart++];
                } else arr[i] = arr[rightStart++];
            }
        }

    }

    public static class QuickSort implements SortTemplate {

        @Override
        public void sort(int[] arr) {
            sort(arr, 0, arr.length - 1);
        }

        private void sort(int[] arr, int lo, int hi) {
            if (hi <= lo) {
                return;
            }
            int j = partition(arr, lo, hi);
            sort(arr, lo, j - 1);
            sort(arr, j + 1, hi);
        }

        private int partition(int[] arr, int lo, int hi) {
            return 0;
        }
    }

}
