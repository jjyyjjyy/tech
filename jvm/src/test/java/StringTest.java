import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://blog.csdn.net/xzjayx/article/details/103020029
 *
 * @author jy
 */
class StringTest {

    @Test
    void testStringEquals() {
        /*String s1 = new String("123");
        String s2 = "123";
        Assertions.assertFalse(s1 == s2);*/

        /*String s1 = new String("123").intern();
        String s2 = "123";
        Assertions.assertTrue(s1 == s2);*/

        /*String s1 = new String("123");
        s1.intern();
        String s2 = "123";
        Assertions.assertFalse(s1 == s2);*/

        /*String s = "123";
        Assertions.assertTrue(s == s.intern());*/

        /*String s = new String("123");
        Assertions.assertFalse(s == s.intern());*/

        /*String s1 = new String("123").intern();
        String s2 = "1" + "23";
        Assertions.assertTrue(s1 == s2);*/

        /*String s1 = new String("1") + new String("23");
        String s2 = "123";
        s1.intern();
        Assertions.assertFalse(s1 == s2);*/

        /*String s1 = "1" + new String("23");
        String s2 = "123";
        Assertions.assertFalse(s1 == s2);
        Assertions.assertTrue(s1.intern() == s2);*/

        String s1 = "23";
        String s2 = "1" + s1;
        String s3 = "123";
        Assertions.assertFalse(s2 == s3);
        Assertions.assertTrue(s2.intern() == s3);

    }

}
