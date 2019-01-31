package me.jy.lang;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author jy
 */
public class OverrideTests {

    public String o(String s) {
        return "String";
    }

    public String o(Object s) {
        return "Object";
    }

    @Test
    public void testOverride() {
        Assert.assertEquals("String", new OverrideTests().o("123"));

    }

}
