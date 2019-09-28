package me.jy.lang.thread.juc.lock;

import me.jy.lang.thread.juc.lock.spin.*;
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

    private static final int TEST_REPEAT_TIMES = 10;

    private static final int THREAD_NUM = Runtime.getRuntime().availableProcessors();

    @BeforeEach
    void reset() {
        Lock.resetThreadId();
    }

    @RepeatedTest(TEST_REPEAT_TIMES)
    void testPetersonLock() {
        Lock lock = new PetersonLock();
        LockTester lockTester = new LockTester(lock);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        verify(lockTester, executorService);
    }

    @RepeatedTest(TEST_REPEAT_TIMES)
    void testFilterLock() {
        Lock lock = new FilterLock(THREAD_NUM);
        LockTester lockTester = new LockTester(lock);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUM);
        verify(lockTester, executorService);
    }

    @RepeatedTest(TEST_REPEAT_TIMES)
    void testBakeryLock() {
        Lock lock = new BakeryLock(THREAD_NUM);
        LockTester lockTester = new LockTester(lock);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUM);
        verify(lockTester, executorService);
    }

    @RepeatedTest(TEST_REPEAT_TIMES)
    void testTASLock() {
        Lock lock = new TASLock();
        LockTester lockTester = new LockTester(lock);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUM);
        verify(lockTester, executorService);
    }

    @RepeatedTest(TEST_REPEAT_TIMES)
    void testTTASLock() {
        Lock lock = new TTASLock();
        LockTester lockTester = new LockTester(lock);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUM);
        verify(lockTester, executorService);
    }

    @RepeatedTest(TEST_REPEAT_TIMES)
    void testBackoffTTSLock() {
        Lock lock = new TTASBackoffLock(5, 100);
        LockTester lockTester = new LockTester(lock);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUM);
        verify(lockTester, executorService);
    }

    @RepeatedTest(TEST_REPEAT_TIMES)
    void testArrayLock() {
        Lock lock = new ArrayLock(64);
        LockTester lockTester = new LockTester(lock);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUM);
        verify(lockTester, executorService);
    }

    @RepeatedTest(TEST_REPEAT_TIMES)
    void testCLHLock() {
        Lock lock = new CLHLock();
        LockTester lockTester = new LockTester(lock);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUM);
        verify(lockTester, executorService);
    }

    @RepeatedTest(TEST_REPEAT_TIMES)
    void testMCSLock() {
        Lock lock = new MCSLock();
        LockTester lockTester = new LockTester(lock);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
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
