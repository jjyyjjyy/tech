package me.jy.lang.thread;

/**
 * @author jy
 */
public class SynchronizedDemo {

    private final Object lock = new Object();

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
