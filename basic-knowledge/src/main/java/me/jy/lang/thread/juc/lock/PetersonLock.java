package me.jy.lang.thread.juc.lock;

/**
 * @author jy
 */
public class PetersonLock implements Lock {

    private static final int LIMIT = 1;

    private final boolean[] flags = new boolean[LIMIT + 1];

    private int victim = LIMIT;

    @Override
    public void lock() {
        int id = getThreadId();
        flags[id] = true;
        victim = id;
        while (flags[LIMIT - id] && victim == id) ;
    }

    @Override
    public void unlock() {
        flags[getThreadId()] = false;
    }
}
