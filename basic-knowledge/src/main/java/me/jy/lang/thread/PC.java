package me.jy.lang.thread;

/**
 * @author jy
 * @date 2017/12/02
 */
public class PC {

    public static void main(String[] args) {
        SharedService ss = new SharedService();
        Producer producer = new Producer(ss);
        Consumer consumer = new Consumer(ss);
        producer.start();
        consumer.start();
    }

    private static class SharedService {
        private volatile int capacity = 0;

        public synchronized void get() {
            while (true) {
                if (!getAvailable()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }
                capacity--;
                System.out.println("get... result=" + capacity);
                notifyAll();
            }
        }

        public synchronized void push() {
            while (true) {
                if (pushAvailable()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }
                capacity++;
                System.out.println("push... result=" + capacity);
                notifyAll();
            }
        }

        private boolean getAvailable() {
            return this.capacity > 10;
        }

        private boolean pushAvailable() {
            return this.capacity < 5;
        }
    }

    private static class Producer extends Thread {
        private final SharedService sharedService;

        private Producer(SharedService sharedService) {
            this.sharedService = sharedService;
        }

        public void run() {
            this.sharedService.push();
        }
    }

    private static class Consumer extends Thread {
        private final SharedService sharedService;

        private Consumer(SharedService sharedService) {
            this.sharedService = sharedService;
        }

        public void run() {
            this.sharedService.get();
        }
    }
}
