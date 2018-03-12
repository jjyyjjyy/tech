package me.jy.lang.thread.juc.lock;

/**
 * @author jy
 * @date 2018/03/11
 */
public class LockOne implements Lock {

    private static final int LIMIT = 1;

    private final boolean[] flags = new boolean[LIMIT + 1];

    @Override
    public void lock() {
        int id = getThreadId();

        flags[id] = true;
        while (flags[LIMIT - id]) { // wait for another one unlock,dead lock causes when threads come here at same time
        }
    }

    @Override
    public void unlock() {
        flags[getThreadId()] = false;
    }


}
