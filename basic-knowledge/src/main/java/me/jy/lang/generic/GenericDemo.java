package me.jy.lang.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * @author jy
 */
public class GenericDemo {

    public static void main(String[] args) {
        // A -> B -> C
        Example<? extends B> eba = new Example<A>();
        Example<? extends B> ebb = new Example<B>();
//        Example<? extends B> ebc = new Example<C>(); // error, C不是B的子类

//        Example<? super B> sba = new Example<A>(); // error, A不是B的父类
        Example<? super B> sbb = new Example<B>();
        Example<? super B> sbc = new Example<C>();

        List<? super B> superList = Arrays.asList(1, new C(), new B(), new A());
        Object s0 = superList.get(0); // get返回Object类型
        System.out.println(s0);
//        A s1 = (A) superList.get(0); // 编译通过, 但运行时抛出ClassCastException

        List<? extends C> extendsList = Arrays.asList(new A(), new B(), new C());
        C c = extendsList.get(0);
//        extendsList.add(new C()); // error, 无法add任何类型

        System.out.println(new SubExample().getGenericType());
    }

    private static class SubExample extends Example<Integer>{

    }

    private static class Example<T> {

        // 获取泛型类型
        public Type getGenericType(){
            ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
            return parameterizedType.getActualTypeArguments()[0];
        }
    }

    private static class C {
    }

    private static class B extends C {
    }

    private static class A extends B {
    }
}
