package sync;

import org.openjdk.jol.info.ClassLayout;

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
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }

    public void synchronizedBlock() {
        synchronized (lock) {
        }
    }

    public static void main(String[] args) {

        SynchronizedDemo demo = new SynchronizedDemo();

        System.out.println(ClassLayout.parseInstance(demo.lock).toPrintable());
        demo.synchronizedInstanceMethod();
        System.out.println(ClassLayout.parseInstance(demo.lock).toPrintable());

        // 00000000 00000000 00000000 01100110
        // 11001101 01010001 11000011 00000001
    }
}
