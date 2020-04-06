package me.jy.lang;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author jy
 */
class ReflectionTests {


    @Test
    void testGetMethods() {
        System.out.println(Arrays.toString(Child.class.getMethods())); // a + Object
        System.out.println(Arrays.toString(Child.class.getDeclaredMethods())); // []
    }

    @Test
    void testGetFields() {
        System.out.println(Arrays.toString(Child.class.getFields()));
        System.out.println(Arrays.toString(Child.class.getDeclaredFields()));
    }

    public static class Parent {

        public int parentPublicField;
        protected int parentProtectedField;
        private int aField;
        private int parentPrivateField;


        private void parentPrivateMethod() {
        }

        protected void parentProtectedMethod() {
        }

        public void parentPublicMethod() {
        }
    }

    public static class Child extends Parent {

        private int childPrivateField;

        private void childPrivateMethod() {
        }
    }
}

