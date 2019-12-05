package me.jy.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author jy
 */
public class DisruptorTests {

    private Disruptor<TestEvent> disruptor;

    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void setup() {
        disruptor = new Disruptor<>(TestEvent::new, 16, Executors.defaultThreadFactory(), ProducerType.SINGLE, new BlockingWaitStrategy());

        disruptor.handleEventsWith((e, seq, end) -> System.out.println(e.getValue()));
    }

    @Test
    public void testPublishBeforeStart() {

        disruptor.publishEvent(TestEvent::setValue);

        sleep(1);

        disruptor.start();
    }

    @Test
    public void testMultipleArgs() {

        disruptor.publishEvents((e, seq, arg) -> e.setValue(seq), new Long[]{11L, 22L, 33L, 44L, 55L});

        disruptor.start();
    }

    @Data
    private static class TestEvent {
        private Long value;
    }

}
