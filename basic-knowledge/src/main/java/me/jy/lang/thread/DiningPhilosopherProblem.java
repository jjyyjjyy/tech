package me.jy.lang.thread;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

/**
 * 哲学家就餐问题
 * 解决方法:
 * 1. 最多允许4个哲学家拿起筷子
 * 2. 哲学家先拿起编号小的筷子, 再拿起编号大的筷子.
 * 3. 给哲学家设置一个等待筷子拿起的最长时间.
 *
 * @author jy
 */
public class DiningPhilosopherProblem {

    private final Semaphore[] chopsticks;

    public DiningPhilosopherProblem(int num) {
        this.chopsticks = IntStream.range(0, num)
            .mapToObj(i -> new Semaphore(1))
            .toArray(Semaphore[]::new);
    }

    public static void main(String[] args) {
        int num = 5;
        DiningPhilosopherProblem dpp = new DiningPhilosopherProblem(num);
        Philosopher[] philosophers = new Philosopher[num];
        for (int i = 0; i < num; i++) {
            philosophers[i] = new Philosopher(dpp.getChopsticks(), i);
        }

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < num; i++) {
            executorService.execute(philosophers[i]);
        }
    }

    public Semaphore[] getChopsticks() {
        return chopsticks;
    }

    public static class Philosopher implements Runnable {

        private final int no;

        private final int leftNo;
        private final int rightNo;

        private final Semaphore[] chopsticks;

        public Philosopher(Semaphore[] chopsticks, int no) {
            this.chopsticks = chopsticks;
            this.no = no;
            int leftNo = no;
            int rightNo = (no + 1) % chopsticks.length;
            this.leftNo = Math.min(leftNo, rightNo);
            this.rightNo = Math.max(leftNo, rightNo);
        }

        @Override
        @SneakyThrows
        public void run() {
            while (true) {
                chopsticks[leftNo].acquire();
                System.out.println(leftNo);
                chopsticks[rightNo].acquire();
                eating();
                chopsticks[leftNo].release();
                chopsticks[rightNo].release();
                thinking();
            }
        }

        @SneakyThrows
        private void eating() {
            System.out.println("Philosopher " + no + " is eating...");
        }

        @SneakyThrows
        public void thinking() {
            System.out.println("Philosopher " + no + " is thinking...");
        }

    }

}
