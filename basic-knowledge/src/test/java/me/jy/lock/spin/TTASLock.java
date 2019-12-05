package me.jy.lock.spin;

import me.jy.lock.Lock;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author jy
 */
public class TTASLock implements Lock {

    private static final AtomicBoolean mutex = new AtomicBoolean();

    @Override
    public void lock() {
        while (true) {
            while (mutex.get()) ; // get为true说明有其他线程已经进入临界区
            if (!mutex.getAndSet(true)) { // get为false说明可以进入临界区
                return;
            }
        }
    }

    @Override
    public void unlock() {
        mutex.set(false);
    }
}
