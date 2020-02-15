package me.jy.list;

/**
 * @author jy
 */
class LinkedQueue<T> {

    private ListNode<T> head;
    private ListNode<T> tail;

    private int size;

    public LinkedQueue() {
    }

    public void push(T e) {
        ListNode<T> node = new ListNode<>(e);
        if (head == null) {
            head = tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    public T pop() {
        if (size == 0) {
            return null;
        }

        ListNode<T> legacyHead = this.head;

        this.head = this.head.getNext();

        if (head == null) {
            this.tail = null;
        } else {
            this.head.setPrev(null);
        }

        legacyHead.setNext(null);
        size--;

        return legacyHead.getData();
    }

    public int size() {
        return size;
    }
}
