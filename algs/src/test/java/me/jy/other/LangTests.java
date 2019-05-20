package me.jy.other;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author jy
 */
class LangTests {

    private static <T extends Comparable<T>> T findMax(T[] arr) {
        T maxElement = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (maxElement.compareTo(arr[i]) < 0) {
                maxElement = arr[i];
            }
        }
        return maxElement;
    }

    @Test
    void testArrayExtends() {
        Person[] people = new Employee[2];
        Assertions.assertDoesNotThrow(() -> people[0] = new Employee());
        Assertions.assertThrows(ArrayStoreException.class, () -> people[1] = new Student());
    }

    @Test
    void testGenericMethod() {
        Assertions.assertNotNull(findMax(new Employee[]{new Employee(), new Employee()}));
        Assertions.assertNotNull(findMax(new Manager[]{new Manager(), new Manager()}));
    }

    private interface Person {
    }

    private static class Manager extends Employee {
        @Override
        public int compareTo(Employee o) {
            return super.compareTo(o);
        }
    }

    private static class Student implements Person {
    }

    private static class Employee implements Person, Comparable<Employee> {

        @Override
        public int compareTo(Employee o) {
            return this.hashCode() - o.hashCode();
        }
    }
}
