package sync;

/**
 * @author jy
 */
public class SynchronizedDemo {

    private final Object lock = new Object();

    public static synchronized void synchronizedStaticMethod() {
    }

    public static void synchronizedStaticBlock() {
        synchronized (SynchronizedDemo.class) {
        }
    }

    public synchronized void synchronizedInstanceMethod() {
    }

    public void synchronizedBlock() {
        synchronized (lock) {
        }
    }
}
