package me.jy.pi;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author jy
 */
public class EstimatePI {

    public double estimate(int count) {

        AtomicInteger insideCircleCount = new AtomicInteger();

        IntStream.rangeClosed(1, count)
            .parallel()
            .forEach(i -> {
                double a = ThreadLocalRandom.current().nextDouble();
                double b = ThreadLocalRandom.current().nextDouble();
                if ((Math.pow(a, 2) + Math.pow(b, 2)) <= 1) {
                    insideCircleCount.incrementAndGet();
                }
            });
        return insideCircleCount.get() / (double) count * 4;
    }
}
