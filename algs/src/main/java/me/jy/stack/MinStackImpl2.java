package me.jy.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author jy
 */
public class MinStackImpl2 implements MinStack {

    private Deque<Integer> dataStack = new ArrayDeque<>();
    private Deque<Integer> minStack = new ArrayDeque<>();

    // tag::minStack[]
    @Override
    public void push(Integer e) {
        dataStack.push(e);

        if (minStack.isEmpty() || e <= getMin()) {
            minStack.push(e);
        } else {
            minStack.push(getMin());
        }
    }

    @Override
    public Integer pop() {
        minStack.pop();
        return dataStack.pop();
    }

    @Override
    public Integer getMin() {
        return minStack.peek();
    }

    // end::minStack[]
}
