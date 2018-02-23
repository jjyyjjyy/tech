package me.jy.list;

import java.util.Arrays;

/**
 * @author jy
 * @date 2018/02/23
 */
@SuppressWarnings("unchecked")
public class SelfArrayDequeue<E> {

    private Object[] elements;

    private int head;
    private int tail;

    private int size = 0;

    public SelfArrayDequeue() {
        this.elements = new Object[16];
    }

    private int getCapacity() {
        return elements.length;
    }

    public boolean add(E e) {// addLast
        if (e == null) {
            return false;
        }

        elements[tail] = e;
        tail = (tail + 1) & (getCapacity() - 1);
        if (tail == head) {
            doubleCapacity();
        }
        size++;
        return true;
    }

    public boolean push(E e) {// addFirst
        if (e == null) {
            return false;
        }
        elements[head = (head - 1) & (getCapacity() - 1)] = e;
        if (tail == head) {
            doubleCapacity();
        }
        size++;
        return true;

    }


    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        if (isEmpty()) {
            return null;
        }

        return (E) elements[(head + index) & (getCapacity() - 1)];
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        E element = (E) elements[head];
        if (element != null) {
            int preHead = (head + 1) & (getCapacity() - 1);
            elements[head] = null;
            head = preHead;
            size--;
            return element;
        }
        return null;
    }

    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        int preTail = (tail - 1) & (getCapacity() - 1);
        E element = (E) elements[preTail];
        if (element != null) {
            elements[preTail] = null;
            tail = preTail;
            size--;
            return element;
        }
        return null;
    }

    public boolean isEmpty() {
        return head == tail;
    }


    private void doubleCapacity() {
        int oldCapacity = getCapacity();
        int newCapacity = oldCapacity << 1;
        int rightLength = elements.length - head;
        Object[] newTable = new Object[newCapacity];
        System.arraycopy(elements, head, newTable, 0, rightLength);
        System.arraycopy(elements, 0, newTable, rightLength, head);
        this.elements = newTable;
        head = 0;
        tail = oldCapacity;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }


    public static void main(String[] args) {
        SelfArrayDequeue<Integer> dequeue = new SelfArrayDequeue<>();
        for (int i = 0; i < 20; i++) {
            dequeue.add(i);
        }
        dequeue.removeLast();
        dequeue.removeFirst();
        dequeue.add(111);
        dequeue.push(2999);

        System.out.println(dequeue);
    }

}
