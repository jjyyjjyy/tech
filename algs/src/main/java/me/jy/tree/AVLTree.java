package me.jy.tree;

/**
 * @author jy
 */
public class AVLTree<T extends Comparable<T>> {

    private Node root;

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    private int heightBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.abs(height(node.left) - height(node.right));
    }

    /**
     * 将Z节点变为X节点的右子节点, X节点原有的右子节点变为Z节点的左子节点.
     */
    public Node llRotate(Node node) {
        Node pivot = node.left;
        node.left = pivot.right;
        pivot.right = node;

        return pivot;
    }

    /**
     * 将Z节点变为Y节点的左子节点, Y节点原有的左子节点变为Z节点的右子节点.
     */
    public Node rrRotate(Node node) {
        Node pivot = node.right;
        node.right = pivot.left;
        pivot.left = node;
        return pivot;
    }

    /**
     * 以X节点的右子节点为轴进行左单旋(用X节点的右子节点替换X节点), 然后以新的X节点为轴进行右单旋.
     */
    private Node lrRotate(Node node) {
        Node left = node.left;
        Node pivot = left.right;

        // ll
        left.right = pivot.left;
        pivot.left = left;
        node.left = pivot;

        // rr
        node.left = pivot.right;
        pivot.right = node;

        return pivot;
    }

    /**
     * 以Y节点的左子节点为轴进行右单旋(用Y节点的左子节点替换Y节点), 然后以新的Y节点为轴进行左单旋.
     */
    private Node rlRotate(Node node) {
        Node right = node.right;
        Node pivot = right.left;

        // rr
        right.left = pivot.right;
        pivot.right = right;
        node.right = pivot;

        // ll
        node.right = pivot.left;
        pivot.left = node;

        return pivot;
    }

    public void insert(T value) {
        root = insert(root, value);
    }

    private Node insert(Node root, T value) {
        Node node = new Node(value);
        if (root == null) {
            root = node;
        } else if (root.value.compareTo(value) < 0) {
            root.right = insert(root.right, value);
            if (heightBalanceFactor(root) > 1) {
                if (root.right.value.compareTo(value) < 0) {
                    root = rrRotate(root);
                } else {
                    root = rlRotate(root);
                }
            }
        } else {
            root.left = insert(root.left, value);
            if (heightBalanceFactor(root) > 1) {
                if (root.left.value.compareTo(value) > 0) {
                    root = llRotate(root);
                } else {
                    root = lrRotate(root);
                }
            }
        }
        return root;
    }

    public int heightFactor() {
        return heightBalanceFactor(root);
    }

    /**
     * 中序遍历
     */
    public void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.value);
        inOrder(node.right);
    }

    public void print() {
        inOrder(root);
    }

    private class Node {

        private final T value;
        private Node left;
        private Node right;

        private Node(T value) {
            this.value = value;
        }
    }
}
