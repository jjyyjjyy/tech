package me.jy.lang.thread;

/**
 * @author jy
 * @date 2017/11/09
 */
public class ThreadDemo1 {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        thread.setDaemon(true);// JVM等待所有非守护线程结束后退出
        System.out.println(thread.isAlive());// false
        System.out.println(thread.getState());// NEW
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
