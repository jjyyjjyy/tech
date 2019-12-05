package me.jy.lang;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author jy
 * @date 2018/02/22
 */
public class ReflectionTests {


    @Test
    public void testMethod() {
        System.out.println(Arrays.toString(Child.class.getMethods())); // a + Object
        System.out.println(Arrays.toString(Child.class.getDeclaredMethods())); // []
    }


    public static class Parent {
        public void a() {

        }
    }

    public static class Child extends Parent {
    }
}

