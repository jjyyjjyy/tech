package me.jy.lang;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jy
 * @date 2017/12/19
 */
public class EqualsTests {

    @Test
    public void testAbstractMapEquals() {
        Map<Object, Object> m1 = new HashMap<>();
        Map<Object, Object> m2 = new HashMap<>();
        m1.put(null, null);
        m2.put(null, null);
        assert m1.equals(m2);
    }

    @Test
    public void testLong() {
        Long a = 127L;
        Long b = 127L;
        Assert.assertTrue(a == b);
    }

    @Test
    public void testStringEq() {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s1.intern());
    }

}
