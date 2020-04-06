package me.jy;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.AbstractRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 重试3次, 如果仍然失败则将消息按照原routiing key(会自动加上error.前缀)发送到指定exchange.
 *
 * @author jy
 */
@Slf4j
@SpringBootApplication
public class SpringMQApplication {

    private static final String ERROR_EXCHANGE = "demo.error.exchange";
    private static final String ERROR_QUEUE = "error.ad-trace.processor.queue";

    private static final String TOPIC_EXCHANGE = "demo.ad-trace-topic.exchange";
    private static final String ROUTING_KEY = "ad-trace.processor.queue";
    private static final String BINDING_KEY = "ad-trace.*.queue";

    private static final String PERSISTENCE_QUEUE = "ad-trace.persistence.queue";
    private static final String SYNC_QUEUE = "ad-trace.sync.queue";

    public static void main(String[] args) {
        SpringApplication.run(SpringMQApplication.class, args);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public BeanPostProcessor messageListenerContainerBeanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

                if (bean instanceof AbstractRabbitListenerContainerFactory) {
                    ((AbstractRabbitListenerContainerFactory<?>) bean).setAfterReceivePostProcessors(message -> {
                        log.info("{}", message);
                        return message;
                    });
                }

                return bean;
            }
        };
    }

    @Bean
    public MessageRecoverer messageRecoverer(RabbitTemplate rabbitTemplate) {
        // 重试
        return new RepublishMessageRecoverer(rabbitTemplate, ERROR_EXCHANGE);
    }

    @RabbitListener(bindings = {@QueueBinding(key = BINDING_KEY,
        value = @Queue(value = PERSISTENCE_QUEUE,
            durable = "true", autoDelete = "false", exclusive = "false"),
        exchange = @Exchange(name = TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC))})
    public void listenPersistenceDTO(DemoDTO demoDTO) {
        log.info("persistence message: {}", demoDTO);
    }

    @RabbitListener(bindings = {@QueueBinding(key = BINDING_KEY,
        value = @Queue(value = SYNC_QUEUE,
            durable = "true", autoDelete = "false", exclusive = "false"),
        exchange = @Exchange(name = TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC))})
    public void listenSyncDTO(DemoDTO syncDTO) {
        double rnd = Math.random();
        if (rnd < 0.9) {
            throw new RuntimeException("sync error");
        }
        log.info("[{}] sync message: {}", rnd, syncDTO);
    }

    @Bean
    public CommandLineRunner clr(RabbitTemplate rabbitTemplate) throws Exception {
        Channel channel = rabbitTemplate.getConnectionFactory()
            .createConnection()
            .createChannel(false);
        channel.exchangeDeclareNoWait(ERROR_EXCHANGE, BuiltinExchangeType.DIRECT, true, false, false, null);
        channel.queueDeclareNoWait(ERROR_QUEUE, true, false, false, null);
        // 绑定 error exchange和 routing key
        channel.queueBind(ERROR_QUEUE, ERROR_EXCHANGE, ERROR_QUEUE);
        channel.close();

        return args -> {
            for (int i = 0; i < 10; i++) {
                rabbitTemplate.convertAndSend(TOPIC_EXCHANGE,
                    ROUTING_KEY,
                    new DemoDTO().setName("DemoDTO"));
            }
        };
    }

    @Data
    public static class DemoDTO {
        private String name;
    }
}
