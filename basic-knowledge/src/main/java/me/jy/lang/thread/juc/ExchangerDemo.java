package me.jy.lang.thread.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @author jy
 */
@Slf4j
public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            try {
                // 阻塞等待线程B发送数据
                String data = exchanger.exchange("A");
                log.info(data);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "A").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                // 与线程A交换数据
                String data = exchanger.exchange("B");
                log.info(data);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "B").start();
    }
}
