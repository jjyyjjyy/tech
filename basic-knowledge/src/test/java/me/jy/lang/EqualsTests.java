package me.jy.lang;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author jy
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
        assertTrue(a == b);
    }

    @Test
    public void testStringEq() {
        String s1 = "Programming";
        String s2 = "Programming";
        String s3 = "Program" + "ming";
        assertFalse(s1 == s2);
        assertTrue(s1 == s3);
        assertTrue(s1 == s1.intern());
    }

}
