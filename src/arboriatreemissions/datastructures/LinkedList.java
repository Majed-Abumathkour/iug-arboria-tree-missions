package arboriatreemissions.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A simple singly linked list implementation with head/tail pointers.
 */
public class LinkedList<E> implements Iterable<E> {

    private static class Node<E> {

        private E element;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.element;
    }

    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element, head);
        head = newNode;
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    public void addLast(E element) {
        Node<E> newNode = new Node<>(element, null);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E value = head.element;
        head = head.next;
        size--;
        if (size == 0) {
            tail = null;
        }
        return value;
    }

    public boolean remove(E element) {
        if (isEmpty()) {
            return false;
        }

        if (elementsEqual(head.element, element)) {
            removeFirst();
            return true;
        }

        Node<E> previous = head;
        Node<E> current = head.next;
        while (current != null) {
            if (elementsEqual(current.element, element)) {
                previous.next = current.next;
                if (current == tail) {
                    tail = previous;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public boolean contains(E element) {
        for (E value : this) {
            if (elementsEqual(value, element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (current == null) {
                    throw new NoSuchElementException("No more elements");
                }
                E value = current.element;
                current = current.next;
                return value;
            }
        };
    }

    private boolean elementsEqual(Object a, Object b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return a.equals(b);
    }
}

