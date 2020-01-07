package me.jy.lang.thread.lock.spin;

import me.jy.lang.thread.lock.Lock;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author jy
 */
public class TASLock implements Lock {

    private static final AtomicBoolean mutex = new AtomicBoolean();

    @Override
    public void lock() {
        while (mutex.getAndSet(true)) ;
    }

    @Override
    public void unlock() {
        mutex.set(false);
    }
}
