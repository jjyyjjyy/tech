package me.jy;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jy
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MQScratches {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Test
    public void testDefaultExchange() {

        final String NON_EXCHANGE_QUEUE = "dev.queue.without.exchange";

        amqpAdmin.declareQueue(new Queue(NON_EXCHANGE_QUEUE, false)); // <1> 只声明队列, 不绑定任何exchange
        rabbitTemplate.convertAndSend(NON_EXCHANGE_QUEUE, 1); // <2> 发送消息时直接以queue的名字作为routing key

        assertEquals(1, rabbitTemplate.receiveAndConvert(NON_EXCHANGE_QUEUE)); // <3> 可以收到消息
    }

    @Test
    public void testDirectExchange() {

        final String DIRECT_EXCHANGE_NAME = "dev.exchange.direct";
        final String DIRECT_EXCHANGE_Q1 = "dev.direct.q1";
        final String DIRECT_EXCHANGE_Q2 = "dev.direct.q2";
        final String DIRECT_EXCHANGE_Q3 = "dev.direct.q3";
        final String DIRECT_EXCHANGE_RK1 = "dev.direct.rk1";
        final String DIRECT_EXCHANGE_RK2 = "dev.direct.rk2";

        amqpAdmin.declareExchange(new DirectExchange(DIRECT_EXCHANGE_NAME, false, false));
        amqpAdmin.declareQueue(new Queue(DIRECT_EXCHANGE_Q1, false));
        amqpAdmin.declareQueue(new Queue(DIRECT_EXCHANGE_Q2, false));
        amqpAdmin.declareQueue(new Queue(DIRECT_EXCHANGE_Q3, false));
        amqpAdmin.declareBinding(new Binding(DIRECT_EXCHANGE_Q1, Binding.DestinationType.QUEUE, DIRECT_EXCHANGE_NAME, DIRECT_EXCHANGE_RK1, Collections.emptyMap())); // <1> dev.queue.q1 和 dev.exchange.direct绑定并声明routing key.
        amqpAdmin.declareBinding(new Binding(DIRECT_EXCHANGE_Q2, Binding.DestinationType.QUEUE, DIRECT_EXCHANGE_NAME, DIRECT_EXCHANGE_RK1, Collections.emptyMap())); // <2> dev.queue.q2使用和dev.queue.q1相同的routing key
        amqpAdmin.declareBinding(new Binding(DIRECT_EXCHANGE_Q3, Binding.DestinationType.QUEUE, DIRECT_EXCHANGE_NAME, DIRECT_EXCHANGE_RK2, Collections.emptyMap()));

        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE_NAME, DIRECT_EXCHANGE_RK1, DIRECT_EXCHANGE_RK1); // <3> 发送消息到 dev.rk.demo1
        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE_NAME, DIRECT_EXCHANGE_RK2, DIRECT_EXCHANGE_RK2); // <4> 发送消息到 dev.rk.demo2

        assertEquals(DIRECT_EXCHANGE_RK1, rabbitTemplate.receiveAndConvert(DIRECT_EXCHANGE_Q1));
        assertEquals(DIRECT_EXCHANGE_RK2, rabbitTemplate.receiveAndConvert(DIRECT_EXCHANGE_Q3));
        assertEquals(DIRECT_EXCHANGE_RK1, rabbitTemplate.receiveAndConvert(DIRECT_EXCHANGE_Q2)); // <5> dev.queue.q2也能收到消息
    }

    @Test
    public void testFanoutExchange() {
        final String FANOUT_EXCHANGE_NAME = "dev.exchange.fanout";
        final String FANOUT_EXCHANGE_Q1 = "dev.fanout.q1";
        final String FANOUT_EXCHANGE_Q2 = "dev.fanout.q2";

        amqpAdmin.declareExchange(new FanoutExchange(FANOUT_EXCHANGE_NAME, false, false)); // <1> 声明一个fanout型 exchange
        amqpAdmin.declareQueue(new Queue(FANOUT_EXCHANGE_Q1, false));
        amqpAdmin.declareQueue(new Queue(FANOUT_EXCHANGE_Q2, false));
        amqpAdmin.declareBinding(new Binding(FANOUT_EXCHANGE_Q1, Binding.DestinationType.QUEUE, FANOUT_EXCHANGE_NAME, "asd", Collections.emptyMap()));
        amqpAdmin.declareBinding(new Binding(FANOUT_EXCHANGE_Q2, Binding.DestinationType.QUEUE, FANOUT_EXCHANGE_NAME, "xD", Collections.emptyMap()));

        rabbitTemplate.convertAndSend(FANOUT_EXCHANGE_NAME, "", "fanout"); // <2> 发送消息到fanout exchange时无视routing key

        assertEquals("fanout", rabbitTemplate.receiveAndConvert(FANOUT_EXCHANGE_Q1));
        assertEquals("fanout", rabbitTemplate.receiveAndConvert(FANOUT_EXCHANGE_Q2)); // <3> Q1/Q2都能收到消息
    }

    @Test
    public void testTopicExchange() {
        final String TOPIC_EXCHANGE_NAME = "dev.exchange.topic";
        final String TOPIC_EXCHANGE_Q1 = "dev.topic.q1";
        final String TOPIC_EXCHANGE_Q2 = "dev.topic.q2";

        amqpAdmin.declareExchange(new TopicExchange(TOPIC_EXCHANGE_NAME, false, false));
        amqpAdmin.declareQueue(new Queue(TOPIC_EXCHANGE_Q1, false));
        amqpAdmin.declareQueue(new Queue(TOPIC_EXCHANGE_Q2, false));
        // <1> 使用 # 和 * 声明 routing key
        amqpAdmin.declareBinding(new Binding(TOPIC_EXCHANGE_Q1, Binding.DestinationType.QUEUE, TOPIC_EXCHANGE_NAME, "#.q1", Collections.emptyMap()));
        amqpAdmin.declareBinding(new Binding(TOPIC_EXCHANGE_Q2, Binding.DestinationType.QUEUE, TOPIC_EXCHANGE_NAME, "dev.topic.*", Collections.emptyMap()));

        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, "dev.topic.wa", "q2");
        assertEquals("q2", rabbitTemplate.receiveAndConvert(TOPIC_EXCHANGE_Q2)); // <2> dev.topic.* 与 dev.topic.wa 匹配

        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, "0.q1", "q1");
        assertEquals("q1", rabbitTemplate.receiveAndConvert(TOPIC_EXCHANGE_Q1)); // <3> dev.topic.* 与 0.q1 匹配

        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, "dev.topic.q1", "q1+q2");
        assertEquals("q1+q2", rabbitTemplate.receiveAndConvert(TOPIC_EXCHANGE_Q1));
        assertEquals("q1+q2", rabbitTemplate.receiveAndConvert(TOPIC_EXCHANGE_Q2)); // <4> dev.topic.* 和 #.q1 都与 dev.topic.q1 匹配
    }

    @Test
    public void testHeadersExchange() {
        final String HEADERS_EXCHANGE_NAME = "dev.exchange.headers";
        final String HEADERS_EXCHANGE_Q1 = "dev.headers.q1";
        final String HEADERS_EXCHANGE_Q2 = "dev.headers.q2";

        amqpAdmin.declareExchange(new HeadersExchange(HEADERS_EXCHANGE_NAME, false, false));
        amqpAdmin.declareQueue(new Queue(HEADERS_EXCHANGE_Q1));
        amqpAdmin.declareQueue(new Queue(HEADERS_EXCHANGE_Q2));

    }
}
