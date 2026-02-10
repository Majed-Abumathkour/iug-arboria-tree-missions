package arboriatreemissions.datastructures;

/**
 * A minimal dynamic array-backed list implementation.
 */
public class ArrayList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] data;
    private int size;

    public ArrayList() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E get(int index) {
        checkIndex(index);
        return elementAt(index);
    }

    public E set(int index, E element) {
        checkIndex(index);
        E old = elementAt(index);
        data[index] = element;
        return old;
    }

    public void add(E element) {
        add(size, element);
    }

    public void add(int index, E element) {
        checkIndexForAdd(index);
        ensureCapacity(size + 1);

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = element;
        size++;
    }

    public E remove(int index) {
        checkIndex(index);
        E removed = elementAt(index);

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return removed;
    }

    public boolean remove(E element) {
        int idx = indexOf(element);
        if (idx == -1) {
            return false;
        }
        remove(idx);
        return true;
    }

    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (elementsEqual(elementAtRaw(i), element)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity <= data.length) {
            return;
        }
        changeCapacity(newCapacity);
    }

    private void changeCapacity(int minNewCapacity) {
        int newCapacity = Math.max(data.length * 2, minNewCapacity);
        Object[] expanded = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            expanded[i] = data[i];
        }
        data = expanded;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + ", size " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + ", size " + size);
        }
    }

    @SuppressWarnings("unchecked")
    private E elementAt(int index) {
        return (E) data[index];
    }

    private Object elementAtRaw(int index) {
        return data[index];
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

