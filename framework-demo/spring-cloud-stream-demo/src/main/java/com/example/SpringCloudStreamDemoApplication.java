package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

/**
 * @author jy
 */
@Slf4j
@SpringBootApplication
public class SpringCloudStreamDemoApplication {

    @Bean
    public Consumer<String> demoConsumer() {
        return s -> log.info("Consume: {}", s);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamDemoApplication.class, args);
    }
}
