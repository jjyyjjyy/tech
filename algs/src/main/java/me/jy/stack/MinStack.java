package me.jy.stack;

/**
 * 找到栈中的最小值
 * O(1)
 *
 * @author jy
 */
public interface MinStack {

    void push(Integer e);

    Integer pop();

    Integer getMin();
}
