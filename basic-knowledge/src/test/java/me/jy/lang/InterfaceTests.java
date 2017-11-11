package me.jy.lang;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * default method test with Java8
 *
 * @author jy
 * @date 2017/11/07
 */
public class InterfaceTests {

    @Test
    public void testImplDefaultMethod() {
        C c = new C();
        assertEquals(100, c.hello()); // 具体类优先

        D d = new D();
        assertEquals(2, d.hello()); // 子接口优先

        E e = new E();
        assertEquals(111, e.hello()); // 无法确定则需用 类名.super 显示声明

        assertEquals(0, D.class.getDeclaredMethods().length); // getDeclaredMethods 不计算继承的方法
        assertEquals(1, E.class.getDeclaredMethods().length);

    }

    private static class D implements B, A {
    }

    private static class C implements B, A {
        @Override
        public int hello() {
            return 100;
        }
    }

    private static class E implements AA, A {
        @Override
        public int hello() {
            return AA.super.hello();
        }
    }

    private interface B extends A {

        @Override
        default int hello() {
            return 2;
        }
    }

    private interface A {
        default int hello() {
            return 1;
        }
    }

    private interface AA {
        default int hello() {
            return 111;
        }

        static int world() {
            return 1111111111;
        }
    }
}
