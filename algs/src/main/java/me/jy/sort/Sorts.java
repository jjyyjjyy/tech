package me.jy.sort;

/**
 * @author jy
 */
public final class Sorts {

    // tag::bubble-sort[]

    /**
     * 冒泡排序
     * 两两比较, 如果左边的元素比右边的大, 则交换位置.
     * O(n^2)
     */
    public static class BubbleSort implements SortTemplate {

        @Override
        public void sort(int[] arr) {
            for (int i = arr.length - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    if (less(arr[j + 1], arr[j])) {
                        exchange(arr, j, j + 1);
                    }
                }
            }
        }
    }
    // end::bubble-sort[]

    // tag::select-sort[]

    /**
     * 选择排序
     * 选择数组剩余元素中最小的元素放到首位
     * O(n^2)
     */
    public static class SelectSort implements SortTemplate {

        @Override
        public void sort(int[] arr) {
            for (int i = 0; i < arr.length - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < arr.length; j++) {
                    if (less(arr[j], arr[minIndex])) {
                        minIndex = j;
                    }
                }
                exchange(arr, i, minIndex);
            }
        }
    }
    // end::select-sort[]

    // tag::insert-sort[]

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
                    } else {
                        break;
                    }
                }
            }
        }
    }
    // end::insert-sort[]

    // tag::shell-sort[]

    /**
     * 希尔排序(优化过的插入排序)
     * <p>
     * 步骤:
     * 1. 选定一个增长量, 对元素进行分组.
     * 2. 对每组元素进行插入排序.
     * 3. 减小增长量直至1, 重复步骤2.
     */
    public static class ShellSort implements SortTemplate {

        @Override
        public void sort(int[] arr) {
            int gap = 1;
            while (gap < arr.length / 2) {
                gap = gap * 2 + 1;
            }
            for (; gap > 0; gap /= 2) {
                for (int i = gap; i < arr.length; i++) {
                    for (int j = i; j >= gap; j -= gap) {
                        if (less(arr[j], arr[j - gap])) {
                            exchange(arr, j, j - gap);
                        } else {
                            break; // 如果左边比j小, 则不需要继续向左比较了.
                        }
                    }
                }
            }
        }
    }
    // end::shell-sort[]

    // tag::merge-sort[]

    /**
     * 归并排序
     * 将数组切成若干小数组后排序, 最后合并.
     */
    public static class MergeSort implements SortTemplate {

        @Override
        public void sort(int[] arr) {
            sort(arr, 0, arr.length - 1);
        }

        private void sort(int[] arr, int lo, int hi) {
            if (lo >= hi) {
                return;
            }
            int mid = lo + (hi - lo) / 2;
            sort(arr, lo, mid);
            sort(arr, mid + 1, hi);
            merge(arr, lo, mid, hi);
        }

        /*private void sort2(int[] arr) {
            int n = arr.length;
            for (int i = 1; i < n; i += i) {
                for (int lo = 0; lo < n - i; lo += i * 2) { // 每次处理i*2个元素再合并
                    merge(arr, lo, lo + i - 1, Math.min(n - 1, lo + i * 2 - 1));
                }
            }
        }*/

        private void merge(int[] arr, int lo, int mid, int hi) {

            int[] tmpArr = new int[arr.length];

            int i = 0, p1 = lo, p2 = mid + 1;
            while (p1 <= mid && p2 <= hi) {
                if (less(arr[p1], arr[p2])) {
                    tmpArr[i++] = arr[p1++];
                } else {
                    tmpArr[i++] = arr[p2++];
                }
            }
            while (p1 <= mid) {
                tmpArr[i++] = arr[p1++];
            }
            while (p2 <= hi) {
                tmpArr[i++] = arr[p2++];
            }
            System.arraycopy(tmpArr, 0, arr, 0, tmpArr.length);
        }

    }
    // end::merge-sort[]

    // tag::quick-sort[]

    /**
     * 快速排序
     * 保证某一元素左边值比该元素值小且有序, 右边值比该元素值大且有序, 则该数组有序.
     * 1. 找到1个分界值, 把比分界值小的放左边, 把比分界值大的放右边.
     * 2. 重复步骤1将分界值左右两边分别排序.
     */
    public static class QuickSort implements SortTemplate {

        @Override
        public void sort(int[] arr) {
            sort(arr, 0, arr.length - 1);
        }

        private void sort(int[] arr, int lo, int hi) {
            if (lo >= hi) {
                return;
            }
            int partitionIndex = partition(arr, lo, hi);
            sort(arr, lo, partitionIndex - 1);
            sort(arr, partitionIndex + 1, hi);
        }

        private int partition(int[] arr, int lo, int hi) {
            int left = lo;
            int partitionValue = arr[hi];

            for (int i = left; i < hi; i++) {
                if (arr[i] < partitionValue) {
                    exchange(arr, i, left++);
                }
            }
            exchange(arr, left, hi);
            return left;
        }

        /*private void sort2(int[] arr, int lo, int hi) {
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
        }*/
    }
    // end::quick-sort[]

    /**
     * 堆排序
     * 先将数组转成最大堆的形式, 再将第一项放到最后. 逐步重复1..n-1项
     */
    public static class HeapSort implements SortTemplate {

        @Override
        public void sort(int[] arr) {

            int last = arr.length - 1;

            // 构造最大堆
            buildMaxHeap(arr, last);
            while (last > 0) {
                // 交换首尾元素
                exchange(arr, 0, last);
                buildMaxHeap(arr, --last);
            }
        }

        private void buildMaxHeap(int[] arr, int last) {

            if (last == 0) {
                return;
            }
            // 当前尾节点的父节点
            int parent = (last - 1) / 2;

            while (parent >= 0) {
                int left = parent * 2 + 1;
                int right = parent * 2 + 2;
                // 找到两个子节点中的值最大的节点
                int maxChildIndex = right <= last && less(arr[left], arr[right]) ? right : left;
                // 如果父节点值小, 则交换
                if (less(arr[parent], arr[maxChildIndex])) {
                    exchange(arr, parent, maxChildIndex);
                }
                parent--;
            }
        }
    }
}
