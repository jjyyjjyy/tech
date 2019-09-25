package me.jy.lang.thread.juc.lock;

import java.util.stream.IntStream;

/**
 * 过滤锁
 *
 * @author jy
 */
public class FilterLock implements Lock {

    // 线程数
    private int n;

    private int level[];

    private int victim[];

    public FilterLock(int n) {
        this.n = n;
        this.level = new int[n];
        this.victim = new int[n];
    }

    @Override
    public void lock() {
        int me = getThreadId();
        for (int i = 1; i < n; i++) {
            level[me] = i;
            victim[i] = me;
            int aliasI = i;
            while (victim[i] == me && IntStream.range(0, n).filter(idx -> idx != me).map(k -> level[k]).anyMatch(l -> l >= aliasI))
                ;
        }
    }

    @Override
    public void unlock() {
        level[getThreadId()] = 0;
    }
}
