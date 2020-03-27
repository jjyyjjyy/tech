package me.jy.lang.thread.lock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.IntStream;

/**
 * @author jy
 */
class JUCLockTest {

    @ParameterizedTest
    @CsvSource({"100", "1000", "10000", "100000"})
    void testLock(int count) {

        IntHolder holder = new IntHolder();

        JUCLock lock = new JUCLock();

        IntStream.rangeClosed(1, count)
            .parallel()
            .forEach(i -> {
                lock.lock();
                holder.count++;
                lock.unlock();
            });

        Assertions.assertEquals(count, holder.count);
    }

    private static class IntHolder {
        private int count;
    }

}
