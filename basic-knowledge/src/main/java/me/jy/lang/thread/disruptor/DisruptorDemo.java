package me.jy.lang.thread.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.Arrays;
import java.util.concurrent.Executors;

/**
 * @author jy
 */
public class DisruptorDemo {

    public static void main(String[] args) {
        Disruptor<MessageEvent> disruptor = new Disruptor<>(MessageEvent::new, 1024, Executors.defaultThreadFactory(), ProducerType.SINGLE, new BlockingWaitStrategy());

        disruptor.handleEventsWith(new MessageComsumer("Miracle"));

        disruptor.start();

        MessageProducer producer = new MessageProducer(disruptor);
        producer.batchSendData(Arrays.asList("as", "dg", "edg"));
    }
}
