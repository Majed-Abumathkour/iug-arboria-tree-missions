package arboriatreemissions.trees;

import arboriatreemissions.datastructures.ArrayList;
import arboriatreemissions.datastructures.Queue;

import java.util.Comparator;

/**
 * Generic binary search tree implementation used by Arboria missions.
 */
public class ArboriaBinaryTree<E> {

    private Node<E> root;

    public ArboriaBinaryTree() {
        this.root = null;
    }

    public ArboriaBinaryTree(E value) {
        this.root = new Node<>(value);
    }

    private Node<E> addRecursive(Node<E> current, E value, Comparator<E> comparator) {
        if (current == null) {
            return new Node<>(value);
        }

        int cmp = comparator.compare(value, current.value);

        if (cmp < 0) {
            current.left = addRecursive(current.left, value, comparator);
        } else {
            current.right = addRecursive(current.right, value, comparator);
        }

        return current;
    }

    public void add(E value, Comparator<E> comparator) {
        root = addRecursive(root, value, comparator);
    }

    private Node<E> deleteNode(Node<E> currentRoot, E value, Comparator<E> comparator) {
        if (currentRoot == null) {
            return null;
        }

        int cmp = comparator.compare(value, currentRoot.value);

        if (cmp < 0) {
            currentRoot.left = deleteNode(currentRoot.left, value, comparator);
        } else if (cmp > 0) {
            currentRoot.right = deleteNode(currentRoot.right, value, comparator);
        } else {
            if (currentRoot.left == null && currentRoot.right == null) {
                return null;
            } else if (currentRoot.left == null) {
                return currentRoot.right;
            } else if (currentRoot.right == null) {
                return currentRoot.left;
            } else {
                Node<E> successor = findMinimum(currentRoot.right);
                currentRoot.value = successor.value;
                currentRoot.right = deleteNode(currentRoot.right, successor.value, comparator);
            }
        }

        return currentRoot;
    }

    private Node<E> findMinimum(Node<E> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void delete(E value, Comparator<E> comparator) {
        this.root = this.deleteNode(this.root, value, comparator);
    }

    private boolean containsRecursive(Node<E> current, E value, Comparator<E> comparator) {
        if (current == null) {
            return false;
        }

        int cmp = comparator.compare(value, current.value);
        if (cmp == 0) {
            return true;
        }

        return cmp < 0
                ? containsRecursive(current.left, value, comparator)
                : containsRecursive(current.right, value, comparator);
    }

    public boolean contains(E value, Comparator<E> comparator) {
        return containsRecursive(root, value, comparator);
    }

    public void inOrder(Node<E> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.value);
            inOrder(node.right);
        }
    }

    public void preOrder(Node<E> node) {
        if (node != null) {
            System.out.println(node.value);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void postOrder(Node<E> node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.value);
        }
    }

    public void inOrder() {
        inOrder(root);
    }

    public void preOrder() {
        preOrder(root);
    }

    public void postOrder() {
        postOrder(root);
    }

    public void breadthTraverse() {
        if (this.root == null) {
            return;
        }

        Queue<Node<E>> nodes = new Queue<>();
        nodes.enqueue(root);

        while (!nodes.isEmpty()) {
            Node<E> node = nodes.dequeue();
            System.out.println(node.value);

            if (node.left != null) {
                nodes.enqueue(node.left);
            }
            if (node.right != null) {
                nodes.enqueue(node.right);
            }
        }
    }

    public ArrayList<E> rangeSearch(E min, E max, Comparator<E> comparator) {
        ArrayList<E> result = new ArrayList<>();
        rangeSearchRecursive(root, min, max, comparator, result);
        return result;
    }

    public void rangeSearchRecursive(Node<E> node, E min, E max, Comparator<E> comparator, ArrayList<E> result) {
        if (node == null) {
            return;
        }

        if (comparator.compare(min, node.value) < 0) {
            rangeSearchRecursive(node.left, min, max, comparator, result);
        }

        if (comparator.compare(min, node.value) <= 0 && comparator.compare(node.value, max) <= 0) {
            result.add(node.value);
        }

        if (comparator.compare(node.value, max) < 0) {
            rangeSearchRecursive(node.right, min, max, comparator, result);
        }
    }

    public int height() {
        return heightRecursive(root);
    }

    private int heightRecursive(Node<E> node) {
        if (node == null) {
            return 0;
        }
        int left = heightRecursive(node.left);
        int right = heightRecursive(node.right);
        return Math.max(left, right) + 1;
    }

    class Node<T> {

        T value;
        Node<T> left;
        Node<T> right;

        Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}

