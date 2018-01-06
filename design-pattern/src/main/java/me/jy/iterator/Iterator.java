package me.jy.iterator;

/**
 * @author jy
 * @date 2018/01/06
 */
public interface Iterator<T> {

    T next();

    boolean hasNext();
}
