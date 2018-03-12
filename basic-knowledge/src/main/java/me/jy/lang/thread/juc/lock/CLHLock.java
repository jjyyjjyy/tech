package me.jy.lang.thread.juc.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author jy
 * @date 2018/03/12
 */
public class CLHLock implements Lock {

    private final AtomicReference<Node> tail = new AtomicReference<>(new Node());

    private final ThreadLocal<Node> currentNode = ThreadLocal.withInitial(Node::new);

    private final ThreadLocal<Node> preNode = new ThreadLocal<>();

    @Override
    public void lock() {

        Node current = currentNode.get();
        current.locked = true;

        Node pre = tail.getAndSet(current);
        preNode.set(pre);

        while (pre.locked) {
        }

    }

    @Override
    public void unlock() {
        Node current = currentNode.get();
        current.locked = false;
        currentNode.set(preNode.get());
    }


    private static class Node {
        private volatile boolean locked;
    }
}
