package me.jy.lang.thread.disruptor;

import com.lmax.disruptor.dsl.Disruptor;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * @author jy
 */
public class DisruptorDemo {

    public static void main(String[] args) {
        Disruptor<MessageEvent> disruptor = new Disruptor<>(MessageEvent::new, 1024, Executors.defaultThreadFactory());

        disruptor.handleEventsWith(Stream.of("Dave", "Gosling", "Miracle").map(MessageComsumer::new).toArray(MessageComsumer[]::new));

        disruptor.start();

        MessageProducer producer = new MessageProducer(disruptor);
        producer.batchSendData(Arrays.asList(args));
    }
}
