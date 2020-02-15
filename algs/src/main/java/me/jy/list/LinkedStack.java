package me.jy.list;

/**
 * @author jy
 */
class LinkedStack<T> {

    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;

    public void push(T e) {
        ListNode<T> node = new ListNode<>(e);
        if (tail == null) {
            head = this.tail = node;
        } else {
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;
        }
        size++;
    }

    public T pop() {
        if (size == 0) {
            return null;
        }

        ListNode<T> legacyTail = this.tail;
        this.tail = this.tail.getPrev();

        if (tail == null) {
            head = null;
        } else {
            this.tail.setNext(null);
        }

        legacyTail.setPrev(null);
        size--;

        return legacyTail.getData();
    }

    public int size() {
        return size;
    }
}
