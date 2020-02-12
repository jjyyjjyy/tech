package me.jy._17_iterator;

/**
 * @author jy
 */
public interface Aggregate<T> {

    T get(int index);

    int size();

    void add(T t);

    Iterator<T> iterator();
}
