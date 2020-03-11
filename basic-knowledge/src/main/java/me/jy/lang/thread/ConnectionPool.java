package me.jy.lang.thread;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author jy
 */
class ConnectionPool {

    private final int poolSize;

    private final AtomicIntegerArray stats;

    private final Connection[] connections;

    ConnectionPool(int poolSize) {
        this.poolSize = poolSize;
        this.stats = new AtomicIntegerArray(poolSize);
        this.connections = new Connection[poolSize];
        for (int i = 0; i < poolSize; i++) {
            this.connections[i] = new Connection();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConnectionPool pool = new ConnectionPool(5);

        int threadCount = 20;
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(() -> {
                Connection connection = pool.borrow();
                connection.connect();
                try {
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
                } catch (InterruptedException ignored) {
                }
                pool.free(connection);
            });
            threads[i] = thread;
            thread.start();
        }

        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
        }

    }

    public Connection borrow() {
        while (true) {
            for (int i = 0; i < poolSize; i++) {
                if (stats.get(i) == 0) {
                    if (stats.compareAndSet(i, 0, 1)) {
                        return connections[i];
                    }
                }
            }
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }
        }
    }

    public void free(Connection connection) {
        for (int i = 0; i < poolSize; i++) {
            if (this.connections[i] == connection) {
                this.stats.compareAndSet(i, 1, 0);
                synchronized (this) {
                    this.notify();
                }
                break;
            }
        }
    }

    private static class Connection {

        public void connect() {
            System.out.println(Thread.currentThread().getName() + " connected.");
        }
    }
}
