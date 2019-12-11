package me.jy.list.base;

/**
 * @author jy
 * @date 2017/11/13
 */
public interface MyCollection<E> {

    void add(E e);

    boolean remove(int position);

    default boolean isEmpty() {
        return size() == 0;
    }

    boolean contains(E e);

    E get(int index);

    int size();

    void clear();
}
