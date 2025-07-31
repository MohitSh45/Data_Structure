package com.exercise1.mohit.sharma;

import com.exercise1.mohit.sharma.AbstractBinaryTree;
import com.exercise1.mohit.sharma.LinkedBinaryTree;
import com.exercise1.mohit.sharma.Position;
import com.exercise1.mohit.sharma.LinkedBinaryTree.Node;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class LinkedBinaryTree<E> {
    // Nested Node class
    protected static class Node<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild;
        }

        public E getElement() { return element; }
        public Node<E> getLeft() { return left; }
        public Node<E> getRight() { return right; }
    }

    protected Node<E> root = null;

    public Node<E> addRoot(E e) {
        root = new Node<>(e, null, null, null);
        return root;
    }

    public Node<E> addLeft(Node<E> parent, E e) {
        parent.left = new Node<>(e, parent, null, null);
        return parent.left;
    }

    public Node<E> addRight(Node<E> parent, E e) {
        parent.right = new Node<>(e, parent, null, null);
        return parent.right;
    }

    public int sumOfDepths() {
        return sumOfDepths(root, 0);
    }

    private int sumOfDepths(Node<E> node, int depth) {
        if (node == null) return 0;
        return depth + sumOfDepths(node.getLeft(), depth + 1) + sumOfDepths(node.getRight(), depth + 1);
    }

    // Main method for testing
    public static void main(String[] args) {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        Node<Integer> root = tree.addRoot(1);
        Node<Integer> left = tree.addLeft(root, 2);
        Node<Integer> right = tree.addRight(root, 3);
        tree.addLeft(left, 4);
        tree.addRight(left, 5);
        tree.addLeft(right, 6);

        System.out.println("Sum of Depths: " + tree.sumOfDepths());  // Output should be 8
    }
}
