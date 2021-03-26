package me.jy.lang;

import org.junit.jupiter.api.Test;
import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author jy
 * @date 2017/12/05
 */
class LangTests {

    int a = 1;
    private byte foo;

    public LangTests() {
        a = 2;
    }

    public Number testOverload(Number a) {
        return 0;
    }

    public void testOverload(Integer a) throws IOException {
    }

    @Test
    void testOverflow() {
        assertTrue(Double.isNaN(1.0 % 0));
    }

    @Test
    void testClassLoader() {
        ClassLoader classLoader = getClass().getClassLoader();
        do {
            System.out.println(classLoader);
            classLoader = classLoader.getParent();
        } while (classLoader != null);
    }

    @Test
    void testSwitch() {

        Type type = Type.A;
        switch (type) {
            case A:
                System.out.println(1);
                break;
            case B:
                System.out.println(2);
                break;
            case C:
                System.out.println(3);
                break;
            default:
                System.out.println(0);
        }


        String str = "abc";
        switch (str) {
            case "d":
                System.out.println(1);
                break;
            case "c":
                System.out.println(2);
                break;
            case "A":
                System.out.println(3);
                break;
            default:
                System.out.println(0);
        }

        System.out.println(Math.round(11.5));
        System.out.println(Math.round(-11.5));
    }

    @Test
    void testChar() {
        int c1 = -1;
        int c2 = 65535;
        char cc1 = (char) c1;
        char cc2 = (char) c2;
        assertEquals(cc1, cc2);
    }

    @Test
    void testInfinite() {
        assertEquals(1 / 0.0F, Double.POSITIVE_INFINITY, 0);
        assertEquals(1 / -0.0F, Double.NEGATIVE_INFINITY, 0);
    }

    @Test
    void testUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = (Unsafe) field.get(null);
            LangTests t = new LangTests();
            unsafe.putByte(t, foo, (byte) 2);
            assertEquals(2, unsafe.getByte(t, foo));
            assertTrue(unsafe.getBoolean(t, foo));

            unsafe.putByte(t, foo, (byte) 1);
            assertEquals(1, unsafe.getByte(t, foo));
            assertTrue(unsafe.getBoolean(t, foo));
        } catch (IllegalAccessException | NoSuchFieldException ignore) {
        }
    }

    @Test
    void testInit() {
        assertEquals(2, new LangTests().a);
    }

    @Test
    void testStringEquals() {
        String s1 = "ccc";
        String s2 = "cc" + new String("c");
        String s3 = new String("ccc");

        assertSame(s1, s1.intern());
        // s2为堆中的对象, s1.intern()为常量池中的对象
        assertNotSame(s2, s1.intern());
        // s3为堆中的对象
        assertSame(s1, s3.intern());
    }

    @Test
    void testComputation() {
        int a = 0;
        for (int i = 0; i < 100_000_000; i++) {
            a += i % 8;
        }
        System.out.println(a); // 130ms
    }

    @Test
    void testComputation2() {
        int a = 0;
        for (int i = 0; i < 100_000_000; i++) {
            a += i & 7;
        }
        System.out.println(a); // 75ms
    }

    @Test
    void testStrReverse() {
        System.out.println(reverseStr("abcdefg"));
    }

    private String reverseStr(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }
        return reverseStr(str.substring(1)) + str.charAt(0);
    }

    @Test
    void testTCF() {
        assertEquals(3, tryTest1());
        assertEquals(2, tryTest2());
        assertEquals(1, tryTest3());
        assertEquals(2, tryTest4());
    }

    private int tryTest1() {
        try {
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            return 3;
        }
    }

    private int tryTest2() {
        int i;
        try {
            i = 1;
            return i;
        } finally {
            i = 2;
            return i;
        }
    }

    private int tryTest3() {
        int i;
        try {
            i = 1;
            return i;
        } finally {
            i = 2;
        }
    }

    private int tryTest4() {
        int i;
        try {
            i = 1 / 0;
            return i;
        } catch (Exception e) {
            System.out.println("hit catch");
            i = 3;
            return i;
        } finally {
            System.out.println("hit finally");
            i = 2;
        }
    }

    public void testCascadeEx() {
        try {
            try {
                throw new Sneeze();
            } catch (Annoyance a) {
                System.out.println("Caught Annoyance");
                throw a;
            }
        } catch (Sneeze s) {
            System.out.println("Caught Sneeze");
        } finally {
            System.out.println("Hello World!");
        }
    }

    @Test
    void testEx() {
        testCascadeEx();
    }

    @Test
    void testOperator() {
        boolean a = true;
        int b = 0;
        boolean c = a | b++ > 0;
        assertEquals(1, b);

        b = 0;
        c = a || b++ > 0;
        assertEquals(0, b);
    }

    private enum Type {
        A, B, C
    }

    private static class A {
        Number a() {
            return 0;
        }
    }

    private static class B extends A {
        @Override
        Integer a() {
            return 0;
        }
    }

    class Annoyance extends Exception {
    }

    class Sneeze extends Annoyance {
    }

}
