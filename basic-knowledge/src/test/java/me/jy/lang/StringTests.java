package me.jy.lang;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author jy
 */
public class StringTests {

    @Test
    public void testIntern() {
        String s1 = new StringBuilder("js").append("nb").toString();
        String s2 = new StringBuilder("js").append("nb").toString();

        Assert.assertSame(s1, s1.intern());
        Assert.assertNotSame(s2, s2.intern());

    }

}
