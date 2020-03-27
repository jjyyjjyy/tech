package me.jy.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        Assertions.assertEquals("String", new OverrideTests().o("123"));
        Assertions.assertEquals("String", new OverrideTests().o(null));
    }

}
