package me.jy.lang.thread.juc;

import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

/**
 * @author jy
 */
public class StampedLockDemo {

    private final StampedLock lock = new StampedLock();

    private double x;
    private double y;

    public static void main(String[] args) {
        StampedLockDemo stampedLockDemo = new StampedLockDemo();
        IntStream.rangeClosed(1, 100)
            .parallel()
            .forEach(i -> stampedLockDemo.move(i, i + 1));
        // 7212
        System.out.println(stampedLockDemo.computeDistance());
    }

    public void move(double deltaX, double deltaY) {
        // 获取写锁
        long stamp = lock.writeLock();
        x += deltaX;
        y += deltaY;
        // 释放写锁
        lock.unlockWrite(stamp);
    }

    public double computeDistance() {
        // 尝试获取读锁(乐观锁)
        long stamp = lock.tryOptimisticRead();
        // 操作数据
        double currentX = x;
        double currentY = y;
        // 校验是否有线程获取过写锁
        if (!lock.validate(stamp)) {
            // 重新获取读锁(悲观锁)
            stamp = lock.readLock();
            currentX = x;
            currentY = y;
            // 释放锁
            lock.unlockRead(stamp);
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

}
