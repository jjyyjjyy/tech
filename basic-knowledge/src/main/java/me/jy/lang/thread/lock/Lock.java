package me.jy.lang.thread.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jy
 */
public interface Lock {

    ThreadLocal<Integer> THREAD_ID_HOLDER = new InheritableThreadLocal<>();

    AtomicInteger THREAD_ID_INCREMENTER = new AtomicInteger();

    void lock();

    void unlock();

    // 假设ThreadId从0开始递增
    default int getThreadId() {
        if (THREAD_ID_HOLDER.get() == null) {
            THREAD_ID_HOLDER.set(THREAD_ID_INCREMENTER.getAndIncrement());
        }
        return THREAD_ID_HOLDER.get();
    }

    static void resetThreadId() {
        THREAD_ID_INCREMENTER.set(0);
    }
}
