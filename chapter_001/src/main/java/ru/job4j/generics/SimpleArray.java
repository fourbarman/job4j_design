package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleArray.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 08.05.2020.
 */
public class SimpleArray<T> implements Iterable<T> {
    private T[] arr;
    private int size;
    private int index;

    /**
     * Constructor
     *
     * @param size Size of array.
     */
    public SimpleArray(int size) {
        this.index = 0;
        this.arr = (T[]) new Object[size];
        this.size = arr.length;
    }

    /**
     * Adds element.
     *
     * @param model Model to add.
     */
    public void add(T model) {
        if (index < size) {
            arr[index] = model;
            index++;
        }
    }

    /**
     * Sets element at ind.
     *
     * @param ind   Index to add to.
     * @param model Model to set.
     */
    public void set(int ind, T model) {
        if (ind >= size || ind >= index - 1) {
            throw new ArrayStoreException();
        }
        arr[ind] = model;
    }

    /**
     * Removes element from array and moves rest elements to the left.
     *
     * @param ind Index of deleting element.
     */
    public void remove(int ind) {
        if (ind >= size || ind >= index - 1) {
            throw new ArrayStoreException();
        }
        arr[ind] = null;
        System.arraycopy(arr, ind + 1, arr, ind, index - ind + 1);
        index--;
    }

    /**
     * Returns element by ind.
     *
     * @param ind Index of returning element.
     * @return T element.
     */
    public T get(int ind) {
        if (ind < index && ind < size) {
            return arr[ind];
        }
        throw new NoSuchElementException();
    }

    /**
     * toString.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(arr, 0, index));
    }

    /**
     * iterator.
     *
     * @return T Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new SimpleIterator<T>(arr);
    }

    /**
     * SimpleIterator.
     *
     * @author fourbarman (maks.java@yandex.ru).
     * @version %I%, %G%.
     * @since 08.05.2020.
     */
    public class SimpleIterator<E> implements Iterator<E> {
        int index = 0;
        E[] array;

        /**
         * Constructor
         *
         * @param array E array.
         */
        public SimpleIterator(E[] array) {
            this.array = array;
        }

        /**
         * Checks if array has next element.
         *
         * @return Boolean.
         */
        @Override
        public boolean hasNext() {
            return index < array.length && index < SimpleArray.this.index;
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
            return array[index++];
        }
    }
}
