package me.jy.lang.thread.juc.lock;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * backoff随机毫秒数
 *
 * @author jy
 */
public class Backoff {

    private final int minDelay;
    private final int maxDelay;
    private int limit;

    public Backoff(int minDelay, int maxDelay) {
        this.limit = this.minDelay = minDelay;
        this.maxDelay = maxDelay;
    }

    public void backoff() {
        int i = ThreadLocalRandom.current().nextInt(limit) + 1;
        this.limit = Math.min(maxDelay, 2 * i);
        try {
            TimeUnit.MILLISECONDS.sleep(limit);
            if (this.limit == maxDelay) { // miracle :)
                this.limit = minDelay;
            }
        } catch (InterruptedException ignore) {
        }
    }
}
