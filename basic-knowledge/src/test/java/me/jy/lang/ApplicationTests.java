package me.jy.lang;

import org.junit.Test;

/**
 * @author jy
 * @date 2017/12/05
 */
public class ApplicationTests {

    @Test
    public void testInt() {
        int COUNT_BITS = Integer.SIZE - 3;

        int CAPACITY = (1 << COUNT_BITS) - 1;

        int RUNNING = -1 << COUNT_BITS;
        int SHUTDOWN = 0;
        int STOP = 1 << COUNT_BITS;
        int TIDYING = 2 << COUNT_BITS;
        int TERMINATED = 3 << COUNT_BITS;

        System.out.println(Integer.toBinaryString(RUNNING));
        System.out.println(Integer.toBinaryString(STOP));
        System.out.println(Integer.toBinaryString(TIDYING));
        System.out.println(Integer.toBinaryString(TERMINATED));

        System.out.println(2 & ~CAPACITY);
    }
}
