package me.jy.lang.juc;

import org.junit.Test;

import java.util.concurrent.*;

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

    @Test
    public void testFutureException() throws ExecutionException, InterruptedException {
        Future<Object> future = pool.submit(() -> {
            Thread.sleep(2200);
            ex();
            return 1;
        });
        System.out.println(future.get());
    }

    @Test
    public void testCFException() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> cf = new CompletableFuture<>();
        new Thread(() -> {
            try {
                Thread.sleep(2222);
                ex();
            } catch (InterruptedException e) {
                e.printStackTrace();
                cf.completeExceptionally(e);
            }
            cf.complete(22);
        }).start();
        // wait permanently
        System.out.println(cf.get());
    }

    private void ex() {
        throw new RuntimeException();
    }

}
