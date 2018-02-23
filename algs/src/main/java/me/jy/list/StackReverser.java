package me.jy.list;

import java.util.Stack;

/**
 * @author jy
 * @date 2018/02/22
 */
public class StackReverser {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack);
        reverseStack(stack);
        System.out.println(stack);
    }

    private static <T> void reverseStack(Stack<T> stack) {
        if (stack.isEmpty()) {
            return;
        }
        // 取出来放到stack底部
        T pop = stack.pop();
        reverseStack(stack);
        addStackBottom(stack, pop);
    }

    private static <T> void addStackBottom(Stack<T> stack, T element) {
        if (stack.isEmpty()) {
            stack.push(element);
            return;
        }
        T pop = stack.pop();
        addStackBottom(stack, element);
        stack.push(pop);
    }

}
