package me.jy.lang.thread;

/**
 * @author jy
 * @date 2017/12/02
 */
public class ThreadCacheDemo {

    private static Integer result;

    private static int computeResult() {
        return 1000;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> result = computeResult());
        System.out.println("raw result=" + result);
        thread.start();
        thread.join();
        while (true) {
            //computeResult return 10000 -> 0000/0000/000
            //computeResult return 100 -> 0
            System.out.println(result);
        }
    }
}
