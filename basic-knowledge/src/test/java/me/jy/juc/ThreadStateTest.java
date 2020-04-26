package me.jy.juc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import static me.jy.lang.util.SleepUtil.sleep;

/**
 * @author jy
 */
@Slf4j
class ThreadStateTest {

    @Test
    void testNewState() {

        CountDownLatch latch = new CountDownLatch(1);

        Thread thread = new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException ignored) {
            }
        });

        Assertions.assertEquals(Thread.State.NEW, thread.getState());
        thread.start();
        Assertions.assertTrue(Arrays.asList(Thread.State.RUNNABLE, Thread.State.WAITING).contains(thread.getState()));

        latch.countDown();

        sleep(1000);
        Assertions.assertEquals(Thread.State.TERMINATED, thread.getState());
    }

    @Test
    void testSleepState() {
        Thread thread = new Thread(() -> sleep(1000));
        thread.start();
        sleep(500);
        Assertions.assertEquals(Thread.State.TIMED_WAITING, thread.getState());
    }

    @Test
    void testInterruptRunnable() {
        Thread thread = new Thread();
        thread.start();
        thread.interrupt();

        Assertions.assertTrue(thread.isInterrupted());

        sleep(100);
        Assertions.assertEquals(Thread.State.TERMINATED, thread.getState());
    }

    @Test
    void testInterruptWaiting() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
                log.info("thread interrupted: {}", Thread.currentThread().isInterrupted());
            }
        });
        thread.start();
        thread.interrupt();

        Assertions.assertTrue(thread.isInterrupted());
    }

    @Test
    void testInterruptBlocked() {
        Thread thread = new Thread(() -> {
            try {
                this.wait();
            } catch (InterruptedException ignored) {
                log.info("thread interrupted: {}", Thread.currentThread().isInterrupted());
            }
        });
        thread.start();
        thread.interrupt();

        Assertions.assertTrue(thread.isInterrupted());
    }
}
