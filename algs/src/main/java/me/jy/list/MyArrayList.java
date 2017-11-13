package me.jy.list;

import me.jy.list.base.MyCollection;
import me.jy.list.base.MyIterator;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author jy
 * @date 2017/11/13
 */
@SuppressWarnings("unchecked")
public class MyArrayList<E> implements MyCollection<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private static final String DEFAULT_ERROR_MESSAGE = "Array index out of bounds ";

    private Object[] elements = new Object[DEFAULT_CAPACITY];

    private int size;

    @Override
    public void add(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    private void ensureCapacity() {
        if (this.size < DEFAULT_CAPACITY) {
            return;
        }

        if (this.size == elements.length) {
            grow(size * 3 / 2 + 1);
        }
    }

    private void grow(int i) {
        Object[] newElements = new Object[i];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        this.elements = newElements;
    }

    @Override
    public boolean remove(int position) {
        if (position < 0 || position >= size--) {
            throw new IllegalArgumentException(DEFAULT_ERROR_MESSAGE + position);
        }
        System.arraycopy(elements, position + 1, elements, position, elements.length - position - 1);
        return true;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(E e) {
        for (Object element : elements) {
            if (element.equals(e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index > this.size - 1)
            throw new IllegalArgumentException(DEFAULT_ERROR_MESSAGE + index);
        return (E) elements[index];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public MyIterator<E> iterator() {
        return new MyArrayListIterator();
    }

    @Override
    public void clear() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = DEFAULT_CAPACITY;
    }

    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    public static <T> MyArrayList<T> of(T... elements) {
        MyArrayList<T> newInstance = new MyArrayList<>();
        // can use constructor with T[] arguments, against waste memory of original elements
        newInstance.elements = elements;
        newInstance.size = elements.length;
        return newInstance;
    }

    public Stream<E> stream() {
        return Arrays.stream((E[]) elements);
    }

    private class MyArrayListIterator implements MyIterator<E> {

        private int position = 0;

        @Override
        public E next() {
            if (this.position >= size) {
                throw new IllegalStateException(DEFAULT_ERROR_MESSAGE);
            }
            return (E) elements[position];
        }

        @Override
        public boolean hasNext() {
            return this.position++ < size;
        }

        @Override
        public boolean remove() {
            return MyArrayList.this.remove(--position);
        }
    }
}
