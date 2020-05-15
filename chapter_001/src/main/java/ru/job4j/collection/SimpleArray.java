package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * SimpleArray.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 13.05.2020.
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] container = new Object[10];
    private int modCount = 0;
    private int index = 0;

    /**
     * Returns element by ind.
     *
     * @param ind Index of returning element.
     * @return T element.
     */
    @SuppressWarnings("unchecked")
    public T get(int ind) {
        Objects.checkIndex(ind, index);
        return (T) container[ind];
    }

    /**
     * Adds element.
     * If array is full, increases array on 2 and adds element.
     *
     * @param model Model to add.
     */
    public void add(T model) {
        if (index >= container.length) {
            increaseSize();
        }
        this.container[index] = model;
        index++;
        modCount++;
    }

    /**
     * Increase array size on 2.
     */
    private void increaseSize() {
        Object[] arr = new Object[container.length * 2];
        System.arraycopy(container, 0, arr, 0, container.length);
        container = arr;
    }

    /**
     * iterator.
     *
     * @return T Iterator.
     */
    @SuppressWarnings("unchecked")
    @Override
    public Iterator<T> iterator() {
        return new SimpleIterator<T>((T[]) container);
    }

    /**
     * SimpleIterator.
     *
     * @author fourbarman (maks.java@yandex.ru).
     * @version %I%, %G%.
     * @since 13.05.2020.
     */
    public class SimpleIterator<E> implements Iterator<E> {
        private int index = 0;
        private int expectedModCount;
        private E[] array;

        /**
         * Constructor
         *
         * @param array E array.
         */
        public SimpleIterator(E[] array) {
            this.array = array;
            expectedModCount = modCount;
        }

        /**
         * Checks if array has next element.
         * If array has changed throws ConcurrentModificationException.
         *
         * @return Boolean.
         */
        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return index < SimpleArray.this.index;
        }

        /**
         * Returns next element from array.
         *
         * @return E.
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return this.array[index++];
        }
    }
}
