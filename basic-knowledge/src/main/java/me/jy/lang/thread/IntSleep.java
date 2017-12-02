package me.jy.lang.thread;

/**
 * @author jy
 * @date 2017/12/02
 */
public class IntSleep {

    public static void main(String[] args) throws InterruptedException {
        Thread intSleep = new Thread(() -> {

            while (true) {
                System.out.println("Hello");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " -> interrupted!");
                    break;
                }
            }
        });
        intSleep.start();

        Thread.sleep(2000);
        intSleep.interrupt();
    }
}
