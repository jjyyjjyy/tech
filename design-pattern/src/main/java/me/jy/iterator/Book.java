package me.jy.iterator;

/**
 * @author jy
 * @date 2018/01/06
 */
public class Book {

    private final String name;

    public Book(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
