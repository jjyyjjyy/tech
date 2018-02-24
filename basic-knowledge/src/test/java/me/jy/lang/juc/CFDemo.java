package me.jy.lang.juc;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author jy
 * @date 2018/02/23
 */
public class CFDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {


        boolean result = CompletableFuture.supplyAsync(() -> new ServiceChecker().check())
                .thenCombineAsync(
                        CompletableFuture.supplyAsync(() -> new ServiceChecker().check()),
                        (a1, a2) -> a1 && a2)
                .thenCombineAsync(
                        CompletableFuture.supplyAsync(() -> new ServiceChecker().check()),
                        (a1, a2) -> a1 && a2)
                .get();
        System.out.println(result);
    }

    private static class ServiceChecker implements Checker {
    }

    private interface Checker {

        default boolean check() {
            Random random = new Random();
            try {
                Thread.sleep((long) random.nextInt(5000));
            } catch (InterruptedException e) {
                return false;
            }
            System.out.println(Thread.currentThread().getName() + " check over");
            return random.nextInt(10) > 5;
        }
    }
}
