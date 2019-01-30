package me.jy.lang.thread.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.time.Instant;
import java.util.concurrent.Executors;

/**
 * @author jy
 */
public class DisruptorDemo {

    public static void main(String[] args) {

        long end = Instant.now().plusSeconds(10L).getEpochSecond();

        Disruptor<MessageEvent> disruptor = new Disruptor<>(MessageEvent::new, 1024, Executors.defaultThreadFactory(), ProducerType.SINGLE, new BlockingWaitStrategy());

        disruptor.handleEventsWith(new MessageConsumer("Consumer"));

        new Thread(() -> {
            while ((Instant.now().getEpochSecond()) < end) {
                disruptor.publishEvent((event, seq) -> event.setValue(Instant.now().toString()));
            }
        }).start();

        disruptor.start();
    }

    private static void test() {

    }


}
