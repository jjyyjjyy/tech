package me.jy.lang.thread.lock;

/**
 * @author jy
 */
public class LockOne implements Lock {

    private static final int LIMIT = 1;

    private final boolean[] flags = new boolean[LIMIT + 1];

    @Override
    public void lock() {
        int id = getThreadId();

        flags[id] = true;
        // 等待另一个线程释放锁. 两个线程同时执行到这会发生死锁
        while (flags[LIMIT - id]) ;
    }

    @Override
    public void unlock() {
        flags[getThreadId()] = false;
    }


}
