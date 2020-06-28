package oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author jy
 */
public class OOMDemo {

    // -Xmx10m
    // java.lang.OutOfMemoryError: Java heap space
    private static void heapOOM() {
        List<HugeClass> classes = new ArrayList<>();
        while (true) {
            classes.add(new HugeClass());
        }
    }

    // tag::StackOverflow[]
    // java.lang.StackOverflowError
    private static void stackOverflow() {
        stackOverflow();
    }
    // end::StackOverflow[]

    // tag::StackOOM[]
    // java.lang.OutOfMemoryError: unable to create new native thread
    private static void createInfiniteThread() {
        while (true) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100L);
                } catch (InterruptedException ignored) {
                }
            }).start();
        }
    }
    // end::StackOOM[]

    // -Xmx10m
    // java.lang.OutOfMemoryError: Java heap space
    private static void testUTF8ConstantPoolOOM() {
        List<String> list = new ArrayList<>(1000);
        while (true) {
            list.add(("" + ThreadLocalRandom.current().nextInt(99999999)).intern());
        }
    }

    // -XX:MaxMetaspaceSize=20m
    // java.lang.OutOfMemoryError: Metaspace
    private static void createInfiniteProxyClasses() {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(OOMDemo.class);
        enhancer.setUseCache(false);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> null);
        while (true) {
            enhancer.create();
        }
    }

    // java.lang.OutOfMemoryError: Direct buffer memory
    // above jdk13: java.lang.OutOfMemoryError: Cannot reserve 1000000000 bytes of direct buffer memory (allocated: 0, limit: 10485760)
    private static void directMemoryOOM() {
        ByteBuffer.allocateDirect(1_000_000_000);
    }

    public static void main(String[] args) {
//        heapOOM();
//        stackOverFlow();
//        createInfiniteThread();
        testUTF8ConstantPoolOOM();
//        createInfiniteProxyClasses();
//        directMemoryOOM();
    }


    private static class HugeClass {
        private final byte[] bytes = new byte[1 * 1024 * 1024];
    }
}
