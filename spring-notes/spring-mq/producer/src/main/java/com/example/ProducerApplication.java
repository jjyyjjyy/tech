package com.example;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.charset.Charset;
import java.util.stream.IntStream;

/**
 * @author jy
 */
@SpringBootApplication
public class ProducerApplication {

    private static Message getMessage(String body) {
        return MessageBuilder.withBody(body.getBytes(Charset.defaultCharset())).setHeader("content_type", "text/plain").build();
    }

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Bean
    public ApplicationRunner ar(RabbitTemplate rabbitTemplate) {
        return ags -> rabbitTemplate.send("amq.fanout", "", getMessage("HelloWorld"));
    }

    @Bean
    public CommandLineRunner clr(RabbitTemplate rabbitTemplate) {
        return args -> IntStream
            .rangeClosed(1, 10)
            .mapToObj(String::valueOf)
            .forEach(i -> rabbitTemplate.send("rk.demo", getMessage(i)));
    }
}
