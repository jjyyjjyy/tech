package me.jy.lang.juc;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author jy
 * @date 2018/02/24
 */
public class TestTaskBase {

    protected final Logger logger = Logger.getLogger(getClass().getSimpleName());

    protected final ExecutorService pool = Executors.newFixedThreadPool(5);

    private long start = 0;
    @Rule
    public TestName testName = new TestName();

    @Before
    public void start() {
        start = System.currentTimeMillis();
        logger.info("Starting " + testName.getMethodName() + " - " + start);
    }

    @After
    public void end() {
        long end = System.currentTimeMillis();
        logger.info("End " + testName.getMethodName() + " - " + end);
        logger.info("Total cost: " + (end - start) + " ms");
        start = 0;
    }

    protected void work() {
        work(1);
    }

    protected int work(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
            System.out.println("work over!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return seconds;
    }

    protected int workExceptionally() {
        work(1);
        throw new RuntimeException("Ex happens");
    }

    protected CompletableFuture<Integer> buildCF() {
        return CompletableFuture.supplyAsync(() -> work(1));
    }

}
