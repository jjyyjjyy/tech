package me.jy.lang.thread.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author jy
 * @date 2017/12/03
 */
public class ExecutorServiceDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<String> result = es.submit(() -> {
            Thread.sleep(5000);
            return "Hello";
        });
        System.out.println(1);
        System.out.println(result.get()); // await
        System.out.println(2);
        es.shutdown();
    }
}
