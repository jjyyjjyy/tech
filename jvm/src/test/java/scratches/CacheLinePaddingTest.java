package scratches;

/**
 * @author jy
 * @date 2018/01/21
 */
public class CacheLinePaddingTest implements Runnable {


    private final int position;
    private static final CustomLong[] CUSTOM_LONGS = new CustomLong[4];

    static {
        for (int i = 0; i < 4; i++) {
            CUSTOM_LONGS[i] = new CustomLong();
        }
    }

    public CacheLinePaddingTest(int position) {
        this.position = position;
    }

    @Override
    public void run() {
        for (long i = 0; i < 1999999999L; i++) {
            CUSTOM_LONGS[position].value = i;
        }
    }

    private static class CustomLong {

        //        private long p1, p2, p3, p4, p5, p6, p7;

        /*@Contended*/
        private long value;
    }

    public static void main(String[] args) throws InterruptedException {
        double sum = 0;
        for (int j = 0; j < 10; j++) {
            long start = System.nanoTime();
            Thread[] threads = new Thread[4];
            for (int i = 0; i < 4; i++) {
                threads[i] = new Thread(new CacheLinePaddingTest(i));
                threads[i].start();
            }
            for (int i = 0; i < 4; i++) {
                threads[i].join();
            }
            sum += (System.nanoTime() - start) / 1000000000d;

        }
        System.out.println(sum / 10);
        //2.1576657949999998
        //2.1085113839000003
        // 太玄学了!

    }
}
