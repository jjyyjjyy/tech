package me.jy.lang.thread;

/**
 * Thread.UncaughtExceptionHandler demo
 *
 * @author jy
 * @date 2017/11/11
 */
public class ThreadEHDemo {

    private static class DemoHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("Thread " + t.getName() + " exception cause: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {

            new Thread(() -> System.out.println(1 / 0)).start();
        } catch (Exception e) {
            //cannot reach here
            throw new RuntimeException("=====\tException :" + e.getMessage() + "\t=====");
        }

        Thread th2 = new Thread(() -> System.out.println(2 / 0));
        th2.setUncaughtExceptionHandler(new DemoHandler());
        th2.start();//print custom ex message successfully
    }


}
