package me.jy.tree;

import java.util.Optional;

/**
 * @author jy
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    private Node root;

    public void insert(T value) {
        Node node = new Node(value);
        if (root == null) {
            root = node;
            return;
        }
        Node current = root;
        while (true) {
            int compare = current.value.compareTo(value);
            if (compare == 0) {
                return;
            }
            if (compare < 0) {
                if (current.right == null) {
                    current.right = node;
                    break;
                }
                current = current.right;
            } else {
                if (current.left == null) {
                    current.left = node;
                    break;
                }
                current = current.left;
            }
        }
    }

    public void remove(T value) {
        checkValue(value);
        remove(value, root);
    }

    public T findMin() {
        if (root == null) {
            return null;
        }
        return findMinNode(root).value;
    }

    public T findMax() {
        if (root == null) {
            return null;
        }
        return findMaxNode(root).value;
    }

    public boolean contains(T value) {
        checkValue(value);
        return contains(value, root);
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    private boolean contains(T value, Node node) {
        if (node == null) {
            return false;
        }
        int compare = value.compareTo(node.value);
        if (compare == 0) {
            return true;
        }
        if (compare < 0) {
            return contains(value, node.left);
        }
        return contains(value, node.right);
    }

    /**
     * 删除
     *
     * @param value 要删除的节点的值
     * @param node  根节点
     */
    private Node remove(T value, Node node) {
        if (node == null) {
            return null;
        }

        int compare = value.compareTo(node.value);
        if (compare < 0) {
            node.left = remove(value, node.left);
        } else if (compare > 0) {
            node.right = remove(value, node.right);
        } else if (node.left != null && node.right != null) {
            // 找到右子节点中最小的节点, 变为node的右子节点
            node.value = findMinNode(node.right).value;
            node.right = remove(node.value, node.right);
        } else {
            // node为树叶或者只有一个子节点
            node = Optional.ofNullable(node.left).orElse(node.right);
        }
        return node;
    }

    private Node findMaxNode(Node node) {
        if (node.right == null) {
            return node;
        }
        return findMaxNode(node.right);
    }

    private Node findMinNode(Node node) {
        if (node.left == null) {
            return node;
        }
        return findMinNode(node.left);
    }

    private void checkValue(T value) {
        if (value == null) {
            throw new IllegalArgumentException("No soup for u!");
        }
    }

    private class Node {

        private T value;
        private Node left;
        private Node right;

        private Node(T value) {
            this.value = value;
        }
    }

}
