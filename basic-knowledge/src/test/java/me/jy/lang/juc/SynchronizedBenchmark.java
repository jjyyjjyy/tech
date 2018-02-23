package me.jy.lang.juc;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

/**
 * @author jy
 * @date 2018/02/23
 */
public class SynchronizedBenchmark {


    @Test
    public void testSync() {
        tes(Counter::addSync);
    }

    @Test
    public void testLock() {
        tes(Counter::addByLock);
    }


    private void tes(Consumer<Counter> consumer) {
        long start = System.nanoTime();
        ExecutorService pool = Executors.newFixedThreadPool(5);
        Counter benchmark = new Counter();
        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            pool.execute(() -> {
                for (int j = 0; j < 100; j++) {
                    consumer.accept(benchmark);
                    if (benchmark.counter == 10000) {
                        latch.countDown();
                        long end = System.nanoTime();
                        System.out.println(consumer+": " + (end - start));
                    }
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class Counter {
        private int counter = 0;

        private Lock lock = new ReentrantLock();

        public synchronized void addSync() {
            counter++;
        }

        public void addByLock() {
            lock.lock();
            counter++;
            lock.unlock();
        }
    }
}
