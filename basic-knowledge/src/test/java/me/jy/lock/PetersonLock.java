package me.jy.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Peterson锁, 实现双线程互斥
 *
 * @author jy
 */
public class PetersonLock implements Lock {

    private static final int LIMIT = 1;

    private final boolean[] flags = new boolean[LIMIT + 1];

    private AtomicInteger victim = new AtomicInteger();

    @Override
    public void lock() {
        int id = getThreadId();
        flags[id] = true; // 表示当前线程正在竞争临界区
        victim.set(id); // 让其他正在竞争临界区的线程通过
        while (flags[LIMIT - id] && victim.get() == id) ;
    }

    @Override
    public void unlock() {
        flags[getThreadId()] = false;
    }
}
