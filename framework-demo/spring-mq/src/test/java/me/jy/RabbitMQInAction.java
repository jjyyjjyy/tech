package me.jy;

import com.rabbitmq.client.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

/**
 * @author jy
 */
@Slf4j
class RabbitMQInAction {

    private static final String EXCHANGE = "demo.exchange";
    private final ConnectionFactory connectionFactory;

    {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("rabbit");
        connectionFactory.setPassword("123456");
        connectionFactory.setVirtualHost("dev");
    }

    /**
     * Exchange属性含义:
     * durable: 服务器重启的时候是否保证不丢失相关信息.
     * autoDelete: 与这个Exchange连接的Exchange或队列都断开时, 该Exchange自动删除.
     * internal: 如果设置为true, 则只能由另外一个Exchange路由到这个Exchange.
     * <p>
     * Queue属性含义:
     * durable: 服务器重启的时候是否保证不丢失相关信息.
     * exclusive: 如果一个队列被声明为排他的, 则这个队列只对首次声明它的连接可见, 并在连接断开后自动删除.
     * autoDelete: 与这个队列连接的消费者都断开时, 该队列会自动删除.
     */
    @Test
    @SneakyThrows
    void testHelloWorld() {
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE, "direct", true, false, null);
        channel.queueDeclare("demo_queue", true, false, false, null);
        channel.queueBind("demo_queue", EXCHANGE, "demo_rk");

        channel.basicPublish(EXCHANGE, "demo_rk", MessageProperties.TEXT_PLAIN, "Hello World!".getBytes());

        channel.close();
        connection.close();


        Connection consumerConnection = connectionFactory.newConnection();
        Channel consumerChannel = consumerConnection.createChannel();

        CountDownLatch latch = new CountDownLatch(1);

        consumerChannel.basicQos(64);
        consumerChannel.basicConsume("demo_queue", true, new DefaultConsumer(consumerChannel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("message: " + new String(body));
                if (true)
                    throw new RuntimeException(new String(body)); // 自动ack
                consumerChannel.basicAck(envelope.getDeliveryTag(), false);
                latch.countDown();
            }
        });

        latch.await();
        consumerChannel.close();
        consumerConnection.close();
    }

    @Test
    @SneakyThrows
    void testMandatory() {
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        CountDownLatch latch = new CountDownLatch(1);

        // routing key匹配不到队列时, mandatory为true时消息会返回给生产者. mandatory为false时消息会直接丢弃.
        channel.basicPublish(EXCHANGE, "oo", true, MessageProperties.TEXT_PLAIN, "mandatory test".getBytes());
        channel.addReturnListener((replyCode, replyText, exchange, routingKey, properties, body) -> {
            System.out.println("Basic.Return: " + new String(body));
            latch.countDown();
        });

        latch.await();
    }

    @Test
    @SneakyThrows
    void testTTL() {

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        // 设置队列消息ttl为6秒, 队列中生存时间大于6秒的消息会被直接丢弃.
        channel.queueDeclare("demo_queue_ttl", true, false, false, Map.of("x-message-ttl", 6000));
        channel.queueBind("demo_queue_ttl", "demo_exchange", "demo_queue_ttl");

        channel.basicPublish(EXCHANGE, "demo_queue_ttl", MessageProperties.TEXT_PLAIN, "I am invisible".getBytes());

        AMQP.BasicProperties properties = MessageProperties.TEXT_PLAIN.builder().deliveryMode(2).expiration("6000").build();
        channel.basicPublish(EXCHANGE, "demo_rk", properties, "I am visible util one consumer join in.".getBytes());

    }

    @Test
    @SneakyThrows
    void testDLX() {
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("demo.exchange.dlx", "direct", true);
        channel.exchangeDeclare("demo.exchange.normal", "direct", true);

        // 设置死信队列
        Map<String, Object> args = Map.of("x-message-ttl", 10000, "x-dead-letter-exchange", "demo.exchange.dlx");

        channel.queueDeclare("demo.queue.normal", true, false, false, args);
        channel.queueBind("demo.queue.normal", "demo.exchange.normal", "demo.queue.normal");

        channel.queueDeclare("demo.queue.dlx", true, false, false, null);
        channel.queueBind("demo.queue.dlx", "demo.exchange.dlx", "demo.queue.normal");

        // 向普通队列发送消息, 消息过期后会放入死信队列.
        // 死信队列匹配的routing key与原队列所用的routing key相同. 也可以指定消息的x-dead-letter-routing-key参数
        channel.basicPublish("demo.exchange.normal", "demo.queue.normal", MessageProperties.TEXT_PLAIN, "I am dead".getBytes());
    }

    @Test
    @SneakyThrows
    void testDelayQueue() {
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("demo.exchange.normal", "direct", true);
        channel.exchangeDeclare("demo.exchange.dlx", "direct", true);

        Stream.of(5, 15, 30, 60)
            .forEach(i -> {
                String queue = "demo.queue." + i + "s";
                try {

                    channel.queueDeclare(queue, true, false, false, Map.of("x-message-ttl", i * 1000, "x-dead-letter-exchange", "demo.exchange.dlx"));
                    channel.queueBind(queue, "demo.exchange.normal", queue);

                    channel.queueDeclare("delay." + queue, true, false, false, null);
                    channel.queueBind("delay." + queue, "demo.exchange.dlx", queue);
                } catch (Exception ignored) {
                }
            });

        CountDownLatch latch = new CountDownLatch(1);

        channel.basicPublish("demo.exchange.normal", "demo.queue.5s", MessageProperties.TEXT_PLAIN, "I am coming!".getBytes());
        log.info("Sending message");

        channel.basicConsume("delay.demo.queue.5s", true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                log.info("message: {} ", new String(body));
                latch.countDown();
            }
        });

        latch.await();
    }
}
