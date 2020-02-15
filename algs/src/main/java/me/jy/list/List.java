package me.jy.list;

/**
 * @author jy
 */
interface List<T> {

    void add(T e);

    T get(int index);

    void remove(int index);

    int size();
}
