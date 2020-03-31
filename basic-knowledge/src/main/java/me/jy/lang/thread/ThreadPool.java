package me.jy.lang.thread;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jy
 */
@Slf4j
class ThreadPool {

    private final int coreSize;

    private final int maxPoolSize;

    private final long timeout;

    private final TimeUnit timeUnit;

    private final BlockingQueue<Runnable> queue;

    private final RejectPolicy rejectPolicy;

    private final Set<Worker> workers = new HashSet<>();

    public ThreadPool() {
        this(5, 10, 3, TimeUnit.SECONDS, NormalRejectPolicy.DISCARD);
    }

    public ThreadPool(int coreSize, int maxPoolSize, long timeout, TimeUnit timeUnit, RejectPolicy rejectPolicy) {
        this.coreSize = Math.max(1, coreSize);
        this.maxPoolSize = Math.max(2, maxPoolSize);
        this.queue = new BlockingQueue<>(this.maxPoolSize);
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.rejectPolicy = rejectPolicy;
    }

    @SneakyThrows
    private static void testThreadPool() {
        ThreadPool pool = new ThreadPool(2, 5, 2, TimeUnit.SECONDS, NormalRejectPolicy.EXCEPTION);

        for (int i = 0; i < 100; i++) {
            pool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                } catch (Exception ignored) {
                }
                log.info("Executing...");
            });
        }

    }

    @SneakyThrows
    private static void testBlockingQueue() {
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

    public static void main(String[] args) {
        testThreadPool();
    }

    public void execute(Runnable task) {

        Worker worker = new Worker(task);

        synchronized (workers) {
            if (workers.size() < coreSize) {
                workers.add(worker);
                worker.start();
            } else {
                if (!queue.offer(worker, timeout, timeUnit)) {
                    rejectPolicy.handleReject(task, queue);
                }
            }
        }
    }

    private enum NormalRejectPolicy implements RejectPolicy {
        DISCARD,
        WAITING {
            @Override
            public void handleReject(Runnable task, BlockingQueue<Runnable> queue) {
                queue.add(task);
            }
        },
        EXCEPTION {
            @Override
            public void handleReject(Runnable task, BlockingQueue<Runnable> queue) {
                throw new RuntimeException(task.toString());
            }
        },
    }

    private interface RejectPolicy {
        default void handleReject(Runnable task, BlockingQueue<Runnable> queue) {
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

        public E poll(long timeout, TimeUnit timeUnit) {
            lock.lock();
            long countDown = timeUnit.toNanos(timeout);
            try {
                while (deque.isEmpty()) {
                    if (countDown <= 0) {
                        return null;
                    }
                    try {
                        countDown = emptyCondition.awaitNanos(countDown);
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

        public boolean offer(E e, long timeout, TimeUnit timeUnit) {
            lock.lock();
            long countDown = timeUnit.toNanos(timeout);
            try {
                while (deque.size() == capacity) {
                    if (countDown <= 0) {
                        return false;
                    }
                    try {
                        countDown = fullCondition.awaitNanos(countDown);
                    } catch (InterruptedException ignored) {
                    }
                }
                deque.addLast(e);
                emptyCondition.signal();
                return true;
            } finally {
                lock.unlock();
            }
        }
    }

    private class Worker extends Thread {

        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            while (task != null || (task = queue.poll(timeout, timeUnit)) != null) {
                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            synchronized (workers) {
                workers.remove(this);
            }
        }
    }
}
