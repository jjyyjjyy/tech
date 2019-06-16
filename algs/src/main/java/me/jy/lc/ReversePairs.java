package me.jy.lc;

import lombok.extern.slf4j.Slf4j;

/**
 * 逆序对问题
 * 求数组中每个元素比它前面元素小的逆序对数量
 * <p>
 * 归并排序时如果左指针元素比右指针元素大, 则累计左指针后面元素个数.
 *
 * @author jy
 */
@Slf4j
public class ReversePairs {

    public int getReversePairs(int[] arr) {
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
        int count = 0;

        // 左部分指针
        int p1 = lo;
        // 右部分指针
        int p2 = mid + 1;

        while (p1 <= mid && p2 <= hi) {
            count += arr[p2] < arr[p1] ? (mid - p1 + 1) : 0;

            tmp[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            tmp[i++] = arr[p1++];
        }
        while (p2 <= hi) {
            tmp[i++] = arr[p2++];
        }
        if (tmp.length >= 0) System.arraycopy(tmp, 0, arr, lo, tmp.length);
        return count;
    }

    int getReversePairs0(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    System.out.printf("[%d,%d]", arr[i], arr[j]);
                    count++;
                }
            }
        }
        System.out.println();
        return count;
    }

}
