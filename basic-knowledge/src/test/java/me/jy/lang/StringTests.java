package me.jy.lang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * https://blog.csdn.net/xzjayx/article/details/103020029
 *
 * @author jy
 */
class StringTests {

    /**
     * 直接写双引号常量来创建
     * <p>
     * 判断这个常量是否存在于常量池，
     * 如果存在，则直接返回地址值
     *   如果是引用，返回引用地址指向的堆空间对象地址值
     *   如果是常量，则直接返回常量池常量的地址值，
     * 如果不存在，
     *   在常量池中创建该常量，并返回此常量的地址值
     */
    @Test
    void testStringSame() {
        String s1 = "testStringSame 123";
        String s2 = "testStringSame 123";

        assertSame(s1, s2);
        assertSame(s1, s2.intern());
    }

    /**
     * new String创建字符串
     * <p>
     * 首先在堆上创建对象(无论堆上是否存在相同字面量的对象),
     *      然后判断常量池上是否存在字符串的字面量，
     *        如果不存在, 在常量池上创建常量
     *        如果存在, 不做任何操作
     */
    @Test
    void testStringSame1() {
        String s1 = new String("testStringSame1 123");
        String s2 = "testStringSame1 123";
        assertNotSame(s1, s2);
    }

    /**
     * intern方法:
     * <p>
     * 如果字符串常量池存在该常量, 则返回该常量地址.
     * 如果不存在, 则将该字符串对象引用放入常量池, 返回该引用.
     */
    @Test
    void testStringSame2() {
        String s1 = new String("testStringSame2 123").intern(); // String对象创建时会在常量池放入一个"123"常量, intern方法返回该常量引用(非String对象)
        String s2 = "testStringSame2 123";
        assertSame(s1, s2);
    }

    @Test
    void testStringSame3() {
        String s1 = new String("testStringSame3 123");
        s1.intern();
        String s2 = "testStringSame3 123";
        assertNotSame(s1, s2);
    }

    /**
     * 两个双引号的字符串相加
     * <p>
     * 判断这两个常量、相加后的常量在常量池上是否存在
     *   如果不存在
     *    则在常量池上创建相应的常量（并将常量地址值返回）
     *   如果存在，则直接返回地址值（只不过地址值分为两种情况，1是堆中的引用，2是本身常量池的地址）
     *     如果是引用，返回引用地址指向的堆空间对象地址值，
     *     如果是常量，则直接返回常量池常量的地址值，
     */
    @Test
    void testStringSame4() {
        String s1 = new String("testStringSame4 123").intern();
        String s2 = "testStringSame4 " + "123";

        assertSame(s1, s2);
    }

    /**
     * 两个new String()的字符串相加
     * <p>
     * 首先会创建这两个对象（堆中）以及相加后的对象（堆中）
     * 然后拼接后创建一个新的字符串对象
     */
    @Test
    void testStringSame5() {
        String s1 = new String("testStringSame5 ") + new String("123");
        s1.intern();
        String s2 = "testStringSame5 123";
        assertSame(s1, s2);
    }

    @Test
    void testStringSame6() {
        String s1 = new String("testStringSame6 ") + new String("123");
        String s2 = "testStringSame6 123";
        s1.intern();
        assertNotSame(s1, s2);
    }

    /**
     * 双引号字符串常量与new String字符串相加
     * <p>
     * 首先创建两个对象，一个是new String的对象（堆中），一个是相加后的对象(堆中)
     * 然后判断双引号字符串字面量和new String的字面量在常量池是否存在
     *  如果存在
     *   不做操作
     *  如果不存在
     *   则在常量池上创建常量
     */
    @Test
    void testStringSame7() {
        String s1 = "testStringSame7 " + new String("123");
        String s2 = "testStringSame7 123";
        assertNotSame(s1, s2);
        assertSame(s1.intern(), s2);
    }

    /**
     * 双引号字符串常量与一个字符串变量相加
     */
    @Test
    void testStringSame8() {
        String s1 = "123";
        String s2 = "testStringSame8 " + s1;
        String s3 = "testStringSame8 123";
        assertNotSame(s2, s3);
    }
}
