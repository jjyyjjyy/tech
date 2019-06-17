package me.jy.stack;

import lombok.NonNull;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 找到栈中的最小值
 * O(1)
 *
 * @author jy
 */
public class MinStackImpl1 implements MinStack {

    private Deque<Integer> dataStack = new ArrayDeque<>();
    private Deque<Integer> minStack = new ArrayDeque<>();

    // tag::minStack[]
    @Override
    public void push(@NonNull Integer e) {
        dataStack.push(e);

        if (minStack.isEmpty() || e <= getMin()) {
            minStack.push(e);
        }
    }

    @Override
    public Integer pop() {
        Integer poppedValue = dataStack.pop();
        if (poppedValue <= getMin()) {
            minStack.pop();
        }
        return poppedValue;
    }

    @Override
    public Integer getMin() {
        return minStack.peek();
    }
    // end::minStack[]

}
