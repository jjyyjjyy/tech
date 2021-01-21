package me.jy.lang.thread.juc;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.IntStream;

/**
 * @author jy
 */
public class ConcurrentLinkedQueueDemo {

    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        IntStream.rangeClosed(1, 10)
            .forEach(i -> {
                queue.offer(i);
                queue.remove();
            });
    }
}
