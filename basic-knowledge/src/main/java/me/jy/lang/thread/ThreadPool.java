package me.jy.lang.thread;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jy
 */
class ThreadPool {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> bq = new BlockingQueue<>(5);

        int threadCount = 20;
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            int j = i;
            Thread thread = new Thread(() -> {
                bq.add(j);
                try {
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
                } catch (InterruptedException ignored) {
                }
                System.out.println(bq.take());
            });
            threads[i] = thread;
            thread.start();
        }

        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
        }
    }

    private static class BlockingQueue<E> {

        private final int capacity;
        private final Deque<E> deque;

        private Lock lock = new ReentrantLock();

        private Condition emptyCondition = lock.newCondition();

        private Condition fullCondition = lock.newCondition();

        private BlockingQueue(int capacity) {
            this.capacity = capacity;
            this.deque = new ArrayDeque<>(capacity);
        }

        public E take() {
            lock.lock();
            try {
                while (deque.isEmpty()) {
                    try {
                        emptyCondition.await();
                    } catch (InterruptedException e) {
                        return null;
                    }
                }
                E e = deque.removeFirst();
                fullCondition.signal();
                return e;
            } finally {
                lock.unlock();
            }
        }

        public void add(E e) {
            lock.lock();
            try {
                while (deque.size() == capacity) {
                    try {
                        fullCondition.await();
                    } catch (InterruptedException ignored) {
                    }
                }
                deque.addLast(e);
                emptyCondition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
