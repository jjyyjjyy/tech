package me.jy.lang.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author jy
 */
@Slf4j
public class ThreadYieldDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {
                Thread.yield();
                log.info("Runnable after yield"); // reachable
            }
        });
        thread.start();

        TimeUnit.SECONDS.sleep(2);

        thread.interrupt();

    }
}
