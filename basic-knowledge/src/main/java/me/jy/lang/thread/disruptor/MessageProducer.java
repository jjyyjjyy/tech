package me.jy.lang.thread.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.Collection;

/**
 * @author jy
 */
public class MessageProducer {

    private final Disruptor<MessageEvent> disruptor;


    public MessageProducer(Disruptor<MessageEvent> disruptor) {
        this.disruptor = disruptor;
    }

    public void sendData(String data) {
        RingBuffer<MessageEvent> ringBuffer = this.disruptor.getRingBuffer();
        long idx = ringBuffer.next();
        MessageEvent event = ringBuffer.get(idx);
        event.setValue(data);
        ringBuffer.publish(idx);
    }

    public void batchSendData(Collection<String> dataCollection) {
        dataCollection.forEach(this::sendData);
    }
}
