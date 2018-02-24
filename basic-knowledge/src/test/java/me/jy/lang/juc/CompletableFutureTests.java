package me.jy.lang.juc;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author jy
 * @date 2018/02/24
 */
public class CompletableFutureTests extends TestTaskBase {

    @Test
    public void testBlock() {
        work();
    }

    @Test
    public void testPool() {
        Callable<Integer> task = this::workExceptionally;
        Integer result = null;
        try {
            result = pool.submit(task).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(result);

    }

    @Test
    public void testSupplyAsAsync() {
        CompletableFuture<Integer> future = buildCF();
        try {
            Integer integer = future.get();
            System.out.println(integer);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAccept() {
        CompletableFuture<Integer> future = buildCF();
        future.thenAccept(System.out::println);
    }

    @Test
    public void test() throws InterruptedException {
        LinkedBlockingDeque<Integer> deque = new LinkedBlockingDeque<>();
        deque.take(); // block here

    }

}
