package ref;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author jy
 */
public class ReferenceTest {

    /**
     * JVM options: -Xmx15m -XX:SoftRefLRUPolicyMSPerMB=0
     * 测试SoftReference回收策略
     * gc时内存不足(clock-timestamp>=(Xmx-usedHeapSize)/1MB*SoftRefLRUPolicyMSPerMB), 回收软引用对象.
     */
    @Test
    @SneakyThrows
    void testSoftReference() {
        System.out.println("=== testSoftReference ===");
        Object o = new Object();
        SoftReference<Object> reference = new SoftReference<>(o);
        o = null;
        int[] arr = new int[2 * 1024 * 1024];
        System.gc();
        System.out.println(reference.get());
    }

    /**
     * 测试SoftReference回收策略
     * gc后回收弱引用对象.
     */
    @Test
    void testWeakReference() {
        WeakReference<Object> reference = new WeakReference<>(new Object());
        Assertions.assertNotNull(reference.get());
        System.gc();
        Assertions.assertNull(reference.get());
    }

}
