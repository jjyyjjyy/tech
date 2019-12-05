package me.jy.lock.spin;

import me.jy.lock.Lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @author jy
 */
public class ArrayLock implements Lock {

    private final ThreadLocal<Integer> slot = ThreadLocal.withInitial(() -> 0);
    private final int size;
    private AtomicInteger tail = new AtomicInteger();
    private AtomicReferenceArray<Boolean> flags;

    public ArrayLock(int size) {
        this.size = size;
        Boolean[] booleans = new Boolean[size];
        for (int i = 1; i < size; i++) {
            booleans[i] = false; // false用来阻塞该线程
        }
        booleans[0] = true; // 默认第一个线程可以直接获得锁
        this.flags = new AtomicReferenceArray<>(booleans);
    }

    @Override
    public void lock() {
        int idx = tail.getAndIncrement() % size;
        slot.set(idx);
        while (!flags.get(idx)) ; // 等待上一个线程释放锁
    }

    @Override
    public void unlock() {
        Integer idx = this.slot.get();
        flags.set(idx % size, false); // 重置标志位
        flags.set((idx + 1) % size, true); // 释放下一个slot线程
    }
}
