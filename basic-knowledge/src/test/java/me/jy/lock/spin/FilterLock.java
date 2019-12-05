package me.jy.lock.spin;

import me.jy.lock.Lock;

import java.util.stream.IntStream;

/**
 * 过滤锁
 *
 * @author jy
 */
public class FilterLock implements Lock {

    // 线程数
    private int n;

    private int[] level;

    private int[] victim;

    public FilterLock(int n) {
        this.n = n;
        this.level = new int[n];
        this.victim = new int[n];
    }

    @Override
    public void lock() {
        int me = getThreadId();
        for (int i = 1; i < n; i++) {
            level[me] = i; // 表示当前线程正在竞争临界区, 阻塞其他线程
            victim[i] = me; // 让其他线程通过临界区
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
