package me.jy.list;

import me.jy.list.base.MyCollection;

import java.util.Objects;

/**
 * @author jy
 */
public class MyLinkedList<E> implements MyCollection<E> {

    private int size;

    private Node<E> currentNode;

    private Node<E> head;

    public MyLinkedList() {
        clear();
    }

    @SafeVarargs
    public static <T> MyLinkedList<T> of(T... elements) {
        if (Objects.isNull(elements) || elements.length == 0) {
            return new MyLinkedList<>();
        }
        MyLinkedList<T> list = new MyLinkedList<>();
        for (T element : elements) {
            list.add(element);
        }
        return list;
    }

    @Override
    public void add(E e) {
        if (size == 0) {
            currentNode.element = e;
        } else {
            currentNode = currentNode.next = new Node<>(currentNode, e, null);
        }
        size++;
    }

    @Override
    public boolean remove(int position) {

        Node<E> node = getNode(position);
        node.prev.next = node.next;
        if (position != size - 1) {
            node.next.prev = node.prev;
        }
        size--;
        return true;
    }

    @Override
    public boolean contains(E e) {
        if (size == 0) {
            return false;
        }
        Node<E> temp;
        for (int i = 0; i < size; i++) {
            temp = head.next;
            if (Objects.equals(e, temp)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        return getNode(index).element;
    }

    private Node<E> getNode(int index) {
        if (index >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        Node<E> temp = head;
        for (int i = -1; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        currentNode = new Node<>(head, null, null);
        head = new Node<>(null, null, currentNode);
        currentNode.prev = head;
        size = 0;
    }

    private static class Node<E> {

        private Node<E> prev;
        private E element;
        private Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }
}
