package me.jy._17_iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jy
 */
public class BookSelf implements Aggregate<Book> {

    private final List<Book> books = new ArrayList<>();

    @Override
    public Book get(int index) {
        return books.get(index);
    }

    @Override
    public int size() {
        return this.books.size();
    }

    @Override
    public void add(Book book) {
        this.books.add(book);
    }

    @Override
    public Iterator<Book> iterator() {
        return new BookIterator(this);
    }
}
