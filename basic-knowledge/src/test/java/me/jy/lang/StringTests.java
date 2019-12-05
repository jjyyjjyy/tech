package me.jy.lang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * @author jy
 */
public class StringTests {

    @Test
    public void testIntern() {
        String s1 = new StringBuilder("js").append("nb").toString();
        String s2 = new StringBuilder("js").append("nb").toString();

        assertSame(s1, s1.intern());
        assertNotSame(s2, s2.intern());

    }

}
