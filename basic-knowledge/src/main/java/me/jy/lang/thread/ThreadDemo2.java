package me.jy.lang.thread;

import java.util.stream.Stream;

/**
 * @author jy
 * @date 2017/11/09
 */
public class ThreadDemo2 {

    private static boolean flag;

    private static int value = 1;

/*    private static Thread raceTh1 = new Thread(() -> {
        value = 22;
        flag = true;
    });

    private static Thread raceTh2 = new Thread(() -> {
            if (flag) {
                System.out.println("the value is :" + value);
            } else {
                System.out.println("flag is false");
            }
        });*/

    public static void main(String[] args) throws Exception {
        Stream.generate(() -> 1)
                .limit(20000)
                .forEach(a -> { //TODO: make race condition
                    Thread th1 = new Thread(() -> {
                        value = 22;
                        flag = true;
                    });
                    Thread th2 = new Thread(() -> {
                        if (flag) {
                            System.out.println("the value is :" + value);
                        } else {
                            System.out.println("flag is false");
                        }
                    });
                    try {
                        th1.start();
                        Thread.sleep(10);
                        th2.start();
                        th1.join();
                        th2.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

    }

}
