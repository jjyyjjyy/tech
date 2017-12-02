package me.jy.lang.thread;

/**
 * @author jy
 * @date 2017/12/02
 */
public class DeadLockDemo {

    private final Object lock1 = new Object();

    private final Object lock2 = new Object();

    public void method1() {
        synchronized (lock1) {
            synchronized (lock2) {
                System.out.println(111111);
            }
        }
    }

    public void method2() {
        synchronized (lock2) {
            synchronized (lock1) {
                System.out.println(222222);
            }
        }
    }

    public static void main(String[] args) {
        DeadLockDemo demo = new DeadLockDemo();
        new Thread(() -> {
            while (true) demo.method1();
        }).start();
        new Thread(() -> {
            while (true) demo.method2();
        }).start();
        // sad

        // acquire lock seq
        // against invoke another synchronized method
    }
}
