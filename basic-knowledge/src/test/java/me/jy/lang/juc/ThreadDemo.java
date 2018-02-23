package me.jy.lang.juc;

/**
 * @author jy
 * @date 2018/02/23
 */
public class ThreadDemo {

    private void w() {
        synchronized (this) {
            System.out.println("wait start");
            try {
                this.wait();
                System.out.println("wait over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void n() {
        System.out.println("notify start");
        synchronized (this) {
            this.notifyAll();
            try {
                Thread.sleep(200);
                System.out.println("notify over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadDemo demo = new ThreadDemo();
        demo.w();
        demo.n();
    }
}