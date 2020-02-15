package me.jy.list;

/**
 * 基于数组实现栈
 *
 * @author jy
 */
class ArrayStack<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] DEFAULT_EMPTY_DATA = {};

    private Object[] elements;

    private int size;

    public ArrayStack() {
        this.elements = DEFAULT_EMPTY_DATA;
    }

    public ArrayStack(int capacity) {
        this.elements = new Object[Math.max(capacity, DEFAULT_CAPACITY)];
    }

    public void push(T e) {
        if (this.size == elements.length) {
            resize(size + (size >> 1));
        }
        this.elements[size++] = e;
    }

    private void resize(int i) {
        Object[] newElements = new Object[Math.max(i, DEFAULT_CAPACITY)];
        System.arraycopy(elements, 0, newElements, 0, size);
        this.elements = newElements;
    }

    public T pop() {
        if (this.size == 0) {
            return null;
        }
        T element = (T) this.elements[size - 1];
        this.elements[--size] = null;
        return element;
    }

    public int size() {
        return size;
    }
}
