package me.jy.lang.thread;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author jy
 */
public class FalseSharingDemo implements Runnable {

    private static final long ITERATIONS = 500L * 1000L * 1000L;

    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    private static final VolatileLong[] longArr = new VolatileLong[THREAD_COUNT];

    static {
        for (int i = 0; i < THREAD_COUNT; i++) {
            longArr[i] = new VolatileLong();
        }
    }

    private final int currentIndex;

    public FalseSharingDemo(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        runTest();
        long end = System.currentTimeMillis();

        System.out.println("Cost: " + (end - start) + "ms");
    }

    private static void runTest() {
        List<CompletableFuture<Void>> tasks = IntStream.range(0, THREAD_COUNT)
            .parallel()
            .mapToObj(FalseSharingDemo::new)
            .map(CompletableFuture::runAsync)
            .collect(Collectors.toList());

        tasks.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longArr[currentIndex].l0 = i;
        }
    }


    private static class VolatileLong {

        private volatile long l0;   // 19s
        private long p1, p2, p3, p4, p5, p6, p7;  // 10s

    }
}

