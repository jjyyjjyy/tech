package scrathes;

/**
 * @author jy
 * @date 2018/01/21
 */
public class ReRangeInstructionTest {

    private boolean flag;

    private int a = 1;

    private synchronized void write() {
        flag = true;
        a = 222222;
    }

    private synchronized void read() {
        if (!flag) {
            a = a * 3;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            ReRangeInstructionTest test = new ReRangeInstructionTest();
            Thread th2 = new Thread(test::read);
            Thread th1 = new Thread(test::write);
            th1.start();
            th2.start();
            th1.join();
            th2.join();
            System.out.println(test.a);
            //TODO: fuck
        }
    }
}
