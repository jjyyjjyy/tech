package com.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author jy
 */
@Slf4j
@Service
public class AppService {

    @RabbitListener(queues = "dev.demo")
    public void handleDirectMessage(String message) {
        log.info("Receive message: {}", message);
    }

    @RabbitListener(queues = "dev.fanout.demo")
    public void handleFanoutMessage(String message) {
        log.info("Receive fanout message: {}", message);
    }
}
