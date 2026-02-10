package arboriatreemissions.datastructures;

import java.util.Iterator;

/**
 * A queue (FIFO) implementation built on top of the custom LinkedList.
 */
public class Queue<E> implements Iterable<E> {

    private final LinkedList<E> list;

    public Queue() {
        list = new LinkedList<>();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void enqueue(E element) {
        list.addLast(element);
    }

    public E dequeue() {
        return list.removeFirst();
    }

    public E first() {
        return list.first();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}

