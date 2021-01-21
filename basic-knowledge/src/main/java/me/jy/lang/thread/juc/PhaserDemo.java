package me.jy.lang.thread.juc;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author jy
 */
@Slf4j
public class PhaserDemo {

    private static final int PARTIES = 10;
    private static final Phaser PHASER = new Phaser(PARTIES);

    public static void main(String[] args) {
        PhaserDemo demo = new PhaserDemo();
        IntStream.rangeClosed(1, PARTIES)
            .parallel()
            .forEach(i -> new Thread(demo::countDownLatch).start());
        // 模拟CountDownLatch
        // 等待parties减到0
        PHASER.awaitAdvance(0);
        log.info("awaitAdvance passed");

        // 模拟CyclicBarrier
        IntStream.rangeClosed(1, PARTIES)
            .parallel()
            .forEach(i -> new Thread(demo::cyclicBarrier).start());
    }

    @SneakyThrows
    public void countDownLatch() {
        TimeUnit.SECONDS.sleep(200000);
        PHASER.arrive();
        log.info("arrived");
    }

    @SneakyThrows
    public void cyclicBarrier() {
        log.info("phase0");
        TimeUnit.SECONDS.sleep(2);
        // 等待其他线程执行完毕
        PHASER.arriveAndAwaitAdvance();
        log.info("phase1");
        TimeUnit.SECONDS.sleep(3);
        // 等待其他线程执行完毕
        PHASER.arriveAndAwaitAdvance();
        log.info("phase2");
    }

}
