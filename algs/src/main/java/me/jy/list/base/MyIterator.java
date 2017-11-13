package me.jy.list.base;

/**
 * @author jy
 * @date 2017/11/13
 */
public interface MyIterator<E> {

    E next();

    boolean hasNext();

    boolean remove();
}
