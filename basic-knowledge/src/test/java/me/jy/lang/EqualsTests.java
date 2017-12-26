package me.jy.lang;

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
}
