package me.jy.algs4.ch3;

/**
 * @author jy
 */
public class BST<K extends Comparable<K>, V> {

    private Node root;

    public V get(K key) {
        if (key == null) {
            return null;
        }
        return get(root, key);
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        root = put(root, key, value);
    }

    public int size() {
        return size(root);
    }

    public K min() {
        if (root == null) {
            return null;
        }
        return min(root).key;
    }

    /**
     * 获取排行为n的键
     *
     * @param n 排行
     * @return key
     */
    public K select(int n) {
        if (n <= 0) {
            return null;
        }
        Node selectedNode = select(root, n);
        if (selectedNode == null) {
            return null;
        }
        return selectedNode.key;
    }

    private Node select(Node node, int n) {
        if (node == null) {
            return null;
        }
        int t = size(node.left);

        if (t == n) {
            return node;
        } else if (t < n) {
            return select(node.right, n - t - 1);
        } else {
            return select(node.left, n);
        }
    }

    private Node min(Node root) {
        if (root.left == null) {
            return root;
        }
        return min(root.left);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value, 1);
        }
        int compare = key.compareTo(node.key);
        if (compare > 0) {
            node.right = put(node.right, key, value);
        } else if (compare < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.value = value;
        }
        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }

    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare == 0) {
            return node.value;
        } else if (compare > 0) {
            return get(node.right, key);
        } else {
            return get(node.left, key);
        }
    }

    private int size(Node node) {
        return node == null ? 0 : node.n;
    }

    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private int n;

        private Node(K key, V value, int n) {
            this.key = key;
            this.value = value;
            this.n = n;
        }
    }
}
