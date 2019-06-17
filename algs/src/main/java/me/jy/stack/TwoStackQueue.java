package me.jy.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Supplier;

/**
 * 用两个栈实现队列
 *
 * @author jy
 */
public class TwoStackQueue {

    // tag::twoStackQueue[]
    private Deque<Integer> dataStack = new ArrayDeque<>();
    private Deque<Integer> tmpStack = new ArrayDeque<>();

    public void add(Integer e) {
        dataStack.push(e);
    }

    public Integer poll() {
        return getFirst(tmpStack::pop);
    }

    public Integer peek() {
        return getFirst(tmpStack::peek);
    }

    private Integer getFirst(Supplier<Integer> operator) {
        if (dataStack.isEmpty()) {
            return null;
        }
        while (!dataStack.isEmpty()) {
            tmpStack.push(dataStack.pop());
        }
        Integer value = operator.get();
        while (!tmpStack.isEmpty()) {
            dataStack.push(tmpStack.pop());
        }
        return value;
    }

    // end::twoStackQueue[]
}
