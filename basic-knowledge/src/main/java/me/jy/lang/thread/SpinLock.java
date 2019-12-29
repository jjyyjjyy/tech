package me.jy.lang.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

/**
 * @author jy
 */
public class SpinLock {

    private static final AtomicReference<Thread> LOCK_OBJECT = new AtomicReference<>();

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        SpinLock lock = new SpinLock();

        IntStream.rangeClosed(1, 1000)
            .parallel()
            .forEach(i -> {
                lock.lock();
                list.add(i);
                lock.unlock();
            });

        System.out.println(list.size());
    }

    public void lock() {
        Thread currentThread = Thread.currentThread();
        while (!LOCK_OBJECT.compareAndSet(null, currentThread)) ;
    }

    public void unlock() {
        LOCK_OBJECT.set(null);
    }
}
