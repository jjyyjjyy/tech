package me.jy.iterator;

/**
 * @author jy
 * @date 2018/01/06
 */
public interface Aggregate<T> {

    T get(int index);

    int size();

    void add(T t);


    Iterator<T> iterator();
}
