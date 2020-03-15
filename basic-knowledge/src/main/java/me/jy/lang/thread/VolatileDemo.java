package me.jy.lang.thread;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author jy
 */
public class VolatileDemo {

    private static boolean run = true;
    private boolean ready = false;
    private int a;

    @SneakyThrows
    private static void testVisibility() {
        Thread thread = new Thread(() -> {
            while (run) {
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);

        run = false;
        thread.join();
    }

    private static void testReOrder() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100_000; i++) {
            VolatileDemo volatileDemo = new VolatileDemo();
            executorService.execute(volatileDemo::testReOrder1);
            executorService.execute(volatileDemo::testReOrder2);
        }
    }

    public static void main(String[] args) {
        testReOrder();
    }

    private void testReOrder1() {
        int b = -1;
        if (ready) {
            b = a + a;
        }
        if (b == 0) {
            System.err.println(b);
        }
    }

    private void testReOrder2() {
        a = 2;
        ready = true;
    }
}
