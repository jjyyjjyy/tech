package me.jy.lock.spin;

import me.jy.lock.Lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author jy
 */
public class CLHLock implements Lock {

    private final ThreadLocal<Node> node = ThreadLocal.withInitial(Node::new);

    // 如果只有一个线程, 将自己的node一直设置为tail, 会造成tail和current相同都为true, 则会一直循环下去.
    // 所以在释放锁的时候需要将当前node重新赋值. pre的作用仅仅是复用Node对象.
    private final ThreadLocal<Node> pre = new ThreadLocal<>();

    private AtomicReference<Node> tail = new AtomicReference<>(new Node());

    @Override
    public void lock() {
        Node current = this.node.get();
        current.locked = true; // 设置当前线程正在竞争锁
        Node preNode = this.tail.getAndSet(current);
        this.pre.set(preNode);
        while (preNode.locked) ; // 等待上一个线程释放锁
    }

    @Override
    public void unlock() {
        Node current = this.node.get();
        current.locked = false;
        // 重置当前node. 此处复用上一个node对象, 相当于 `this.node.set(new Node());` .
        this.node.set(this.pre.get());
    }

    private static class Node {
        // false代表已经释放锁, true代表正在竞争锁或者已经成功进入临界区.
        private volatile boolean locked;
    }
}
