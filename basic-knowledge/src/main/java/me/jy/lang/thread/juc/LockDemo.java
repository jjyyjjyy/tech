package me.jy.lang.thread.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.stream.IntStream;

/**
 * @author jy
 */
public class LockDemo implements Lock {

    private final SyncDemo sync = new SyncDemo();

    public static void main(String[] args) {
        TestCase testCase = new TestCase();

        LockDemo lock = new LockDemo();

        IntStream.rangeClosed(1, 1_000_000)
            .parallel()
            .forEach(i -> {
                lock.lock();
                testCase.add(1);
                lock.unlock();
            });
        System.out.println(testCase.get() == 1_000_000);
    }

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public static class SyncDemo extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        public AbstractQueuedSynchronizer.ConditionObject newCondition() {
            return new ConditionObject();
        }
    }

    private static class TestCase {
        private int i;

        public void add(int adder) {
            i += adder;
        }

        public int get() {
            return i;
        }
    }
}
