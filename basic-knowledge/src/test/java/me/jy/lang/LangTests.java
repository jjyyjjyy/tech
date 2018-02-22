package me.jy.lang;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author jy
 * @date 2017/12/05
 */
public class LangTests {

    // ~ Overload & Override test
    // ========================================================================================================

    public Number testOverload(Number a) {
        return 0;
    }

    public void testOverload(Integer a) throws IOException {
    }

    @Test
    public void testClassLoader() {
        ClassLoader classLoader = getClass().getClassLoader();
        do {
            System.out.println(classLoader);
            classLoader = classLoader.getParent();
        } while (classLoader != null);
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

    // ~ switch test
    // ========================================================================================================

    @Test
    public void testSwitch() {

//        new Type(); enum types cannot be instantiated
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


    private enum Type {
        A, B, C
    }


    int a = 1;

    public LangTests() {
        a = 2;
    }

    @Test
    public void testInit() {
        assertEquals(2, new LangTests().a);
    }

    @Test
    public void testStrReverse() {
        System.out.println(reverseStr("abcdefg"));
    }

    private String reverseStr(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }
        return reverseStr(str.substring(1)) + str.charAt(0);
    }

    @Test
    public void testTCF() {
        Assert.assertEquals(3, tryTest1());
        Assert.assertEquals(2, tryTest2());
        Assert.assertEquals(1, tryTest3());
        Assert.assertEquals(2, tryTest4());
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

    class Annoyance extends Exception {
    }

    class Sneeze extends Annoyance {
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
    public void testEx() {
        testCascadeEx();
    }


}