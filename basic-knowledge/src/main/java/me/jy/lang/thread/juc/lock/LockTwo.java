package me.jy.lang.thread.juc.lock;

/**
 * @author jy
 */
public class LockTwo implements Lock {

    private int victim;

    @Override
    public void lock() {
        int i = getThreadId();
        victim = i;
        // 第一个线程会一直阻塞在这里, 直到第二个线程执行
        while (victim == i) ;
    }

    @Override
    public void unlock() {
        victim = getThreadId();
    }
}
