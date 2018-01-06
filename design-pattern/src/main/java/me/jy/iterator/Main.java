package me.jy.iterator;

/**
 * @author jy
 * @date 2018/01/06
 */
public class Main {

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
