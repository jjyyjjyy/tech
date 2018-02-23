package me.jy.lang.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author jy
 * @date 2018/02/22
 */
public class FlatMapDemo {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        Student j = new Student("J");
        Student k = new Student("K");
        j.addBook("A");
        k.addBook("A");
        j.addBook("A");
        k.addBook("A");
        j.addBook("B");
        k.addBook("C");

        students.add(j);
        students.add(k);

        students.stream()
                .map(a -> a.books)
                .flatMap(Collection::stream)
                .forEach(System.out::println);


        List<String> str = Arrays.asList("Hello", "World");
        str.stream()
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);
    }

    private static class Student {
        private final String name;

        private List<Book> books = new ArrayList<>();

        private Student(String name) {
            this.name = name;
        }

        public void addBook(String bookName) {
            this.books.add(new Book(bookName));
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private static class Book {
        private final String bookName;

        private Book(String bookName) {
            this.bookName = bookName;
        }

        @Override
        public String toString() {
            return bookName;
        }
    }
}
