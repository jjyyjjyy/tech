package scratches;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author jy
 * @date 2018/01/21
 */
public class GCTest {

    private static final int SIZE = 1024 * 1024 * 10;

    private void testGC1() {
        byte[] b1 = new byte[SIZE];
        System.gc();
    }

    private void testGC2() {
        byte[] b2 = new byte[SIZE];
        b2 = null;
        System.gc();
    }

    private void testGC3() {
        {
            byte[] b3 = new byte[SIZE];
        }
        System.gc();
    }

    private void testGC4() {
        {
            byte[] b4 = new byte[SIZE];
        }
        int c = 0;
        System.gc();
    }

    private void testGC5() {
        testGC1();
        System.gc();
    }


    private static StackClass stackClass;

    private static void allocFailed() {
        stackClass = new StackClass();
        stackClass.id = 1;
        stackClass.name = "I am in stack!";
    }

    private static void NothingToAlloc() {
        StackClass stackClass = new StackClass();
        stackClass.id = 1;
        stackClass.name = "I am in stack!";
    }

    public static void main(String[] args) {
        //-XX:+PrintGC

        GCTest gcTest = new GCTest();

        //[GC (System.gc())  18135K->11136K(502784K), 0.0032891 secs]
        //[Full GC (System.gc())  11136K->10979K(502784K), 0.0052007 secs]
//        gcTest.testGC1();

        //[GC (System.gc())  18135K->832K(502784K), 0.0006532 secs]
        //[Full GC (System.gc())  832K->739K(502784K), 0.0043947 secs]
//        gcTest.testGC2();// GC caused

        //[GC (System.gc())  18135K->11104K(502784K), 0.0034522 secs]
        //[Full GC (System.gc())  11104K->10979K(502784K), 0.0061995 secs]
//        gcTest.testGC3();

        //[GC (System.gc())  18135K->848K(502784K), 0.0007128 secs]
        //[Full GC (System.gc())  848K->714K(502784K), 0.0043926 secs]
//        gcTest.testGC4();// GC caused

        //[GC (System.gc())  18135K->11056K(502784K), 0.0047603 secs]
        //[Full GC (System.gc())  11056K->10955K(502784K), 0.0052802 secs]
        //[GC (System.gc())  10955K->10955K(502784K), 0.0002376 secs]
        //[Full GC (System.gc())  10955K->715K(502784K), 0.0041916 secs]
        gcTest.testGC5();// Full GC caused


        // 线程逃逸
        /*for (int i = 0; i < 500000000; i++) {
            NothingToAlloc();// no gc logs
        }*/

        //-XX:MaxMetaspaceSize=1M
        /*int i = 0;
        for (; i < 100000000; i++) {
            try {
                new CGProxy().getInstance(new GCTest());
            } catch (Exception ignored) {
            }finally {
                System.out.println(i);
            }
        }*/


    }

    private static class StackClass {
        private int id;
        private String name;
    }


    private static class CGProxy implements MethodInterceptor {

        private Object target;

        private Object getInstance(Object target) {
            this.target = target;
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(this.target.getClass());
            enhancer.setCallback(this);
            return enhancer.create();
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args,
                                MethodProxy proxy) throws Throwable {
            proxy.invokeSuper(obj, args);
            return null;
        }
    }
}
