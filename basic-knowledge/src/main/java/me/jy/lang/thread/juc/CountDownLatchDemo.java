package me.jy.lang.thread.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jy
 * @date 2017/12/03
 */
public class CountDownLatchDemo {

    private static final int MAIN_THREAD_COUNT = 3;

    public static void main(String[] args) throws InterruptedException {
        // start main thread and do sth -> worker wait for main thread -> worker work -> work finished -> report
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch = new CountDownLatch(MAIN_THREAD_COUNT);

        Runnable work = () -> {
            try {

                System.out.println("wait for main thread then start work...");
                startLatch.await();
                System.out.println("doing work...");
                Thread.sleep(300);
                doneLatch.countDown();
            } catch (InterruptedException e) {
                return;
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(MAIN_THREAD_COUNT);

        for (int i = 0; i < MAIN_THREAD_COUNT; i++) {
            executorService.submit(work);
        }

        System.out.println("wake up!");
        System.out.println("main thread is doing sth else");
        Thread.sleep(2000);
        startLatch.countDown();
        doneLatch.await();
        System.out.println("work finished!");
        executorService.shutdownNow();
    }
}
