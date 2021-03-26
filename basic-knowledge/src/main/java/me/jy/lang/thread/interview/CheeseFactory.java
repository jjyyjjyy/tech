package me.jy.lang.thread.interview;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.LongAdder;

/**
 * 2份酵母粉+1份牛奶可以制作一份奶酪, 奶酪制作完成后可以存放到仓库里, 仓库的容量最大为1000.
 * 有货车从仓库里将奶酪运到超市, 每次运送100份奶酪.
 * 每天工厂最多制作100000份奶酪.
 *
 * @author jy
 */
public class CheeseFactory {

    private static final int MAX_MILK = 200_000;
    private static final int MAX_POWDER = 100_000;

    private final BlockingQueue<Integer> warehouse = new ArrayBlockingQueue<>(1000);

    public static void main(String[] args) {
        LongAdder soldCheeseCount = new LongAdder();
        CheeseFactory factory = new CheeseFactory();
        Thread t = new Thread(() -> {
            while (true) {
                if (factory.warehouse.size() < 100) {
                    continue;
                }
                int count = 0;
                while (count < 100 && factory.warehouse.poll() != null) {
                    count++;
                }
                soldCheeseCount.add(count);
                System.out.println("运送100奶酪到超市. 已送" + soldCheeseCount.sum() + ", 库存剩余" + factory.warehouse.size());
                if (soldCheeseCount.sum() >= 100_000) {
                    break;
                }
            }
        });
        t.start();
        CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            try {
                factory.warehouse.put(1);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        });
        new Thread(() -> {
            int milkCount = 0;
            while (milkCount < MAX_MILK) {
                milkCount++;
                milkCount++;
                try {
                    barrier.await();
                } catch (Exception e) {
                    System.err.println(e);
                    break;
                }
            }
        }).start();
        new Thread(() -> {
            int powder = 0;
            while (powder < MAX_POWDER) {
                powder++;
                try {
                    barrier.await();
                } catch (Exception e) {
                    System.err.println(e);
                    break;
                }
            }
        }).start();
    }
}
