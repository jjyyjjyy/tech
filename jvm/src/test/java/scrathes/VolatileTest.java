package scrathes;

/**
 * @author jy
 * @date 2018/01/21
 */
public class VolatileTest {

    //    private static boolean flag;

    //solution3
    private static volatile boolean flag;

    private static class AThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread A start...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            flag = true;
            System.out.println("Thread A end...");
        }
    }

    private static class BThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread B start...");
            while (!flag) {
                //solution1
//                System.out.println(flag);

                //solution2
                /*try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
            System.out.println("Thread B end...");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AThread aThread = new AThread();
        BThread bThread = new BThread();
        bThread.start();
        aThread.start();
        aThread.join();
        bThread.join();
        System.out.println("JVM exit!");
    }
}
