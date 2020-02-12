package me.jy._17_iterator;

/**
 * @author jy
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
