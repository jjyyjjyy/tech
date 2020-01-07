package me.jy.lang.thread.lock.spin;

import me.jy.lang.thread.lock.Backoff;
import me.jy.lang.thread.lock.Lock;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author jy
 */
public class TTASBackoffLock implements Lock {

    private static final AtomicBoolean mutex = new AtomicBoolean();

    private final Backoff backoff;

    public TTASBackoffLock(int minDelay, int maxDelay) {
        this.backoff = new Backoff(minDelay, maxDelay);
    }

    @Override
    public void lock() {
        while (true) {
            while (mutex.get()) ;
            if (!mutex.getAndSet(true)) {
                return;
            } else {
                backoff.backoff(); // 退让一段时间再去争用锁
            }
        }
    }

    @Override
    public void unlock() {
        mutex.set(false);
    }
}
