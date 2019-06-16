package me.jy.algs4.ch2;

import java.util.concurrent.ThreadLocalRandom;

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
     * 将数组切成若干小数组后排序, 最后合并.
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

    /**
     * 快速排序
     * 保证某一元素左边值比该元素值小且有序, 右边值比该元素值大且有序, 则该数组有序.
     */
    public static class QuickSort implements SortTemplate {

        @Override
        public void sort(int[] arr) {
            sort(arr, 0, arr.length - 1);
        }

        private void sort(int[] arr, int lo, int hi) {
            if (hi <= lo) {
                return;
            }
            // 随机选择数组中一位作为partition
            exchange(arr, hi, ThreadLocalRandom.current().nextInt(lo, hi));
            // 取到左区间和右区间位置
            int[] indexes = partition(arr, lo, hi);
            sort(arr, lo, indexes[0]);
            sort(arr, indexes[1] + 1, hi);
        }

        private int[] partition(int[] arr, int lo, int hi) {
            int less = lo - 1;
            int more = hi;
            while (lo < more) {
                if (arr[lo] < arr[hi]) {
                    // 左指针加1, 交换lo
                    exchange(arr, ++less, lo++);
                } else if (arr[lo] > arr[hi]) {
                    // 右指针减1
                    exchange(arr, --more, lo);
                } else {
                    lo++;
                }
            }
            exchange(arr, more, hi);
            return new int[]{less, more};
        }
    }

    /**
     * 堆排序
     * 将数组转成最大堆的形式, 再将第一项放到最后. 逐步重复1..n-1项
     */
    public static class HeapSort implements SortTemplate {

        @Override
        public void sort(int[] arr) {
            for (int i = arr.length - 1; i > 0; i--) {
                buildMaxHeap(arr, i);
                // 交换首尾元素
                exchange(arr, 0, i);
            }
        }

        private void buildMaxHeap(int[] arr, int i) {

            // 当前尾节点的父节点
            int parent = (i + 1) / 2 - 1;

            while (parent >= 0) {
                int left = parent * 2 + 1;
                int right = parent * 2 + 2;
                // 找到两个子节点中的值最大的节点
                int maxChildIndex = less(arr[left], arr[right]) && right <= i ? right : left;
                // 如果父节点值小, 则交换
                if (less(arr[parent], arr[maxChildIndex])) {
                    exchange(arr, parent, maxChildIndex);
                }
                parent--;
            }
        }
    }
}
