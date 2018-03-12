package me.jy.lang.thread.juc.lock;

/**
 * @author jy
 * @date 2018/03/11
 */
public interface Lock {

    void lock();

    void unlock();

    default int getThreadId() {
        return Long.valueOf(Thread.currentThread().getId()).intValue() - 10;
    }
}
