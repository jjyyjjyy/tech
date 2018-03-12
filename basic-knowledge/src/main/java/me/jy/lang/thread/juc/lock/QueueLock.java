package me.jy.lang.thread.juc.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jy
 * @date 2018/03/12
 */
public class QueueLock implements Lock {

    private AtomicInteger slot = new AtomicInteger(0);

    private ThreadLocal<Integer> slotIndex = ThreadLocal.withInitial(() -> 0);

    private final boolean[] flags;

    private final int capacity;

    public QueueLock(int capacity) {
        this.capacity = capacity;
        this.flags = new boolean[capacity];
        flags[0] = true;
    }

    @Override
    public void lock() {

        int index = slot.getAndIncrement() % capacity;

        slotIndex.set(index);

        while (!flags[index]) {

        }
    }

    @Override
    public void unlock() {

        int index = slot.getAndIncrement() % capacity;
        flags[index] = false;
        flags[(slot.getAndIncrement() + 1) % capacity] = true;
    }
}
