package me.jy.juc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author jy
 */
class LongAddrTest {

    private final AtomicLong counter1 = new AtomicLong();

    private final LongAdder counter2 = new LongAdder();

    @Test
    void testAtomicLong() {
        for (int i = 0; i < 5_000_000; i++) {
            counter1.incrementAndGet();
        }

        Assertions.assertEquals(5_000_000, counter1.get());

    }

    @Test
    void testLongAddr() {
        for (int i = 0; i < 5_000_000; i++) {
            counter2.increment();
        }

        Assertions.assertEquals(5_000_000, counter2.intValue());
    }

}
