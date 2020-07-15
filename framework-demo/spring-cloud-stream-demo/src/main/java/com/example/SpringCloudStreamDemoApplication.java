package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author jy
 */
@Slf4j
@SpringBootApplication
public class SpringCloudStreamDemoApplication {

    @Bean
    public Consumer<String> demoConsumerA() {
        return s -> log.info("ConsumeA: {}", s);
    }

    @Bean
    public Consumer<String> demoConsumerB() {
        return s -> log.info("ConsumeB: {}", s);
    }

    @Bean
    public CommandLineRunner clr(StreamBridge streamBridge) {
        return args -> {
            TimeUnit.SECONDS.sleep(5L);
            streamBridge.send("demo.topic", "I am from streamBridge");
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamDemoApplication.class, args);
    }
}
