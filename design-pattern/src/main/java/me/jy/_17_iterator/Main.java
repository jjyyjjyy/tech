package me.jy._17_iterator;

/**
 * @author jy
 */
class Main {

    public static void main(String[] args) {

        BookSelf bookSelf = new BookSelf();
        bookSelf.add(new Book("A"));
        bookSelf.add(new Book("B"));
        bookSelf.add(new Book("C"));
        bookSelf.add(new Book("D"));

        Iterator<Book> bookIterator = bookSelf.iterator();
        while (bookIterator.hasNext()) {
            System.out.println(bookIterator.next());
        }
    }
}
