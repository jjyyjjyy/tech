package me.jy.lang;

import org.junit.jupiter.api.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jy
 * @date 2017/11/07
 */
public class GenericTests<A, B> {

    @Test
    public void testGenericSetGet() {
//        List<Fruit> fruits = new ArrayList<Apple>();  list<Apple> x-> list<Fruit>
        List<? extends Fruit> fruits = new ArrayList<Apple>();
        // 上界通配符只能get
//        fruits.add(new Apple());
//        fruits.add(new Fruit());

//        Fruit fruit = fruits.get(0);

        List<? super Banana> bananas = new ArrayList<>();
        bananas.add(new Banana());
        // error
//        bananas.add(new Fruit());

        Object object = bananas.get(0); // 取出来默认为Object,需手动转型
        assertEquals(Banana.class, object.getClass());
    }

    private interface GenericInterface<T> {
    }

    private static class ActualGenericTests extends GenericTests<String, Long> implements GenericInterface<Integer> {
    }

    @Test
    public void testGenericReflection() {
        ParameterizedType type = (ParameterizedType) ActualGenericTests.class.getGenericSuperclass();
        assertEquals(GenericTests.class, type.getRawType());
        Type[] arguments = type.getActualTypeArguments();
        assertEquals(2, arguments.length);
        assertEquals("java.lang.String", arguments[0].getTypeName());
        assertEquals("java.lang.Long", arguments[1].getTypeName());

        Type[] interfaces = ActualGenericTests.class.getGenericInterfaces();
        assertEquals("java.lang.Integer",
            ((ParameterizedType) interfaces[0]).getActualTypeArguments()[0].getTypeName());
    }

    private class Banana extends Fruit {
    }

    private class Apple extends Fruit {

    }

    private class Fruit {
    }
}
