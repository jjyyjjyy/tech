import java.util.concurrent.TimeUnit;

/**
 * @author jy
 */
public class FinalizeDemo {

    private static FinalizeDemo INSTANCE = null;

    public static void main(String[] args) {
        FinalizeDemo demo = new FinalizeDemo();
        demo = null;
        System.gc();

        // 触发finalize方法
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(INSTANCE);

        INSTANCE = null;
        System.gc();

        // 不会触发finalize方法, INSTANCE为null
        System.out.println(INSTANCE);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        INSTANCE = this;
    }
}
