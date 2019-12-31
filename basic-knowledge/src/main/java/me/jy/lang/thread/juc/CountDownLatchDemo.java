package me.jy.lang.thread.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch 倒计式锁demo
 * <p>
 * 作用: 某一个线程可以等待其他线程都完成后才执行任务.
 *
 * @author jy
 */

/**
 * 11:51:29.761 [pool-1-thread-1] INFO me.jy.lang.thread.juc.CountDownLatchDemo - working
 * 11:51:29.761 [pool-1-thread-3] INFO me.jy.lang.thread.juc.CountDownLatchDemo - working
 * 11:51:29.761 [pool-1-thread-2] INFO me.jy.lang.thread.juc.CountDownLatchDemo - working
 * 11:51:32.768 [main] INFO me.jy.lang.thread.juc.CountDownLatchDemo - workers done!
 */
@Slf4j
public class CountDownLatchDemo {

    private static final int MAIN_THREAD_COUNT = 3;

    private static final ExecutorService executorService = Executors.newFixedThreadPool(MAIN_THREAD_COUNT);

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(MAIN_THREAD_COUNT);


        for (int i = 1; i <= MAIN_THREAD_COUNT; i++) {
            int tmp = i;
            executorService.submit(() -> {
                try {
                    log.info("working");
                    TimeUnit.SECONDS.sleep(tmp);
                    latch.countDown();
                } catch (InterruptedException ingored) {
                }
            });
        }

        latch.await();
        log.info("workers done!");
        executorService.shutdownNow();
    }
}
