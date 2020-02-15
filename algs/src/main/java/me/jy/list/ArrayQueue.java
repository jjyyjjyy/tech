package me.jy.list;

/**
 * @author jy
 */
class ArrayQueue<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] DEFAULT_EMPTY_DATA = {};

    private Object[] elements;

    private int size;

    public ArrayQueue() {
        this.elements = DEFAULT_EMPTY_DATA;
    }

    public ArrayQueue(int capacity) {
        this.elements = new Object[Math.max(capacity, DEFAULT_CAPACITY)];
    }

    public void push(T e) {
        if (this.elements.length == size) {
            resize(size + (size >> 1));
        }
        this.elements[size++] = e;
    }

    public T pop() {
        if (this.size == 0) {
            return null;
        }
        T data = (T) this.elements[0];
        System.arraycopy(elements, 1, elements, 0, --size);
        return data;
    }

    public int size() {
        return size;
    }

    private void resize(int i) {
        Object[] newElements = new Object[Math.max(i, DEFAULT_CAPACITY)];
        System.arraycopy(elements, 0, newElements, 0, size);
        this.elements = newElements;
    }
}
