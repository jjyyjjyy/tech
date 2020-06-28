package me.jy.lang.thread;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author jy
 */
public class ABAProblem {

    private final AtomicReference<Integer> ar = new AtomicReference<>(100);

    private final AtomicStampedReference<Integer> asr = new AtomicStampedReference<>(100, 1);

    private static void testAtomicReference() {
        ABAProblem abaProblem = new ABAProblem();
        new Thread(abaProblem::updateTwice).start();
        new Thread(() -> {
            abaProblem.updateSlowly();
            System.out.println(abaProblem.ar.get()); // 102
        }).start();
    }

    private static void testAtomicStampedReference() {
        ABAProblem abaProblem = new ABAProblem();
        new Thread(abaProblem::updateStampedTwice).start();
        new Thread(() -> {
            abaProblem.updateStampedSlowly();
            System.out.println(abaProblem.asr.getReference()); // 100
        }).start();
    }

    public static void main(String[] args) {
        testAtomicReference();
        testAtomicStampedReference();
    }

    public void updateTwice() {
        ar.compareAndSet(100, 101);
        ar.compareAndSet(101, 100);
    }

    @SneakyThrows
    public void updateSlowly() {
        TimeUnit.SECONDS.sleep(1L);
        ar.compareAndSet(100, 102);
    }

    @SneakyThrows
    public void updateStampedTwice() {
        int stamp = asr.getStamp();
        TimeUnit.SECONDS.sleep(1L);
        asr.compareAndSet(100, 101, stamp, ++stamp);
        asr.compareAndSet(101, 100, stamp, ++stamp);
    }

    @SneakyThrows
    public void updateStampedSlowly() {
        int stamp = asr.getStamp();
        try {
            TimeUnit.SECONDS.sleep(2L);
        } catch (InterruptedException ignored) {
        }
        asr.compareAndSet(100, 102, stamp, ++stamp);
    }
}
