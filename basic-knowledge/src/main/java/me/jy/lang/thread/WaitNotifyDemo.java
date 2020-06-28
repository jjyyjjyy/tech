package me.jy.lang.thread;

/**
 * @author jy
 */
public class WaitNotifyDemo {

    private int currentFlag;

    private final int loop = 5;

    public static void main(String[] args) {
        WaitNotifyDemo waitNotifyDemo = new WaitNotifyDemo();

        new Thread(() -> waitNotifyDemo.print("a", 0, 1)).start();
        new Thread(() -> waitNotifyDemo.print("b", 1, 2)).start();
        new Thread(() -> waitNotifyDemo.print("c", 2, 0)).start();
    }

    public void print(String output, int flag, int nextFlag) {

        for (int i = 0; i < loop; i++) {
            synchronized (this) {
                while (currentFlag != flag) {
                    try {
                        this.wait();
                    } catch (InterruptedException ignored) {
                    }
                }
                System.out.println(output);
                currentFlag = nextFlag;
                this.notifyAll();
            }
        }
    }
}
