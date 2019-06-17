package me.jy.stack;

import me.jy.util.RandomArrayGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author jy
 */
class TwoStackQueueTest {

    @ParameterizedTest
    @CsvSource({"10", "100", "1000", "10000", "100000"})
    void testPoll(@ConvertWith(RandomArrayGenerator.class) int[] arr) {
        TwoStackQueue queue = new TwoStackQueue();
        for (int e : arr) {
            queue.add(e);
        }
        for (int e : arr) {
            Assertions.assertEquals(e, queue.poll().intValue());
        }
    }
}
