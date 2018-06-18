package me.jy.lang.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jy
 * @date 2018/02/23
 */
public class ProductConsumerDemo {

    public static void main(String[] args) {

        Repository repository = new Repository();
        new Thread(() -> repository.push(1)).start();
        new Thread(() -> repository.push(1)).start();
        new Thread(() -> repository.take(1)).start();
    }

    private static class Repository {

        private volatile int capacity;

        private Lock lock = new ReentrantLock();

        private Condition fullCondition = lock.newCondition();

        private Condition emptyCondition = lock.newCondition();

        public void push(int size) {

            while (true) {
                lock.lock();
                if (isFull()) {
                    try {
                        fullCondition.await();
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                capacity += size;
                System.out.println("increment to :" + capacity);
                emptyCondition.signalAll();
            }

            lock.unlock();
        }

        public void take(int size) {
            while (true) {
                lock.lock();
                if (isEmpty()) {
                    try {
                        emptyCondition.await();
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                capacity -= size;
                System.out.println("decrement to :" + capacity);
                fullCondition.signalAll();
            }
            capacity -= size;
            lock.unlock();
        }

        public boolean isEmpty() {
            return capacity <= 0;
        }

        public boolean isFull() {
            return capacity > 10;
        }
    }
}