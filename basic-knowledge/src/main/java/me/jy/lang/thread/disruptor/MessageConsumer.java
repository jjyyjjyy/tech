package me.jy.lang.thread.disruptor;

import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jy
 */
@Slf4j
public class MessageConsumer implements EventHandler<MessageEvent> {

    private final String consumerName;

    public MessageConsumer(String consumerName) {
        this.consumerName = consumerName;
    }

    @Override
    public void onEvent(MessageEvent event, long sequence, boolean endOfBatch) {
        log.info("Received event: {} by {}", event.getValue(), consumerName);
    }
}
