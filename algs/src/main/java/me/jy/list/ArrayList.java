package me.jy.list;

/**
 * 基于数组实现线性表
 *
 * @author jy
 */
class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] DEFAULT_EMPTY_DATA = {};

    private Object[] elements;

    private int size;

    public ArrayList() {
        this.elements = DEFAULT_EMPTY_DATA;
    }

    public ArrayList(int size) {
        this.elements = new Object[Math.max(0, size)];
    }

    @Override
    public void add(T e) {
        if (this.elements.length == size) {
            resize(size + (size >> 1));
        }
        elements[size++] = e;
    }

    private void resize(int i) {
        i = Math.max(DEFAULT_CAPACITY, i);
        Object[] newElements = new Object[i];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    @Override
    public T get(int index) {
        checkRange(index);
        return (T) elements[index];
    }

    @Override
    public void remove(int index) {
        checkRange(index);
        elements[index] = null;
        if (index != --size) {
            System.arraycopy(elements, index + 1, elements, index, size - index + 1);
        }
    }

    @Override
    public int size() {
        return size;
    }

    private void checkRange(int index) {
        if (index >= size) {
            throw new RuntimeException("Index out of bounds!");
        }
    }

}
