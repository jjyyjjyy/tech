package me.jy.lang.thread.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jy
 */
public class ReentrantLockDemo {

    private final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        ReentrantLockDemo lockDemo = new ReentrantLockDemo();
        new Thread(lockDemo::synchronizedMethod).start();
        new Thread(lockDemo::synchronizedMethod).start();
    }

    public void synchronizedMethod() {
        lock.lock();
        try {
            TimeUnit.HOURS.sleep(1L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
