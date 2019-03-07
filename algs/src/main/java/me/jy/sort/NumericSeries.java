package me.jy.sort;

import java.util.Arrays;

/**
 * 生成排列: 求给定数列的下一最小数列
 *
 * @author jy
 */
public class NumericSeries {

    public static int[] getNextSeries(int[] origin) {
        int size = origin.length;
        int[] newArr = Arrays.copyOf(origin, size);

        for (int i = size - 1; i > 0; i--) {
            int left = origin[i - 1];
            int right = origin[i];
            if (left >= right) {
                newArr[i - 1] = left;
                newArr[i] = right;
                continue;
            }
            if (i == size - 1) {
                newArr[i - 1] = right;
                newArr[i] = left;
                break;
            }

            int minIndex = i;

            for (int j = i + 1; j < size; j++) {
                if (origin[minIndex] > origin[j] && origin[j] > left) {
                    minIndex = j;
                }
            }

            newArr[i - 1] = origin[minIndex];
            origin[minIndex] = left;
            int[] part = Arrays.copyOfRange(origin, i, size);
            Arrays.sort(part);
            // 复制排序好的 a_(i)...a_n到新数组
            System.arraycopy(part, 0, newArr, i, size - i);
            break;
        }
        return newArr;
    }

}
