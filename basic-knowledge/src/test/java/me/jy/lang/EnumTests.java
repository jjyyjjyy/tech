package me.jy.lang;

import org.junit.Assert;
import org.junit.Test;

import java.util.EnumMap;
import java.util.EnumSet;

import static me.jy.lang.EnumTests.Gender.*;
import static org.junit.Assert.assertEquals;

/**
 * 枚举
 *
 * @author jy
 * @date 2017/11/06
 */
public class EnumTests {

    @Test
    public void testEnum() {

        // 枚举常量数量
        assertEquals(3, Gender.values().length);

        // 返回指定名字的枚举常量
        assertEquals(Gender.MALE, Gender.valueOf("MALE"));

        // 返回枚举常量名
        Assert.assertEquals("FEMALE", FEMALE.name());

        // 返回该枚举常量的顺序, 从0开始
        Assert.assertEquals(1, FEMALE.ordinal());

        // compareTo比较枚举常量的顺序
        assertEquals(true, MALE.compareTo(FEMALE) < 0);

        Assert.assertEquals(-1, MIDDLE.getValue());
    }

    @Test
    public void testEnumMap() {

        EnumMap<Gender, Integer> enumMap = new EnumMap<>(Gender.class);

        enumMap.forEach((g, i) -> enumMap.put(g, g.value)); // enumMap#entrySet -> empty
        enumMap.put(MALE, 1);

        assertEquals(1, (int) enumMap.get(MALE));
    }

    @Test
    public void testEnumSet() {
        assertEquals(3, EnumSet.allOf(Gender.class).size());

        // 范围包括边界
        assertEquals(2, EnumSet.range(FEMALE, MIDDLE).size());
    }

    enum Gender implements GenderValue { // 枚举也可实现接口,但不能继承

        MALE(1), FEMALE(2), MIDDLE(-1) {
            public int getValue() {
                System.out.println("Oops");
                return -1;
            }
        };

        private int value;

        // 枚举类只能用private修饰符(默认)
        Gender(int value) {
            this.value = value;
        }

        @Override
        public int getValue() {
            return value;
        }

        // final methods
        /*
        equals
        hashCode
        compareTo
        getDeclaringClass
        clone
        finalize
        */
    }

    interface GenderValue { // inner interface 默认为static
        int getValue();
    }
}