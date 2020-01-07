package me.jy.lang.thread.lock.spin;

import me.jy.lang.thread.lock.Lock;

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
            preNode.next = current; // 将上一个线程的next设置为该线程
            while (current.locked) ; // 本地自旋, 等待上一个线程释放这个线程的锁
        }
    }

    @Override
    public void unlock() {
        Node current = this.node.get();
        if (current.next == null) {
            // 如果tail是自己, 则说明只有自己竞争锁, 那么直接清空tail返回.
            if (tail.compareAndSet(current, null)) {
                return;
            }
            // 需要等待其他线程释放锁
            while (current.next == null) ;
        }
        // 如果next不为空, 则直接释放下一个线程的锁
        current.next.locked = false;
        current.next = null;
    }


    private static class Node {
        // important: invalid local cache
        private volatile boolean locked;
        private volatile Node next;
    }
}
