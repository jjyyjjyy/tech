package me.jy.lang.thread.juc.lock.spin;

import me.jy.lang.thread.juc.lock.Lock;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

/**
 * Bakery锁, 先来先服务
 *
 * @author jy
 */
public class BakeryLock implements Lock {

    private AtomicBoolean[] flag;
    private int[] label;

    public BakeryLock(int n) {
        this.flag = IntStream.range(0, n).mapToObj(i -> new AtomicBoolean()).toArray(AtomicBoolean[]::new);
        this.label = new int[n];
    }

    @Override
    public void lock() {
        int i = getThreadId();

        flag[i].set(true); // 表示该线程正在竞争临界区
        label[i] = Arrays.stream(label).max().getAsInt() + 1;
        flag[i].set(false);

        while (IntStream.range(0, flag.length)
            .filter(j -> i != j)
            .anyMatch(j -> flag[j].get() // 其他线程还在设置label值
                || label[j] > 0 // 其他线程正在竞争临界区
                && (label[j] < label[i] || Objects.equals(label[i], label[j]) && j < i))) // 比较优先级
            ;
    }

    @Override
    public void unlock() {
        label[getThreadId()] = 0;
    }
}
