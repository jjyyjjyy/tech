package me.jy.lang.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jy
 */
@Slf4j
public class ThreadApiDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        log.info("{}, {}", thread.getState(), thread.isAlive());
//        thread.setDaemon(true);// JVM会等待所有非守护线程结束后退出
        thread.start();
        System.out.println(thread.getState());// RUNNABLE
        System.out.println(thread.isAlive());// true
        System.out.println(2);
        // print thread state
        Thread currentThread = Thread.currentThread();
        System.out.println(thread.getName() + ": " + thread.getState());// Thread-0: RUNNABLE/TIME_WAITING
        System.out.println(currentThread.getName() + ": " + currentThread.getState());// main: RUNNABLE

        System.out.println("available processor=" + Runtime.getRuntime().availableProcessors());
    }
}
