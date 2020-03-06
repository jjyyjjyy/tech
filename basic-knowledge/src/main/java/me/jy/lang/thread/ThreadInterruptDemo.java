package me.jy.lang.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jy
 */
@Slf4j
public class ThreadInterruptDemo {

    public static void main(String[] args) throws InterruptedException {
//        interruptRunnable();
        interruptTimeWaiting();
    }

    private static void interruptTimeWaiting() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                log.debug("Interrupted: {}", Thread.currentThread().isInterrupted());
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                log.warn("Interrupted: {}", Thread.currentThread().isInterrupted());
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(1000L);
        thread.interrupt();
        log.debug("Interrupted: {}", thread.isInterrupted());

    }

    private static void interruptRunnable() {
        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            int count = 0;
            while (!Thread.interrupted()) {
                System.out.println(name + (count++));
            }
        };
        Thread th1 = new Thread(runnable, "r1=");
        Thread th2 = new Thread(runnable, "r2=");

        th1.start();
        th2.start();

        while (true) {
            double d = Math.random();
            if (d > 0.49999 && d < 0.500001) {
                break;
            }
        }
        th1.interrupt();
        th2.interrupt();
    }
}
