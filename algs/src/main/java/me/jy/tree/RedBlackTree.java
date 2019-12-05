package me.jy.tree;

import lombok.NonNull;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Red-Black tree implementation.
 * Inspired from https://zhuanlan.zhihu.com/p/79980618
 *
 * @author jy
 */
@SuppressWarnings("unchecked")
public class RedBlackTree<K extends Comparable<K>, V> implements Map<K, V> {

    private RBEntry<K, V> root;

    private int size = 0;

    public V put(@NonNull K key, V value) {
        if (root == null) {
            root = new RBEntry<>(key, value, null);
            root.color = EntryColor.BLACK;
            size++;
            return null;
        }
        RBEntry<K, V> newEntry = doInsert(key, value);
        // 父节点为黑色, 结束.
        if (newEntry == null || parentOf(newEntry).color == EntryColor.BLACK) return null;
        fixAfterInsertion(newEntry);
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        m.forEach(this::put);
    }

    private RBEntry<K, V> doInsert(@NonNull K key, V value) {
        RBEntry<K, V> t = root;
        RBEntry<K, V> parent = null;
        int compare = 0;

        // 找到节点插入的位置
        while (t != null) {
            parent = t;
            compare = key.compareTo(t.key);
            if (compare == 0) {
                t.setValue(value);
                return null;
            }
            if (compare < 0) {
                t = t.leftChild;
            } else {
                t = t.rightChild;
            }
        }
        RBEntry<K, V> newEntry = new RBEntry<>(key, value, parent);
        // 新节点先标记为红色
        newEntry.color = EntryColor.RED;

        // 插入新entry
        if (compare < 0) {
            parent.leftChild = newEntry;
        } else {
            parent.rightChild = newEntry;
        }
        size++;
        return newEntry;
    }

    public RBEntry<K, V> getEntry(@NonNull K key) {
        RBEntry<K, V> t = root;
        while (t != null) {
            int compare = key.compareTo(t.key);
            if (compare == 0) {
                break;
            }
            if (compare < 0) {
                t = t.leftChild;
            } else {
                t = t.rightChild;
            }
        }
        return t;
    }

    public RBEntry<K, V> getFirstEntry() {
        if (root == null) {
            return null;
        }
        RBEntry<K, V> left = root.leftChild;
        while (left.leftChild != null) {
            left = left.leftChild;
        }
        return left;
    }

    public RBEntry<K, V> getLastEntry() {
        RBEntry<K, V> p = root;
        if (p != null)
            while (p.rightChild != null)
                p = p.rightChild;
        return p;
    }

    public V get(@NonNull Object key) {
        RBEntry<K, V> entry = getEntry((K) key);
        return entry == null ? null : entry.getValue();
    }

    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return getEntry((K) key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entries = new LinkedHashSet<>();
        iterateTree(entries, root);
        return entries;
    }

    /**
     * 中序遍历
     */
    private void iterateTree(Set<Entry<K, V>> entrySet, RBEntry<K, V> entry) {
        if (entry == null) {
            return;
        }
        iterateTree(entrySet, entry.leftChild);
        entrySet.add(entry);
        iterateTree(entrySet, entry.rightChild);
    }

    /**
     * 插入后平衡操作以满足红黑树性质
     *
     * @param newEntry 新插入的节点
     */
    private void fixAfterInsertion(RBEntry<K, V> newEntry) {
        RBEntry<K, V> t = newEntry;

        while (t != null && t != root && parentOf(t).color == EntryColor.RED) { // 父节点为红色, 且当前节点不是根节点
            // 父节点
            RBEntry<K, V> parent = parentOf(t);
            // 祖父节点
            RBEntry<K, V> grandParent = parentOf(parent);
            // 祖父节点的左子节点
            RBEntry<K, V> leftParent = leftOf(grandParent);
            // 祖父节点的右子节点
            RBEntry<K, V> rightParent = rightOf(grandParent);
            // 叔叔节点, 即父节点的兄弟节点
            RBEntry<K, V> uncle = parent == leftParent ? rightParent : leftParent;
            // 如果parent节点和uncle节点都为红色
            if (colorOf(uncle) == EntryColor.RED) {
                // 将父节点和uncle节点标记为黑色, grandParent节点标记为红色
                setColor(parent, EntryColor.BLACK);
                setColor(uncle, EntryColor.BLACK);
                setColor(grandParent, EntryColor.RED);
                // 重置X节点
                t = grandParent;
            } else { // uncle节点为黑色
                if (parent == leftParent) {
                    if (t == rightOf(parent)) { // LR -> 左右双旋, 先左单旋
                        t = parent;
                        leftRotate(t);
                    }
                    // 交换parent和grandParent的颜色(此时parent肯定为红色, grantParent肯定为黑色, 所以可以直接设置颜色)
                    setColor(parentOf(t), EntryColor.BLACK);
                    setColor(parentOf(parentOf(t)), EntryColor.RED);
                    rightRotate(parentOf(parentOf(t))); // 右单旋
                } else {
                    if (t == leftOf(parent)) { // RL -> 右左双旋, 先右单旋
                        t = parent;
                        rightRotate(t);
                    }
                    // 交换parent和grandParent的颜色(此时parent肯定为红色, grantParent肯定为黑色, 所以可以直接设置颜色)
                    setColor(parentOf(t), EntryColor.BLACK);
                    setColor(parentOf(parentOf(t)), EntryColor.RED);
                    leftRotate(parentOf(parentOf(t)));
                }
            }
        }

        root.color = EntryColor.BLACK;
    }

    /**
     * 右单旋
     *
     * @param p pivot
     */
    private void rightRotate(RBEntry<K, V> p) {
        if (p == null) {
            return;
        }
        RBEntry<K, V> leftChild = p.leftChild;
        // l的右子节点赋值给p的左子节点
        p.leftChild = leftChild.rightChild;
        if (p.leftChild != null) {
            p.leftChild.parent = p;
        }
        leftChild.parent = p.parent;
        if (p.parent == null) { // 如果原来p节点为root, 则现在leftChild节点成为新的root节点
            root = leftChild;
        } else if (p.parent.rightChild == p) { // 将leftChild挂到p的父节点上
            p.parent.rightChild = leftChild;
        } else {
            p.parent.leftChild = leftChild;
        }
        // 将p挂到leftChild的右节点上
        leftChild.rightChild = p;
        p.parent = leftChild;

    }

    /**
     * 左单旋
     *
     * @param p pivot
     */
    private void leftRotate(RBEntry<K, V> p) {
        if (p == null) {
            return;
        }
        RBEntry<K, V> rightChild = p.rightChild;
        p.rightChild = rightChild.leftChild;
        if (p.rightChild != null) {
            p.rightChild.parent = p;
        }
        rightChild.parent = p.parent;
        if (p.parent == null) {
            root = rightChild;
        } else if (p.parent.rightChild == p) {
            p.parent.rightChild = rightChild;
        } else {
            p.parent.leftChild = rightChild;
        }
        rightChild.leftChild = p;
        p.parent = rightChild;

    }

    private RBEntry<K, V> parentOf(RBEntry<K, V> entry) {
        return entry == null ? null : entry.parent;
    }

    private RBEntry<K, V> leftOf(RBEntry<K, V> entry) {
        return entry == null ? null : entry.leftChild;
    }

    private RBEntry<K, V> rightOf(RBEntry<K, V> entry) {
        return entry == null ? null : entry.rightChild;
    }

    private EntryColor colorOf(RBEntry<K, V> entry) {
        // 空节点标识为黑色
        return entry == null ? EntryColor.BLACK : entry.color;
    }

    private void setColor(RBEntry<K, V> entry, EntryColor color) {
        if (entry != null) {
            entry.color = color;
        }
    }

    public enum EntryColor {
        RED, BLACK
    }

    // public modifier for unit tests
    public static final class RBEntry<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;
        private RBEntry<K, V> parent;
        private RBEntry<K, V> leftChild;
        private RBEntry<K, V> rightChild;
        private EntryColor color = EntryColor.RED;

        private RBEntry(K key, V value, RBEntry<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }

        public RBEntry<K, V> getLeftChild() {
            return leftChild;
        }

        public RBEntry<K, V> getRightChild() {
            return rightChild;
        }

        public RBEntry<K, V> getParent() {
            return parent;
        }
    }

}
