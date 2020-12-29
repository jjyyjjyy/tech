package me.jy.lang.thread.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 测试ReentrantReadWriteLock重入锁次数最大上限.
 * -Xss10m
 *
 * @author jy
 */
public class ReadWriteLockDemo {

    private static final ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock();
    private final Lock readLock = LOCK.readLock();
    private final Lock writeLock = LOCK.writeLock();

    public static void main(String[] args) {
        ReadWriteLockDemo test = new ReadWriteLockDemo();
        new Thread(() -> test.lock(2 ^ 16 + 1)).run();
    }

    public void lock(int n) {
        writeLock.lock();
        if (n > 0) {
            lock(n--);
            writeLock.unlock();
        }
    }
}
