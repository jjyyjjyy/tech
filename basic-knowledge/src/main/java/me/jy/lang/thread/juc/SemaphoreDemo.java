package me.jy.lang.thread.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author jy
 */
@Slf4j
public class SemaphoreDemo {

    private static final Semaphore SEMAPHORE = new Semaphore(5);

    public static void main(String[] args) {
        IntStream
            .rangeClosed(1, 100)
            .parallel()
            .forEach(i -> {
                try {
                    SEMAPHORE.acquire();
                    log.info("I am in.");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ignored) {
                } finally {
                    SEMAPHORE.release();
                }
            });
    }
}
