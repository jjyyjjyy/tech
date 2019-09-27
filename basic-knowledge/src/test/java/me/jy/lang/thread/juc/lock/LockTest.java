package me.jy.lang.thread.juc.lock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author jy
 */
class LockTest {

    @BeforeEach
    void reset() {
        Lock.resetThreadId();
    }

    @RepeatedTest(20)
    void testPetersonLock() {
        Lock lock = new PetersonLock();
        LockTester lockTester = new LockTester(lock);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        verify(lockTester, executorService);
    }

    @RepeatedTest(20)
    void testFilterLock() {
        Lock lock = new FilterLock(10);
        LockTester lockTester = new LockTester(lock);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        verify(lockTester, executorService);
    }

    @RepeatedTest(20)
    void testBakeryLock() {
        Lock lock = new BakeryLock(10);
        LockTester lockTester = new LockTester(lock);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        verify(lockTester, executorService);
    }

    private void verify(LockTester tester, ExecutorService executorService) {

        int times = 100_000;

        List<CompletableFuture<Void>> tasks = IntStream.range(0, times)
            .mapToObj(i -> CompletableFuture.runAsync(tester::addValue, executorService))
            .collect(Collectors.toList());
        tasks.stream().map(CompletableFuture::join).collect(Collectors.toList());

        Assertions.assertEquals(times, tester.getValue());
        executorService.shutdown();
    }

    private static class LockTester {

        private final Lock lock;
        private int value;

        private LockTester(Lock lock) {
            this.lock = lock;
        }

        private void addValue() {
            lock.lock();
            value++;
            lock.unlock();
        }

        private int getValue() {
            return value;
        }
    }

}
