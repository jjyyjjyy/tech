package me.jy.lang.thread;

import lombok.extern.slf4j.Slf4j;
import me.jy.lang.util.SleepUtil;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jy
 */
@Slf4j
public class ShutdownDemo {

    // tag::shutdown[]

    private static void shutdown() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            SleepUtil.sleep(1000);
            log.info("sleep 1s");
        });
        executorService.execute(() -> {
            SleepUtil.sleep(2000);
            log.info("sleep 2s");
        });
        executorService.execute(() -> {
            SleepUtil.sleep(3000);
            log.info("sleep 3s");
        });
        executorService.shutdown(); // <1>
        log.info("shutdown called");
        executorService.execute(() -> System.out.println("no more")); // <2>
        // <3>
    }
    // end::shutdown[]

    // tag::shutdownNow[]

    private static void shutdownNow() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            SleepUtil.sleep(10000);
            log.info("sleep 10s");
        });
        executorService.execute(() -> {
            SleepUtil.sleep(20000);
            log.info("sleep 20s");
        });
        executorService.execute(() -> {
            SleepUtil.sleep(30000);
            log.info("sleep 30s");
        });
        List<Runnable> runnables = executorService.shutdownNow(); // <1>
        log.info("shutdownNow: {}", runnables); // <2>
        log.info("shutdown called");
        executorService.execute(() -> System.out.println("no more")); // <3>
        // <4>
    }
    // end::shutdownNow[]


    public static void main(String[] args) {
        shutdown();
//        shutdownNow();
    }
}
