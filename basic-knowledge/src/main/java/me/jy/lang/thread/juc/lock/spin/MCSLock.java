package me.jy.lang.thread.juc.lock.spin;

import me.jy.lang.thread.juc.lock.Lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author jy
 */
public class MCSLock implements Lock {

    private final ThreadLocal<Node> node = ThreadLocal.withInitial(Node::new);

    private final AtomicReference<Node> tail = new AtomicReference<>();

    @Override
    public void lock() {
        Node current = this.node.get();
        Node preNode = tail.getAndSet(current);
        if (preNode != null) {
            current.locked = true;
            preNode.next = current;
            while (current.locked) ;
        }
    }

    @Override
    public void unlock() {
        Node current = this.node.get();
        if (current.next == null) {
            if (tail.compareAndSet(current, null)) {
                return;
            }
            while (current.next == null) ;
        }
        current.next.locked = false;
        current.next = null;
    }


    private static class Node {
        private volatile boolean locked;
        private Node next;
    }
}
