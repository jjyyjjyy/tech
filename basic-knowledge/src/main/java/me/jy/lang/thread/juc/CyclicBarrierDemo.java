package me.jy.lang.thread.juc;

import java.util.concurrent.CyclicBarrier;

/**
 * @author jy
 * @date 2017/12/03
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            new Runner(cyclicBarrier).start();
        }
    }


    private static class Runner extends Thread {

        private final CyclicBarrier cyclicBarrier;

        private Runner(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is running...");
            try {
                Thread.sleep(2000);
                this.cyclicBarrier.await();
                // everyone can drink diff wine, different with CountDownLatch
                System.out.println(Thread.currentThread().getName() + " drink wine...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
