package me.jy.iterator;

/**
 * @author jy
 * @date 2018/01/06
 */
public class BookIterator implements Iterator<Book> {

    private final Aggregate<Book> bookAggregate;

    private int position = 0;

    public BookIterator(Aggregate<Book> bookAggregate) {
        this.bookAggregate = bookAggregate;
    }

    @Override
    public Book next() {
        Book book = bookAggregate.get(position);
        position++;
        return book;
    }

    @Override
    public boolean hasNext() {
        return bookAggregate.size() > position;
    }
}
