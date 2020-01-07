package me.jy.lang.thread.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author jy
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<>(100);
        // queue1.add(null); // java.lang.NullPointerException

        // exception
        // queue1.remove(); // java.util.NoSuchElementException
        for (int i = 0; i < 100; i++) {
            queue1.add(i);
        }
        // queue1.add(1); // java.lang.IllegalStateException: Queue full

        BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<>(100);
        // special value
        System.out.println(queue2.poll()); // null
        for (int i = 0; i < 100; i++) {
            queue2.offer(i);
        }
        System.out.println(queue2.offer(1)); // false

        BlockingQueue<Integer> queue3 = new ArrayBlockingQueue<>(100);

        CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            try {
                Integer take = queue3.take();// block util at least one element added.
                System.out.println(take);
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2L);
                queue3.put(1);
                System.out.println("put 1");
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        latch.await();
        System.out.println("main thread...");

    }
}
