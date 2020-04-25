package me.jy.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * @author jy
 */
class ThreadLocalTests {

    private static final int HASH_INCREMENT = 0x61c88647;

    @ParameterizedTest
    @CsvSource({"25"})
    void testMagicHashNumber(int maxPower) {
        IntStream.rangeClosed(1, maxPower)
            .parallel()
            .forEach(power -> {
                int length = (int) Math.pow(2, power);
                int hash = 0;
                Set<Integer> set = new HashSet<>();

                for (int i = 0; i < length; i++) {
                    set.add(hash & (length - 1));
                    hash += HASH_INCREMENT;
                }
                Assertions.assertEquals(set.size(), length);
            });
    }
}
