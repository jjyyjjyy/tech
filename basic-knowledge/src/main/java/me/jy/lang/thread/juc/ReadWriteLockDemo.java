package me.jy.lang.thread.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author jy
 * @date 2018/02/25
 */
public class ReadWriteLockDemo {

    private static final ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock();

    private final Lock readLock = LOCK.readLock();
    private final Lock writeLock = LOCK.writeLock();


    public void r() {
        doLock(readLock);
    }

    public void w() {
        doLock(writeLock);
    }

    private void doLock(Lock lock) {
        lock.lock();
        try {
            System.out.println(lock.getClass().getSimpleName() + " in");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockDemo test = new ReadWriteLockDemo();
        new Thread(test::r).start();
        new Thread(test::w).start();
        new Thread(test::r).start();
        new Thread(test::r).start();
        new Thread(test::w).start();
    }

}
