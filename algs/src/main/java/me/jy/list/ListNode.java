package me.jy.list;

import lombok.Data;

@Data
class ListNode<T> {
    private ListNode<T> prev;
    private ListNode<T> next;
    private T data;

    public ListNode(T data) {
        this.data = data;
    }
}
