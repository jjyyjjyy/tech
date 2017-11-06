package me.jy.se.lang;

import org.junit.Test;

import java.lang.annotation.*;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * @author jy
 * @date 2017/11/06
 */
@AnnotationTests.Miracle("hello")
@AnnotationTests.Miracle("world")
public class AnnotationTests {

    @Test
    public void testAnnotationReflect() {

        assertEquals("[hello, world]", Arrays.stream(AnnotationTests.class.getAnnotationsByType(Miracle.class))
                .map(Miracle::value)
                .collect(Collectors.toList()).toString());

        assertEquals("[hello, world]", Arrays.stream(AnnotationTests.class.getAnnotation(MiracleContainer.class).value())
                .map(Miracle::value)
                .collect(Collectors.toList()).toString());

    }

    @Repeatable(MiracleContainer.class)
    @Target({ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface Miracle {

        String value() default "";
    }

    @Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface MiracleContainer {

        Miracle[] value();
    }

}
