package me.jy;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jy
 */
public class AllInOne {

    private static final AtomicInteger nextHashCode = new AtomicInteger();
    private static final int HASH_INCREMENT = 0x61c88647;

    @ParameterizedTest
    @CsvSource({"4", "8", "16"})
    void testHash(int capacity) {
        long c = (long) ((1L << 32) * (Math.sqrt(5) - 1) / 2);
        int hashIncrement = -(int) c;

        int keyIndex;
        for (int i = 0; i < capacity; i++) {
            keyIndex = (i + 1) * hashIncrement & (capacity - 1);
            System.out.print(keyIndex + " ");
        }
        System.out.println();
    }

    @RepeatedTest(3)
    void testAddHashcode() {
        System.out.println(nextHashCode.addAndGet(HASH_INCREMENT));
    }

    @Test
    void testThreadLocal() {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.set(1);
        System.out.println(threadLocal.get());
    }


}
