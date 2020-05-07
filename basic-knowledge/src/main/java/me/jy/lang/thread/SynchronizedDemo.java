package me.jy.lang.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jy
 */
@Slf4j
public class SynchronizedDemo {

    private static final Object lock = new Object();

    private static Thread t1, t2, t3;

    private static void printInOrder01() {
        new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("2");
            }
        }).start();
        new Thread(() -> {
            synchronized (lock) {
                log.info("1");
                lock.notify();
            }
        }).start();
    }

    private static void printInOrder02() {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            log.info("2");
        });
        new Thread(() -> {
            log.info("1");
            LockSupport.unpark(t1);
        }).start();

        t1.start();
    }

    private static void printInOrder03() {
        int times = 5;
        t1 = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                LockSupport.park();
                log.info("a");
                LockSupport.unpark(t2);
            }
        });
        t2 = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                LockSupport.park();
                log.info("b");
                LockSupport.unpark(t3);
            }
        });
        t3 = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                LockSupport.park();
                log.info("c");
                LockSupport.unpark(t1);
            }
        });
        t1.start();
        t2.start();
        t3.start();

        LockSupport.unpark(t1);
    }

    private static void printInOrder04() {
        Lock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        Condition c3 = lock.newCondition();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    lock.lock();
                    c1.await();
                    log.info("a");
                    c2.signal();
                } catch (InterruptedException ignored) {
                } finally {
                    lock.unlock();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    lock.lock();
                    c2.await();
                    log.info("b");
                    c3.signal();
                } catch (InterruptedException ignored) {
                } finally {
                    lock.unlock();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    lock.lock();
                    c3.await();
                    log.info("c");
                    c1.signal();
                } catch (InterruptedException ignored) {
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        lock.lock();
        c1.signal();
        lock.unlock();
    }

    private static void printInOrder05() {

        AtomicInteger flag = new AtomicInteger(1);

        new Thread(() -> {
            synchronized (lock) {

                for (int i = 0; i < 5; i++) {
                    while (flag.get() != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException ignored) {
                        }
                    }
                    log.info("a");
                    flag.set(2);
                    lock.notifyAll();
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (lock) {

                for (int i = 0; i < 5; i++) {
                    while (flag.get() != 2) {
                        try {
                            lock.wait();
                        } catch (InterruptedException ignored) {
                        }
                    }
                    log.info("b");
                    flag.set(3);
                    lock.notifyAll();
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (lock) {

                for (int i = 0; i < 5; i++) {
                    while (flag.get() != 3) {
                        try {
                            lock.wait();
                        } catch (InterruptedException ignored) {
                        }
                    }
                    log.info("c");
                    flag.set(1);
                    lock.notifyAll();
                }
            }
        }).start();

    }

    public static void main(String[] args) {
        printInOrder01();
    }

    public synchronized void m1() {

    }

    public void m2() {
        synchronized (lock) {

        }
    }

    public void m3() {
        synchronized (SynchronizedDemo.class) {

        }
    }

}
