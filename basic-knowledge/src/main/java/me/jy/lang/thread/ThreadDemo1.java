package me.jy.lang.thread;

/**
 * @author jy
 * @date 2017/11/09
 */
public class ThreadDemo1 {

    public static void main(String[] args) {
        new Thread(() -> System.out.println(1)).start();
        Thread.yield();// yield main thread
        System.out.println(2);
    }
}
