package me.jy.lang.thread;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.stream.IntStream;

/**
 * @author jy
 */
public class AtomicDemo {

    public static void main(String[] args) {
        Counter counter = new Counter();
        IntStream.rangeClosed(1, 10000)
            .parallel()
            .forEach(i -> counter.incr());

        System.out.println(counter.getValue());
    }

    private static class Counter {
        private static final AtomicLongFieldUpdater<Counter> UPDATER = AtomicLongFieldUpdater.newUpdater(Counter.class, "value");
        private volatile long value;

        public void incr() {
            UPDATER.incrementAndGet(this);
        }

        public long getValue() {
            return value;
        }
    }
}
