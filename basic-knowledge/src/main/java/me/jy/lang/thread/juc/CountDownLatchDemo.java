package me.jy.lang.thread.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author jy
 */
@Slf4j
public class CountDownLatchDemo {

    private static final int WORKER_COUNT = 10;

    private static final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(WORKER_COUNT);
        Runnable work = () -> {
            try {
                log.info("working");
                TimeUnit.SECONDS.sleep(1);
                latch.countDown();
            } catch (InterruptedException ignored) {
            }
        };
        IntStream.rangeClosed(1, WORKER_COUNT).forEach(i -> executorService.execute(work));
        latch.await();

        log.info("workers done!");
        executorService.shutdownNow();
    }
}
