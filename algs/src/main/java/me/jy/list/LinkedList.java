package me.jy.list;

/**
 * 双向链表实现
 *
 * @author jy
 */
class LinkedList<T> implements List<T> {

    private ListNode<T> head;

    private ListNode<T> tail;

    private int size;

    // tag::reverse-list[]
    public static <T> ListNode<T> reverse(ListNode<T> head) {
        ListNode<T> prev = null;
        ListNode<T> current = head;
        while (current != null) {
            ListNode<T> next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        return prev;
    }
    // end::reverse-list[]

    @Override
    public void add(T e) {
        ListNode<T> node = new ListNode<>(e);
        if (this.tail == null) {
            head = this.tail = node;
        } else {
            node.setPrev(tail);
            this.tail.setNext(node);
            this.tail = node;
        }
        size++;
    }

    @Override
    public T get(int index) {
        ListNode<T> current = head;
        for (int i = 0; i < index && current != null; i++) {
            current = current.getNext();
        }
        if (current == null) {
            throw new RuntimeException("Index out of bound!");
        }
        return current.getData();
    }

    @Override
    public void remove(int index) {
        ListNode<T> current = head;
        for (int i = 0; i < index && current != null; i++) {
            current = current.getNext();
        }
        if (current == null) {
            throw new RuntimeException("Index out of bound!");
        }
        ListNode<T> prev = current.getPrev();
        if (prev == null) {
            head = current.getNext();
        } else {
            prev.setNext(current.getNext());
        }
        size--;
    }

    @Override
    public int size() {
        return size;
    }

}
