package me.jy.util;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * @author jy
 */
public class ArrayUtil {

    private static final int BOUND = 199;

    private ArrayUtil() {
    }

    public static int[] getRandomArrayWithBound(int size) {
        return IntStream.rangeClosed(1, size)
            .map(i -> ThreadLocalRandom.current().nextInt(BOUND))
            .distinct()
            .toArray();
    }

    public static int[] getRandomArray(int size) {
        return IntStream.rangeClosed(1, size)
            .map(i -> ThreadLocalRandom.current().nextInt())
            .distinct()
            .toArray();
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
