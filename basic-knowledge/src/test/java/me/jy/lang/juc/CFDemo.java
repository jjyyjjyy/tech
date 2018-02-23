package me.jy.lang.juc;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author jy
 * @date 2018/02/23
 */
public class CFDemo {

    public static void main(String[] args) throws InterruptedException {

        AtomicBoolean flag = new AtomicBoolean(Boolean.TRUE);

        CountDownLatch latch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            int id = i;
            CompletableFuture.runAsync(() -> {
                boolean check = new ServiceChecker().check((long) id);
                if (!check) {
                    flag.compareAndSet(Boolean.TRUE, Boolean.FALSE);
                }
                latch.countDown();
            });
        }

        latch.await();
        System.out.println(flag);
    }

    private static class ServiceChecker implements Checker {
    }

    private interface Checker {

        default boolean check(Long id) {
            Random random = new Random();
            try {
                Thread.sleep((long) random.nextInt(5000));
            } catch (InterruptedException e) {
                return false;
            }
            System.out.println(id + " check over");
            return random.nextInt(10) > 5;
        }
    }
}
