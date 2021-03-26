package me.jy.lang.thread;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author jy
 */
public class CompletableFuturePerformace {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(8);
        long startAt = Instant.now().getEpochSecond();
        List<CompletableFuture<Void>> tasks = IntStream.rangeClosed(1, 100)
            .mapToObj(i -> CompletableFuture.runAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, pool))
            .collect(Collectors.toList());
        tasks.stream().map(CompletableFuture::join).collect(Collectors.toList());
        // 100 * 2 / 8 = 25s
        System.out.println("Cost: " + (Instant.now().getEpochSecond() - startAt) + " s");
        pool.shutdown();
    }
}
