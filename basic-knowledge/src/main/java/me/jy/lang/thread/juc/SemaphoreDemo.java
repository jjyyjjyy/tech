package me.jy.lang.thread.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 信号量锁
 * 作用: 限制并发数
 *
 * @author jy
 */
@Slf4j
public class SemaphoreDemo {

    private static final ExecutorService THREAD_POOL = Executors.newCachedThreadPool();

    private static final int PLAYER_COUNT = 10;

    private static final Semaphore SEMAPHORE = new Semaphore(5);

    public static void main(String[] args) {
        for (int i = 1; i <= PLAYER_COUNT; i++) {
            int tmp = i;
            THREAD_POOL.execute(() -> {
                try {
                    SEMAPHORE.acquire();
                    log.info("I am in.");
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(tmp));
                } catch (InterruptedException ignored) {
                } finally {
                    SEMAPHORE.release();
                }
            });
        }
    }
}
