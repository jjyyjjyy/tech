package me.jy.lang;

import org.junit.Test;

import java.lang.annotation.*;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

    @Test
    public void testRetention() {
        assertEquals(null, SourceAnnotationTester.class.getAnnotation(SourceAnnotation.class));
        assertEquals(null, ClassAnnotationTester.class.getAnnotation(ClassAnnotation.class));
        // TODO: RuntimeAnnotation, $Proxy6
        assertNotEquals(RuntimeAnnotation.class.getSimpleName(),
                RuntimeAnnotationTester.class.getAnnotations()[0].getClass().getSimpleName());
    }

    @Test
    public void testElementType() {
        assertEquals(Bar.class.getTypeName(),
                Foo.class.getAnnotatedSuperclass().getType().getTypeName());

        assertEquals("BBB",
                Foo.class.getAnnotatedSuperclass().getAnnotation(TypeUse.class).value());
    }

    // Classes for test @Repeatable

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

    // Classes for test Retention

    @ClassAnnotation
    private static class ClassAnnotationTester {
    }

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.TYPE)
    private @interface ClassAnnotation {

    }

    /**
     * EQ:
     * private static class SourceAnnotationTester {
     * private SourceAnnotationTester() {
     * }
     * }
     */
    @SourceAnnotation
    private static class SourceAnnotationTester {
    }


    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.TYPE)
    private @interface SourceAnnotation {

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    private @interface RuntimeAnnotation {

    }

    @RuntimeAnnotation
    private static class RuntimeAnnotationTester {
    }


    // Classes for test ElementType

    @Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    private @interface TypeUse {
        String value() default "";
    }

    private static class Foo extends @TypeUse("BBB") Bar {
    }

    private static class Bar {
    }

}
