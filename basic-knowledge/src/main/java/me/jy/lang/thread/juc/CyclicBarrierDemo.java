package me.jy.lang.thread.juc;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author jy
 */
@Slf4j
public class CyclicBarrierDemo {

    private static final int THREAD_COUNT = 5;

    private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(THREAD_COUNT);

    private static final List<Player> PLAYERS = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_COUNT, () -> {
            log.info("====== Result ======");
            PLAYERS.stream()
                .sorted(Comparator.comparingLong(a -> a.finishedAt))
                .forEach(System.out::println);
            THREAD_POOL.shutdownNow();
        });

        IntStream.rangeClosed(1, THREAD_COUNT)
            .forEach(i -> THREAD_POOL.execute(() -> {
                try {
                    log.info(" is running!");
                    TimeUnit.SECONDS.sleep(i);
                    PLAYERS.add(new Player().setName(Thread.currentThread().getName()).setFinishedAt(Instant.now().getEpochSecond()));
                    cyclicBarrier.await();
                    log.info(" over!");
                } catch (Exception ignored) {
                }
            }));
    }

    @Data
    private static class Player {
        private String name;
        private long finishedAt;
    }

}
