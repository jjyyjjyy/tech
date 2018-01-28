package me.jy.lang.primitive;

import org.junit.Assert;

import java.nio.charset.Charset;

/**
 * @author jy
 * @date 2018/01/27
 */
public class LangTests {

    public static void main(String[] args) {
        long longValue1 = 321122121;
        long longValue2 = 3211221211L;

        int[] arr = {10};
        Assert.assertEquals(-2, Integer.MAX_VALUE * 2);

        double d = 10 / 4d;

        System.out.println(true | false);
        System.out.println(true & false);
        System.out.println(true ^ false);

        boolean a1 = true;
        int b1 = 0;
        System.out.println(a1 | b1++ > 0);
        System.out.println(b1);

        boolean a2 = true;
        int b2 = 0;
        System.out.println(a1 || b1++ > 0);
        System.out.println(b2);


        //switch : byte short int char Enum String

        for (int i = 0; i < 0; i++) {
            System.out.println(i);
        }
        for (int i : arr) {
            System.out.println(i);
        }

        System.out.println(Integer.toBinaryString(-1));

        // >> 带符号右移  >>> 无符号右移

        // UTF-8  1-4字节
        // UTF-16 2/4字节
        // UTF-32 4字节

        System.out.println(Charset.defaultCharset().name());

        String pre = "Hello";
        String post = "World";
        pre += " ,";
        pre += post;// eq: pre = new StringBuilder(pre).append(" ,").append(post);
        System.out.println(pre);

        String s = null;
        for (int i = 0; i < 10; i++) {
            s += i; // sad: s.append(new StringBuilder(i))
        }
    }
}
