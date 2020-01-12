package scratches;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jy
 */
class CountPerfTest {

    private static final int MAX = 500_000_000;
    private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(2);
    private final Object lock = new Object();
    private volatile int counter = 0;

    @Test
    void testWithTimer() {
        long start = System.currentTimeMillis();

//        testSingleThread(); // 3000ms

//        testSingleThreadWithLock(); // 8700ms

//        testSingleThreadWithCAS(); // 2581
//        testSingleThreadWithVolatile(); // 2800ms
        testTwoThreadsWithCAS();
//        testTwoThreadsWithLock();

        System.out.println("cost " + (System.currentTimeMillis() - start) + "ms");
    }

    private void testTwoThreadsWithLock() {
        CounterHolder counterHolder = new CounterHolder();
        for (int i = 0; i < MAX; i++) {
            synchronized (lock) {
                THREAD_POOL.execute(() -> counterHolder.i++);
            }
        }
    }

    private void testTwoThreadsWithCAS() {
        AtomicInteger counter = new AtomicInteger();

        try {
            CompletableFuture.runAsync(() -> {
                while (counter.incrementAndGet() == MAX) {
                    break;
                }
            }, THREAD_POOL).join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testSingleThreadWithVolatile() {
        for (int i = 0; i < MAX; i++) {
            counter++;
        }
    }

    private void testSingleThreadWithCAS() {
        AtomicInteger counter = new AtomicInteger();
        for (int i = 0; i < MAX; i++) {
            counter.incrementAndGet();
        }
    }

    private void testSingleThreadWithLock() {
        int i = 0;
        while (i < MAX) {
            synchronized (lock) {
                i++;
            }
        }
    }

    private void testSingleThread() {
        for (int i = 0; i < MAX; i++) {
        }
    }

    private static class CounterRunner implements Runnable {

        private final AtomicInteger counter;

        private CounterRunner(AtomicInteger counter) {
            this.counter = counter;
        }

        @Override
        public void run() {

        }
    }

    private static final class CounterHolder {
        private int i;
    }
}
