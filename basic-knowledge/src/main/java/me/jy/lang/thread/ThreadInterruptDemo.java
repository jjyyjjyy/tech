package me.jy.lang.thread;

/**
 * @author jy
 * @date 2017/12/02
 */
public class ThreadInterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            int count = 0;
            while (!Thread.interrupted()) {
                System.out.println(name + (count++));
            }
        };
        Thread th1 = new Thread(runnable,"r1=");
        Thread th2 = new Thread(runnable,"r2=");

        th1.start();
        th2.start();

        while (true) {
            double d = Math.random();
            if (d > 0.49999 && d < 0.500001) {
                break;
            }
        }
        th1.interrupt();
        th2.interrupt();
    }
}
