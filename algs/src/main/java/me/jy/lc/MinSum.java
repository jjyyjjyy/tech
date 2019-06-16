package me.jy.lc;

/**
 * 小和问题
 * 求数组中每个元素比它前面元素小的子序列之和
 * <p>
 * 归并排序时如果左指针元素比右指针元素大, 则对右指针后面元素个数*左指针元素求和.
 *
 * @author jy
 */
public class MinSum {

    public int getMinSum(int[] arr) {
        return sort(arr, 0, arr.length - 1);
    }

    private int sort(int[] arr, int lo, int hi) {

        if (lo == hi) {
            return 0;
        }

        int mid = lo + ((hi - lo) >> 1);
        return sort(arr, lo, mid) +
            sort(arr, mid + 1, hi)
            + merge(arr, lo, mid, hi);
    }

    private int merge(int[] arr, int lo, int mid, int hi) {
        int i = 0;
        int[] tmp = new int[hi - lo + 1];

        // 小数和
        int sum = 0;

        // 左部分指针
        int p1 = lo;
        // 右部分指针
        int p2 = mid + 1;

        while (p1 <= mid && p2 <= hi) {
            sum += arr[p1] < arr[p2] ? arr[p1] * (hi - p2 + 1) : 0;
            tmp[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            tmp[i++] = arr[p1++];
        }
        while (p2 <= hi) {
            tmp[i++] = arr[p2++];
        }
        if (tmp.length >= 0) System.arraycopy(tmp, 0, arr, lo, tmp.length);
        return sum;
    }

    int getMinSum0(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    sum += arr[i];
                }
            }
        }
        return sum;
    }
}
