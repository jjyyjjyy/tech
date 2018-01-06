package me.jy.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jy
 * @date 2018/01/06
 */
public class BookSelf implements Aggregate<Book> {

    private List<Book> books = new ArrayList<>();

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
