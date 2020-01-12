package classloader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author jy
 */
class ClassLoaderTests {

    @Test
    void testClassLoader() {
        Assertions.assertNull(Object.class.getClassLoader());
        Assertions.assertEquals("app", ClassLoaderTests.class.getClassLoader().getName());
        Assertions.assertEquals("platform", ClassLoaderTests.class.getClassLoader().getParent().getName());
        Assertions.assertNull(ClassLoaderTests.class.getClassLoader().getParent().getParent());
    }

    @Test
    void testXmx() {
        System.out.println("-Xms: " + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "MB");
        System.out.println("-Xmx: " + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "MB");
        System.out.println();
    }
}
