package me.jy.lang.juc;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jy
 * @date 2018/02/24
 */
public class ExecutorsDemo {

    @Test
    public void testEx() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            System.out.println(Thread.currentThread().getName()); // 1
            throw new RuntimeException();
        });
        executor.execute(() -> {
            System.out.println(Thread.currentThread().getName()); // 2
            System.out.println(111);
        });

        executor.shutdown();
    }

    @Test
    public void testCFEx() {
        CompletableFuture<Integer> cf = new CompletableFuture<>();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
                int result = 1 / 0;
            } catch (Exception e) {
                cf.completeExceptionally(e);
            }
            cf.complete(1);

        }).start();
        Integer integer = null;
        try {
            integer = cf.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(integer);
    }

    @Test
    public void testCFSupply() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1222);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 111;
        });
        Integer integer = future.get();
        System.out.println(integer);
    }
}
