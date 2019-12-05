package me.jy.junit;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.provider.*;

import java.lang.annotation.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DisplayName("JUnit Scratches")
class JUnitScratches {

    static {
        System.setProperty("ci", "true");
    }

    @Test
    void pre() {
        Assumptions.assumeTrue(false);

        assertTrue(true, "After assume failed..."); // unreachable code
    }

    static Stream<String> stringProvider() {
        return Stream.of("foo", "bar");
    }

    static Stream<Arguments> multiArgProvider() {
        return Stream.of(Arguments.of("a", 1, Arrays.asList(2, 3)));
    }

    @Test
    @DisplayName("😂")
    void testEmojiDisplayName() {
    }

    // ========= 断言 =========
    @Test
    @DisplayName("Test assertion API")
    void testAssertionAPI() {
        assertEquals(2, 2);
        assertEquals(4, 4, "Optional assertion message");
        assertEquals(2, 2, () -> "Lazy evaluated assertion message");
    }

    @Test
    void testGroupAssertion() {
        assertAll("Group head", () -> assertTrue(true), () -> assertTrue(!false));
    }

    // ========= 假设 =========

    @Test
    void testDependentAssertion() {
        assertAll("dependent assertion", () -> System.out.println("first"), () -> System.out.println("second"));
    }

    // ========= 条件测试 =========

    @Test
    void testExceptionThrowing() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Illegal arg");
        });

        assertEquals("Illegal arg", ex.getMessage());
    }

    @Test
    void testTimeoutEx() {
        String result = assertTimeout(Duration.ofMinutes(2L), () -> "result");

        assertEquals("result", result);
    }

    @Test
    void testAssume() {
        Assumptions.assumeTrue(1 == 1);
        Assumptions.assumingThat(true, () -> {
            System.out.println("Assume true...");
        });
    }

    @Disabled
    @Test
    void testDisabledMethod() {
        throw new RuntimeException("DMZ!!!");
    }

    @EnabledOnOs({OS.MAC, OS.LINUX})
    @Test
    void testEnableOnOS() {
        System.out.println("ENABLED ON MAC OS");
    }

    @DisabledOnOs({OS.WINDOWS})
    @Test
    void testDisableOnOS() {
        System.out.println("DISABLED ON WINDOWS");
    }

    // ========== 依赖注入 ==========

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void testEnableOnJRE() {
        System.out.println("ENABLED ON JAVA_8");
    }

    @Test
    @DisabledIfSystemProperty(named = "ci", matches = "true")
    void testDisabledIfSystemProperty() {
        throw new RuntimeException("DMZ!!!");
    }

    @Test
    @DisplayName("di")
    void testInfoDisplayName(TestInfo testInfo) {
        assertEquals("di", testInfo.getDisplayName());
    }

    @Test
    @Tag("t")
    void testInfoTag(TestInfo testInfo) {
        assertTrue(testInfo.getTags().contains("t"));
    }

    // ======== 参数化测试 ==========

    @RepeatedTest(3)
    void testRepetition(RepetitionInfo repetitionInfo) {
        log.info("Repetition sequence: {}", repetitionInfo.getCurrentRepetition());
        assertEquals(3, repetitionInfo.getTotalRepetitions());
    }

    @Test
    void testReport(TestReporter testReporter) {
        testReporter.publishEntry("interesting");
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "B", "C"})
    void testValueSource(String candidate) {
        assertTrue(candidate.matches("[ABC]"));
    }

    @ParameterizedTest
    @ValueSource(strings = "MC")
    void testValueSource(Book book) {
        assertEquals("MC", book.getTitle());
    }

    @ParameterizedTest
    @CsvSource({"Jane,MALE,1999-09-09,Doe,FEMALE,2010-01-01"})
    void testArgumentAggregator(@CsvToPerson Person person) {
        assertTrue(EnumSet.allOf(Gender.class).contains(person.getGender()));
    }

    @ParameterizedTest
    @EnumSource(TimeUnit.class)
    void testEnumSource(TimeUnit timeUnit) {
        assertTrue(EnumSet.allOf(TimeUnit.class).contains(timeUnit));
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    void testMethodSource(String argument) {
        assertTrue(Arrays.asList("foo", "bar").contains(argument));
    }

    // ========== 动态测试 =========

    @ParameterizedTest
    @CsvSource({"foo,1"})
    void testCSVSource(String a, int b) {
        assertEquals("foo", a);
        assertEquals(1, b);
    }

    @ParameterizedTest
    @MethodSource("multiArgProvider")
    void testMultiArg(String a, int b, List<Integer> c) {
        assertEquals("a", a);
        assertEquals(1, b);
        assertTrue(c.containsAll(Arrays.asList(2, 3)));
    }

    @TestFactory
    Stream<DynamicTest> testDynamics() {
        return Stream.of(
            DynamicTest.dynamicTest("1st dynamic test", () -> assertTrue(true)),
            DynamicTest.dynamicTest("2nd dynamic test", () -> assertTrue(true)));
    }


    private enum Gender {
        MALE, FEMALE
    }

    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    @Documented
    @AggregateWith(PersonAggregator.class)
    private @interface CsvToPerson {

    }

    private static class Book {
        private final String title;

        public Book(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    @Data
    private static class Person {
        private String name;
        private Gender gender;
        private LocalDate birthday;
    }

    private static class PersonAggregator implements ArgumentsAggregator {

        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            return new Person().setName(accessor.getString(0))
                .setGender(accessor.get(1, Gender.class))
                .setBirthday(accessor.get(2, LocalDate.class));
        }
    }

}
